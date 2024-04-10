package rooms.application.driving.commands;

import lombok.Builder;
import rooms.domain.model.RoomNumber;
import rooms.domain.model.RoomType;

@Builder
public record UpdateRoomCommand(
    RoomNumber roomNumber,
    RoomType type,
    boolean hasMiniBar) {

}
