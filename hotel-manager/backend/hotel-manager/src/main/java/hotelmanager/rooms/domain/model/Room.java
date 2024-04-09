package hotelmanager.rooms.domain.model;

import org.jmolecules.ddd.annotation.AggregateRoot;

import hotelmanager.rooms.domain.events.MiniBarFromRoomRemovedEvent;
import hotelmanager.rooms.domain.events.MiniBarToRoomAddedEvent;
import hotelmanager.rooms.domain.events.RoomCreatedEvent;
import hotelmanager.rooms.domain.events.RoomRemovedEvent;
import hotelmanager.rooms.domain.events.RoomTypeUpdatedEvent;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AggregateRoot
@EqualsAndHashCode(callSuper = false)
public class Room extends Entity {
    private RoomNumber number;
    private RoomType type;
    private boolean hasMiniBar;

    public Room(RoomNumber number, RoomType type, boolean hasMiniBar) {
        this.number = number;
        setType(type);
        this.hasMiniBar = hasMiniBar;
    }

    public void setNumber(RoomNumber number) {
        if (number == null) {
            throw new IllegalArgumentException("Room number cannot be null");
        }
        this.number = number;

    }

    public void confirmCreation() {
        domainEvents().add(RoomCreatedEvent.of(this));
    }

    public void clearDomainEvents() {
        domainEvents().clear();
    }

    private void setType(RoomType type) {
        if (type == null) {
            throw new IllegalArgumentException("Room type cannot be null");
        }
        this.type = switch (type) {
            case SINGLE -> RoomType.SINGLE;
            case DOUBLE -> RoomType.DOUBLE;
            case SUITE -> RoomType.SUITE;
        };

    }

    public void updateType(RoomType newType) {
        setType(newType);
    }

    public void confirmRoomTypeUpdate() {
        domainEvents().add(RoomTypeUpdatedEvent.of(this));
    }

    public void remove() {
        domainEvents().add(RoomRemovedEvent.of(this));
    }

    public void confirmRemoval() {
        domainEvents().add(RoomRemovedEvent.of(this));
    }

    public void addMiniBar() {
        hasMiniBar = true;
    }

    public void confirmMiniBarAddition() {
        domainEvents().add(MiniBarToRoomAddedEvent.of(this));
    }

    public void removeMiniBar() {
        hasMiniBar = false;
    }

    public void confirmMiniBarRemoval() {
        domainEvents().add(MiniBarFromRoomRemovedEvent.of(this));
    }
}
