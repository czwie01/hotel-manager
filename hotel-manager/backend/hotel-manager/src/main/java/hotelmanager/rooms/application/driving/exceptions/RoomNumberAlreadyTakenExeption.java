package hotelmanager.rooms.application.driving.exceptions;

import hotelmanager.rooms.domain.model.RoomNumber;
import lombok.Getter;

@Getter
public class RoomNumberAlreadyTakenExeption extends RuntimeException {
    private final transient RoomNumber number;

    public RoomNumberAlreadyTakenExeption(RoomNumber number) {
        super("A room with the number %s already exists".formatted(number.value()));
        this.number = number;
    }
}