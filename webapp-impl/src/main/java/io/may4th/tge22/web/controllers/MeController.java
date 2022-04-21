package io.may4th.tge22.web.controllers;

import io.may4th.tge22.security.api.CurrentUser;
import io.may4th.tge22.security.api.Secured;
import io.may4th.tge22.web.payload.ApiErrorResponse;
import io.may4th.tge22.web.security.UserDetailsImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@AllArgsConstructor
@RequestMapping("/api/me")
@RestController
@Secured
@ApiResponses({
    @ApiResponse(code = 400, message = "Bad Request", response = ApiErrorResponse.class)
})
public class MeController {

    @ApiOperation("getRoom")
    @ApiResponse(code = 200, message = "OK", response = UserDetailsImpl.class)
    @GetMapping
    public UserDetailsImpl getMe(@ApiIgnore @CurrentUser UserDetailsImpl currentUser) {
        return currentUser;
    }
}
