package hotelmanager.rooms.adapters.driving.rest;

import java.util.Collection;

import org.hibernate.mapping.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.MediaType;

import hotelmanager.rooms.application.driving.commands.AddMiniBarToRoomCommand;
import hotelmanager.rooms.application.driving.commands.CreateRoomCommand;
import hotelmanager.rooms.application.driving.commands.RemoveMiniBarFromRoomCommand;
import hotelmanager.rooms.application.driving.commands.RemoveRoomCommand;
import hotelmanager.rooms.application.driving.usecases.AddMiniBarToRoomUseCase;
import hotelmanager.rooms.application.driving.usecases.CreateRoomUseCase;
import hotelmanager.rooms.application.driving.usecases.ListRoomsUseCase;
import hotelmanager.rooms.application.driving.usecases.RemoveMiniBarFromRoomUseCase;
import hotelmanager.rooms.application.driving.usecases.RemoveRoomUseCase;
import hotelmanager.rooms.domain.model.Room;
import hotelmanager.rooms.domain.model.RoomNumber;
import hotelmanager.rooms.domain.model.RoomType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
@Validated
public class RoomRestAdapter {
    private final CreateRoomUseCase createRoomUseCase;
    private final RemoveRoomUseCase removeRoomUseCase;
    private final AddMiniBarToRoomUseCase addMiniBarToRoomUseCase;
    private final RemoveMiniBarFromRoomUseCase removeMiniBarFromRoomUseCase;
    private final ListRoomsUseCase listRoomsUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(tags = "rooms", summary = "Erstellen eines Zimmers", description = "Erstellt ein neues Zimmer im Hotel.", responses = {
            @ApiResponse(responseCode = "201", description = "Zimmer wurde erfolgreich erstellt", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RoomCreatedMessage.class))),
            @ApiResponse(responseCode = BadRequestApiResponse.CODE, description = BadRequestApiResponse.MESSAGE, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
    })
    RoomCreatedMessage postEmployee(
            @Valid @RequestBody CreateRoomMessage createRoomMessage) {
        return toRoomCreatedMessage(createRoomUseCase.create(fromCreateRoomMessage(createRoomMessage)));
    }

    private RoomCreatedMessage toRoomCreatedMessage(Room room) {
        return RoomCreatedMessage.fromRoom(room);
    }

    private CreateRoomCommand fromCreateRoomMessage(@Valid CreateRoomMessage createRoomMessage) {
        return CreateRoomCommand.builder()
                .type(RoomType.valueOf(createRoomMessage.roomType().toUpperCase()))
                .hasMiniBar(createRoomMessage.hasMiniBar())
                .build();

    }

    @PutMapping("/addMiniBar/{roomNumber}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = "rooms", summary = "Minibar hinzufügen", description = "Fügt einem Zimmer per Zimmernummerangabe eine Minibar hinzu.", responses = {
            @ApiResponse(responseCode = "200", description = "Minibar wurde erfolgreich hinzugefügt"),
            @ApiResponse(responseCode = BadRequestApiResponse.CODE, description = BadRequestApiResponse.MESSAGE, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
    })
    void addMiniBarToRoom(
            @PathVariable Long roomNumber) {
        addMiniBarToRoomUseCase.addMiniBarToRoom(AddMiniBarToRoomCommand.builder()
                .roomNumber(new RoomNumber(roomNumber))
                .build());

    }

    @PutMapping("/removeMiniBar/{roomNumber}")
    @Operation(tags = "rooms", summary = "Minibar entfernen", description = "Entfernt die Minibar eines Zimmers per Zimmernummerangabe.", responses = {
            @ApiResponse(responseCode = "200", description = "Minibar wurde erfolgreich entfernt"),
            @ApiResponse(responseCode = BadRequestApiResponse.CODE, description = BadRequestApiResponse.MESSAGE, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
    })
    void removeMiniBarFromRoom(
            @PathVariable Long roomNumber) {
        removeMiniBarFromRoomUseCase
                .removeMiniBarFromRoom(RemoveMiniBarFromRoomCommand.builder()
                        .roomNumber(new RoomNumber(roomNumber))
                        .build());
    }

    @DeleteMapping("/remove/{roomNumber}")
    @Operation(tags = "rooms", summary = "Zimmer entfernen", description = "Entfernt ein Zimmer per Zimmernummerangabe.", responses = {
            @ApiResponse(responseCode = "200", description = "Zimmer wurde erfolgreich gelöscht"),
            @ApiResponse(responseCode = BadRequestApiResponse.CODE, description = BadRequestApiResponse.MESSAGE, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    void removeRoom(@PathVariable Long roomNumber) {
        removeRoomUseCase.removeRoom(RemoveRoomCommand.builder()
                .roomNumber(new RoomNumber(roomNumber))
                .build());
    }

    @GetMapping("/list")
    @Operation(tags = "rooms", summary = "Zimmer auflisten", description = "Listet alle Zimmer im Hotel auf.", responses = {
            @ApiResponse(responseCode = "200", description = "Zimmer wurden erfolgreich aufgelistet", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = BadRequestApiResponse.CODE, description = BadRequestApiResponse.MESSAGE, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    Collection<RoomMessage> listRooms() {
        return listRoomsUseCase.list().stream()
                .map(RoomMessage::fromRoom)
                .toList();
    }

}
