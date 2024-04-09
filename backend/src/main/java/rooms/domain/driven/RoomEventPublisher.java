package rooms.domain.driven;

import java.util.Queue;

import rooms.domain.events.DomainEvent;

public interface RoomEventPublisher {
    void publish(DomainEvent event);

    void publishAll(Queue<DomainEvent> domainEvent);

}
