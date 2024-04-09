package hotelmanager.rooms.domain.events;

import hotelmanager.rooms.domain.model.Room;

@org.jmolecules.event.annotation.DomainEvent
public record MiniBarToRoomAddedEvent(
        Long roomNumber) implements DomainEvent {

    public static MiniBarToRoomAddedEvent of(Room room) {
        return new MiniBarToRoomAddedEvent(
                room.getNumber().value());

    }

    @Override
    public int eventVersion() {
        return 1;
    }
}
