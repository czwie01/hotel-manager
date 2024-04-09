package hotelmanager.rooms.domain.events;

import hotelmanager.rooms.domain.model.Room;

@org.jmolecules.event.annotation.DomainEvent
public record RoomTypeUpdatedEvent(
        Long roomNumber, String roomType) implements DomainEvent {

    public static RoomTypeUpdatedEvent of(Room room) {
        return new RoomTypeUpdatedEvent(
                room.getNumber().value(),
                room.getType().name());

    }

    @Override
    public int eventVersion() {
        return 1;
    }
}
