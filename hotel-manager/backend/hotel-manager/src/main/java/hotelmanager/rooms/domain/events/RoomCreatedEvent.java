package hotelmanager.rooms.domain.events;

import hotelmanager.rooms.domain.model.Room;

@org.jmolecules.event.annotation.DomainEvent
public record RoomCreatedEvent(
        Long roomNumber,
        String roomType,
        boolean hasMiniBar) implements DomainEvent {

    public static RoomCreatedEvent of(Room room) {
        return new RoomCreatedEvent(
                room.getNumber().value(),
                room.getType().name(),
                room.isHasMiniBar());
    }

    @Override
    public int eventVersion() {
        return 1;
    }
}
