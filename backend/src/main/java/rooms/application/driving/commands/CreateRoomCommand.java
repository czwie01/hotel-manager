package rooms.application.driving.commands;

import rooms.domain.model.RoomType;
import lombok.Builder;

@Builder
public record CreateRoomCommand(
        RoomType type,
        boolean hasMiniBar) {
}
