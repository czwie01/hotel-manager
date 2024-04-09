package rooms.domain.driven;

import java.util.Optional;
import java.util.Collection;
import rooms.domain.model.Room;
import rooms.domain.model.RoomNumber;

@org.jmolecules.ddd.annotation.Repository
public interface RoomRepository {
    Optional<Room> findByNumber(RoomNumber number);

    Collection<Room> findAll();

    Room save(Room room);

    void deleteById(RoomNumber number);

    RoomNumber getNextRoomNumber();
}
