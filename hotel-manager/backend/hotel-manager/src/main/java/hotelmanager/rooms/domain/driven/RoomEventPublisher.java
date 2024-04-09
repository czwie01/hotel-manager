package hotelmanager.rooms.domain.driven;

import java.util.Queue;

import hotelmanager.rooms.domain.events.DomainEvent;

public interface RoomEventPublisher {
    void publish(DomainEvent event);

    void publishAll(Queue<DomainEvent> domainEvent);

}
