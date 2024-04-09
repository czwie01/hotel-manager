package rooms.application.driving.commands;

import rooms.domain.model.RoomNumber;
import rooms.domain.model.RoomType;
import lombok.Builder;

@Builder
public record UpdateRoomTypeCommand(RoomNumber number, RoomType newType) {
}
