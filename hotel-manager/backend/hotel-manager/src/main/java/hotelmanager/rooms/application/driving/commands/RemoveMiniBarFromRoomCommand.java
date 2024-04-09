package hotelmanager.rooms.application.driving.commands;

import hotelmanager.rooms.domain.model.RoomNumber;
import lombok.Builder;

@Builder
public record RemoveMiniBarFromRoomCommand(RoomNumber roomNumber) {
}
