package hotelmanager.rooms.adapters.driven.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRoomRepository extends JpaRepository<RoomEntity, Long> {

    @Query(value = "SELECT nextval('rooms_id_seq')", nativeQuery = true)
    Long getNextSeriesId();

}
