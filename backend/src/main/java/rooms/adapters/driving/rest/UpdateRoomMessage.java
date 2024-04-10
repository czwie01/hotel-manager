package rooms.adapters.driving.rest;

import rooms.domain.model.RoomType;

public record UpdateRoomMessage(Long roomNumber, RoomType roomType, boolean hasMiniBar ) {
}
