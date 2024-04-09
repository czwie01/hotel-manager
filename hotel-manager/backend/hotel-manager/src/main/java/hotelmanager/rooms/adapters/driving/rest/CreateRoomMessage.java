package hotelmanager.rooms.adapters.driving.rest;

import hotelmanager.rooms.domain.model.Room;
import io.swagger.v3.oas.annotations.media.Schema;

public record CreateRoomMessage(
        @Schema(description = "Zimmernummer", example = "101") Long roomNumber,
        @Schema(description = "Typ des Zimmers", example = "double") String roomType,
        @Schema(description = "Hat eine Minibar", example = "true") boolean hasMiniBar) {

    public static CreateRoomMessage fromRoom(Room room) {
        return new CreateRoomMessage(
                room.getNumber().value(),
                room.getType().name(),
                room.isHasMiniBar());
    }

}
