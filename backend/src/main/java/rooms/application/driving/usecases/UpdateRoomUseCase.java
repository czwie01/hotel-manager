package rooms.application.driving.usecases;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import rooms.application.driving.DrivingAdapter;
import rooms.application.driving.commands.UpdateRoomCommand;
import rooms.application.driving.exceptions.RoomNotFoundException;
import rooms.domain.driven.RoomEventPublisher;
import rooms.domain.driven.RoomRepository;
import rooms.domain.model.Room;
import rooms.domain.model.RoomNumber;

@DrivingAdapter
@RequiredArgsConstructor
public class UpdateRoomUseCase {
    private final RoomRepository roomRepository;
    private final RoomEventPublisher roomEventPublisher;

    @Transactional
    public void updateRoom(UpdateRoomCommand command) {
        RoomNumber roomNumber = command.roomNumber();

        Room room = roomRepository
                .findByNumber(roomNumber)
                .orElseThrow(() -> new RoomNotFoundException(roomNumber));

        if(!room.getType().toString().equals(command.type().toString())) {
            room.updateType(command.type());
        }
        if(room.isHasMiniBar() != command.hasMiniBar()) {
            if(command.hasMiniBar()) {
                room.addMiniBar();
                room.confirmMiniBarAddition();
            } else {
                room.removeMiniBar();
                room.confirmMiniBarRemoval();
            }
        }

        Room updatedRoom = roomRepository.save(room);
        
        room.confirmRoomTypeUpdate();
        if(room.isHasMiniBar() != command.hasMiniBar()) {
            if(command.hasMiniBar()) {
                room.confirmMiniBarAddition();
            } else {
                room.confirmMiniBarRemoval();
            }
        }

        roomEventPublisher.publishAll(updatedRoom.domainEvents());
        updatedRoom.clearDomainEvents();
    }
}
