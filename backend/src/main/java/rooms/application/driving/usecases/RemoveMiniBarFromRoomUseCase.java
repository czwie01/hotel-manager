package rooms.application.driving.usecases;

import rooms.application.driving.DrivingAdapter;
import rooms.application.driving.commands.RemoveMiniBarFromRoomCommand;
import rooms.application.driving.exceptions.RoomNotFoundException;
import rooms.domain.driven.RoomEventPublisher;
import rooms.domain.driven.RoomRepository;
import rooms.domain.model.Room;
import rooms.domain.model.RoomNumber;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@DrivingAdapter
@RequiredArgsConstructor
public class RemoveMiniBarFromRoomUseCase {
    private final RoomRepository roomRepository;
    private final RoomEventPublisher roomEventPublisher;

    @Transactional
    public void removeMiniBarFromRoom(RemoveMiniBarFromRoomCommand command) {
        RoomNumber roomNumber = command.roomNumber();

        Room room = roomRepository.findByNumber(roomNumber)
                .orElseThrow(() -> new RoomNotFoundException(roomNumber));

        room.removeMiniBar();
        roomRepository.save(room);
        room.confirmMiniBarRemoval();
        roomEventPublisher.publishAll(room.domainEvents());
        room.clearDomainEvents();
    }
}