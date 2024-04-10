package rooms.adapters.driving.rest;

import rooms.domain.model.Room;
import rooms.domain.model.RoomType;
import io.swagger.v3.oas.annotations.media.Schema;

public record CreateRoomMessage(
        @Schema(description = "Typ des Zimmers", example = "double") RoomType roomType,
        @Schema(description = "Hat eine Minibar", example = "true") boolean hasMiniBar) {

    public static CreateRoomMessage fromRoom(Room room) {
        return new CreateRoomMessage(
                room.getType(),
                room.isHasMiniBar());
    }

}
