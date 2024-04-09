package hotelmanager.rooms.application.driving.exceptions;

import hotelmanager.rooms.domain.model.RoomNumber;
import lombok.Getter;

@Getter
public class RoomNotFoundException extends RuntimeException {
    private final transient RoomNumber number;

    public RoomNotFoundException(RoomNumber number) {
        super("A room with the number %s does not exists".formatted(number.value()));
        this.number = number;
    }

}
