package hotelmanager.rooms.adapters.driven.messaging;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import hotelmanager.rooms.domain.driven.DrivenAdapter;
import hotelmanager.rooms.domain.driven.RoomEventPublisher;
import hotelmanager.rooms.domain.events.DomainEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DrivenAdapter
public class RoomMessengerAdapter implements RoomEventPublisher {

    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;
    private final String topicName;

    public RoomMessengerAdapter(KafkaTemplate<String, DomainEvent> kafkaTemplate,
            @Value("${spring.kafka.topic.name}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    @Override
    public void publish(DomainEvent event) {
        kafkaTemplate.send(topicName, event).thenAccept(result -> log.info("Event published: " + event))
                .exceptionally(ex -> {
                    log.info("Failed to send message");
                    return null;
                });
    }

    @Override
    public void publishAll(Queue<DomainEvent> events) {
        while (!events.isEmpty()) {
            publish(events.poll());
        }
    }
}