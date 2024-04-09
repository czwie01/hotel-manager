package rooms.application.driving.commands;

import rooms.domain.model.RoomNumber;
import lombok.Builder;

@Builder
public record AddMiniBarToRoomCommand(RoomNumber roomNumber) {
}
