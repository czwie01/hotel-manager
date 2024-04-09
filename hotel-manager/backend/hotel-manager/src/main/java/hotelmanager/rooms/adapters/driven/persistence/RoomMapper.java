package hotelmanager.rooms.adapters.driven.persistence;

import hotelmanager.rooms.domain.model.Room;
import hotelmanager.rooms.domain.model.RoomNumber;

public class RoomMapper {

    private RoomMapper() {
        // private constructor to hide the implicit public one
    }

    public static Room toDomain(RoomEntity roomEntity) {
        return new Room(
                new RoomNumber(roomEntity.getRoomNumber()),
                roomEntity.getRoomType(),
                roomEntity.isHasMiniBar());
    }

    public static RoomEntity toEntity(Room room) {
        return RoomEntity.builder()
                .roomNumber(room.getNumber().value())
                .roomType(room.getType())
                .hasMiniBar(room.isHasMiniBar())
                .build();
    }
}