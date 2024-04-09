package hotelmanager.rooms.adapters.driven.persistence;

import java.util.Collection;
import java.util.Optional;

import hotelmanager.rooms.domain.driven.DrivenAdapter;
import hotelmanager.rooms.domain.driven.RoomRepository;
import hotelmanager.rooms.domain.model.Room;
import hotelmanager.rooms.domain.model.RoomNumber;
import lombok.RequiredArgsConstructor;

@DrivenAdapter
@RequiredArgsConstructor
public class RoomPersistenceAdapter implements RoomRepository {

    private final JpaRoomRepository repository;

    @Override
    public Optional<Room> findByNumber(RoomNumber number) {
        return repository.findById(number.value()).map(RoomMapper::toDomain);
    }

    @Override
    public Collection<Room> findAll() {
        return repository.findAll().stream().map(RoomMapper::toDomain).toList();
    }

    @Override
    public Room save(Room room) {
        return RoomMapper.toDomain(repository.save(RoomMapper.toEntity(room)));
    }

    @Override
    public void deleteById(RoomNumber number) {
        repository.deleteById(number.value());
    }

    @Override
    public RoomNumber getNextRoomNumber() {
        RoomNumber result;
        do {
            result = new RoomNumber(repository.getNextSeriesId());
        } while (repository.existsById(result.value()));
        return result;
    }
}
