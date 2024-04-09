package hotelmanager.rooms.application.driving.commands;

import hotelmanager.rooms.domain.model.RoomNumber;
import hotelmanager.rooms.domain.model.RoomType;
import lombok.Builder;

@Builder
public record UpdateRoomTypeCommand(RoomNumber number, RoomType newType) {
}
