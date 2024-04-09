package hotelmanager.rooms.domain.events;

import hotelmanager.rooms.domain.model.Room;

@org.jmolecules.event.annotation.DomainEvent
public record RoomRemovedEvent(
        Long roomNumber) implements DomainEvent {

    public static RoomRemovedEvent of(Room room) {
        return new RoomRemovedEvent(
                room.getNumber().value());

    }

    @Override
    public int eventVersion() {
        return 1;
    }
}
