package rooms.application.driving.usecases;

import rooms.application.driving.DrivingAdapter;
import rooms.application.driving.commands.CreateRoomCommand;
import rooms.domain.driven.RoomEventPublisher;
import rooms.domain.driven.RoomRepository;
import rooms.domain.model.Room;
import rooms.domain.model.RoomNumber;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@DrivingAdapter
@RequiredArgsConstructor
public class CreateRoomUseCase {

    private final RoomRepository roomRepository;
    private final RoomEventPublisher roomEventPublisher;

    @Transactional
    public Room create(CreateRoomCommand command) {
        RoomNumber newRoomNumber = roomRepository.getNextRoomNumber();

        Room newRoom = new Room(
                newRoomNumber,
                command.type(),
                command.hasMiniBar());

        Room savedRoom = roomRepository.save(newRoom);

        savedRoom.confirmCreation();

        roomEventPublisher.publishAll(savedRoom.domainEvents());

        savedRoom.clearDomainEvents();

        return savedRoom;
    }

}
