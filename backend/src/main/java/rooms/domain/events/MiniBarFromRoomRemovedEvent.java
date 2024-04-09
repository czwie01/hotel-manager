package rooms.domain.events;

import rooms.domain.model.Room;

@org.jmolecules.event.annotation.DomainEvent
public record MiniBarFromRoomRemovedEvent(
        Long roomNumber) implements DomainEvent {

    public static MiniBarFromRoomRemovedEvent of(Room room) {
        return new MiniBarFromRoomRemovedEvent(
                room.getNumber().value());

    }

    @Override
    public int eventVersion() {
        return 1;
    }
}
