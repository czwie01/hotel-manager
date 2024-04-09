package hotelmanager.rooms.application.driving.commands;

import hotelmanager.rooms.domain.model.RoomType;
import lombok.Builder;

@Builder
public record CreateRoomCommand(
        RoomType type,
        boolean hasMiniBar) {
}
