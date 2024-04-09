package rooms.application.driving.usecases;

import java.util.Collection;

import rooms.domain.driven.DrivenAdapter;
import rooms.domain.driven.RoomRepository;
import rooms.domain.model.Room;
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
