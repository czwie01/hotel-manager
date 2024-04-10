package rooms.adapters.driving.rest;

import rooms.domain.model.Room;
import rooms.domain.model.RoomType;
import io.swagger.v3.oas.annotations.media.Schema;

public record RoomCreatedMessage(
        @Schema(description = "Zimmernummer", example = "101") Long roomNumber,
        @Schema(description = "Typ des Zimmers", example = "double") RoomType roomType,
        @Schema(description = "Hat eine Minibar", example = "true") boolean hasMiniBar) {

    public static RoomCreatedMessage fromRoom(Room room) {
        return new RoomCreatedMessage(
                room.getNumber().value(),
                room.getType(),
                room.isHasMiniBar());
    }

}
