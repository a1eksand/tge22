package io.may4th.tge22.web.controllers;

import io.may4th.tge22.security.api.CurrentUser;
import io.may4th.tge22.security.api.Secured;
import io.may4th.tge22.services.api.RoomService;
import io.may4th.tge22.services.api.tos.RoomTO;
import io.may4th.tge22.web.payload.ApiErrorResponse;
import io.may4th.tge22.web.security.UserDetailsImpl;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/api/me")
@RestController
@Secured
@ApiResponses({
    @ApiResponse(code = 400, message = "Bad Request", response = ApiErrorResponse.class)
})
public class MeController {

    @Autowired
    private final RoomService roomService;

    @GetMapping
    public UserDetailsImpl getMe(@ApiIgnore @CurrentUser UserDetailsImpl currentUser) {
        return currentUser;
    }

    @GetMapping("/rooms")
    public List<RoomTO> getRooms(@ApiIgnore @CurrentUser UserDetailsImpl currentUser) {
        return roomService.findAllByUsersId(currentUser.getUuid());
    }

    @PutMapping("/join")
    public void putJoin(
        @ApiIgnore @CurrentUser UserDetailsImpl currentUser,
        @RequestParam UUID roomId
    ) {
        roomService.joinUserToRoom(currentUser.getUuid(), roomId);
    }
}
