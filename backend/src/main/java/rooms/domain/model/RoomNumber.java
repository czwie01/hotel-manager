package rooms.domain.model;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record RoomNumber(Long value) {
    public RoomNumber {
        if (value == null) {
            throw new IllegalArgumentException("RoomNumber cannot be null");
        } else if (value < 0) {
            throw new IllegalArgumentException("RoomNumber cannot be negative");
        }
    }
}
