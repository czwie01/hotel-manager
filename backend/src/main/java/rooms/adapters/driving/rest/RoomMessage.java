package rooms.adapters.driving.rest;

import rooms.domain.model.Room;
import io.swagger.v3.oas.annotations.media.Schema;

public record RoomMessage(
        @Schema(description = "Zimmernummer", example = "101") Long roomNumber,
        @Schema(description = "Typ des Zimmers", example = "double") String roomType,
        @Schema(description = "Hat eine Minibar", example = "true") boolean hasMiniBar) {

    public static RoomMessage fromRoom(Room room) {
        return new RoomMessage(
                room.getNumber().value(),
                room.getType().name(),
                room.isHasMiniBar());
    }
}
