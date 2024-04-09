package rooms.application.driving.usecases;

import rooms.application.driving.commands.UpdateRoomTypeCommand;
import rooms.application.driving.exceptions.RoomNotFoundException;
import rooms.domain.driven.RoomEventPublisher;
import rooms.domain.driven.RoomRepository;
import rooms.domain.model.Room;
import rooms.domain.model.RoomNumber;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateRoomTypeUseCase {

    private final RoomRepository roomRepository;
    private final RoomEventPublisher roomEventPublisher;

    @Transactional
    public void updateRoomType(UpdateRoomTypeCommand command) {
        RoomNumber roomNumber = command.number();

        Room room = roomRepository
                .findByNumber(roomNumber)
                .orElseThrow(() -> new RoomNotFoundException(roomNumber));

        room.updateType(command.newType());

        Room updatedRoom = roomRepository.save(room);

        updatedRoom.confirmRoomTypeUpdate();

        roomEventPublisher.publishAll(updatedRoom.domainEvents());

        updatedRoom.clearDomainEvents();
    }
}