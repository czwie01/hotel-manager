package rooms.application.driving.usecases;

import rooms.application.driving.DrivingAdapter;
import rooms.application.driving.commands.AddMiniBarToRoomCommand;
import rooms.application.driving.exceptions.RoomNotFoundException;
import rooms.domain.driven.RoomEventPublisher;
import rooms.domain.driven.RoomRepository;
import rooms.domain.model.Room;
import rooms.domain.model.RoomNumber;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@DrivingAdapter
@RequiredArgsConstructor
public class AddMiniBarToRoomUseCase {
    private final RoomRepository roomRepository;
    private final RoomEventPublisher roomEventPublisher;

    @Transactional
    public void addMiniBarToRoom(AddMiniBarToRoomCommand command) {
        RoomNumber roomNumber = command.roomNumber();

        Room room = roomRepository.findByNumber(roomNumber)
                .orElseThrow(() -> new RoomNotFoundException(roomNumber));

        room.addMiniBar();
        roomRepository.save(room);
        room.confirmMiniBarAddition();
        roomEventPublisher.publishAll(room.domainEvents());
        room.clearDomainEvents();
    }
}
