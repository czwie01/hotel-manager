package hotelmanager.rooms.application.driving.usecases;

import hotelmanager.rooms.application.driving.commands.UpdateRoomTypeCommand;
import hotelmanager.rooms.application.driving.exceptions.RoomNotFoundException;
import hotelmanager.rooms.domain.driven.RoomEventPublisher;
import hotelmanager.rooms.domain.driven.RoomRepository;
import hotelmanager.rooms.domain.model.Room;
import hotelmanager.rooms.domain.model.RoomNumber;
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