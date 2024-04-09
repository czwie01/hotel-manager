package hotelmanager.rooms.domain.driven;

import java.util.Optional;
import java.util.Collection;
import hotelmanager.rooms.domain.model.Room;
import hotelmanager.rooms.domain.model.RoomNumber;

@org.jmolecules.ddd.annotation.Repository
public interface RoomRepository {
    Optional<Room> findByNumber(RoomNumber number);

    Collection<Room> findAll();

    Room save(Room room);

    void deleteById(RoomNumber number);

    RoomNumber getNextRoomNumber();
}
