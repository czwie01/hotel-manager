package hotelmanager.rooms.application.driving.usecases;

import hotelmanager.rooms.application.driving.commands.CreateRoomCommand;
import hotelmanager.rooms.domain.driven.DrivenAdapter;
import hotelmanager.rooms.domain.driven.RoomEventPublisher;
import hotelmanager.rooms.domain.driven.RoomRepository;
import hotelmanager.rooms.domain.model.Room;
import hotelmanager.rooms.domain.model.RoomNumber;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@DrivenAdapter
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
