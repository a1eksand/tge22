package io.may4th.tge22.web.controllers;

import io.may4th.tge22.security.api.CurrentUser;
import io.may4th.tge22.security.api.Secured;
import io.may4th.tge22.services.api.RoomService;
import io.may4th.tge22.services.api.tos.NewRoomTO;
import io.may4th.tge22.services.api.tos.RoomTO;
import io.may4th.tge22.web.payload.ApiErrorResponse;
import io.may4th.tge22.web.security.UserDetailsImpl;
import io.may4th.tge22.web.services.PermissionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/api/room")
@RestController
@ApiResponses({
    @ApiResponse(code = 400, message = "Bad Request", response = ApiErrorResponse.class)
})
@Secured
public class RoomController {

    @Autowired
    private final RoomService roomService;

    @Autowired
    private final PermissionService permissionService;

    @ApiOperation("getRoom")
    @ApiResponse(code = 200, message = "OK", response = RoomTO.class)
    @GetMapping
    public RoomTO getRoomById(
        @ApiIgnore @CurrentUser UserDetailsImpl currentUser,
        @RequestParam UUID roomId
    ) {
        permissionService.validateRoomPermission(currentUser, roomId);
        return roomService.findById(roomId);
    }

    @ApiOperation("postRoom")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Created", response = RoomTO.class),
        @ApiResponse(code = 409, message = "Conflict")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomTO postRoom(
        @ApiIgnore @CurrentUser UserDetailsImpl currentUser,
        @RequestBody @Valid NewRoomTO newRoomTO
    ) {
        permissionService.validateCreatedByCurrentUser(currentUser, newRoomTO);
        return roomService.save(newRoomTO);
    }
}
