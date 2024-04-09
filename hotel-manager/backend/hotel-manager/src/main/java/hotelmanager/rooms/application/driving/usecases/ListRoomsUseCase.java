package hotelmanager.rooms.application.driving.usecases;

import java.util.Collection;

import hotelmanager.rooms.domain.driven.DrivenAdapter;
import hotelmanager.rooms.domain.driven.RoomRepository;
import hotelmanager.rooms.domain.model.Room;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@DrivenAdapter
@RequiredArgsConstructor
public class ListRoomsUseCase {
    private final RoomRepository roomRepository;

    @Transactional
    public Collection<Room> list() {
        return roomRepository.findAll();
    }
}
