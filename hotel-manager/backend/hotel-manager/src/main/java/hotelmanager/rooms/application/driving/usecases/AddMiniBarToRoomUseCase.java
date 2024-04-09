package hotelmanager.rooms.application.driving.usecases;

import hotelmanager.rooms.application.driving.DrivingAdapter;
import hotelmanager.rooms.application.driving.commands.AddMiniBarToRoomCommand;
import hotelmanager.rooms.application.driving.exceptions.RoomNotFoundException;
import hotelmanager.rooms.domain.driven.RoomEventPublisher;
import hotelmanager.rooms.domain.driven.RoomRepository;
import hotelmanager.rooms.domain.model.Room;
import hotelmanager.rooms.domain.model.RoomNumber;
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
