package io.may4th.tge22.web.controllers;

import io.may4th.tge22.security.api.CurrentUser;
import io.may4th.tge22.security.api.Secured;
import io.may4th.tge22.web.payload.ApiErrorResponse;
import io.may4th.tge22.web.payload.AuthTokenResponse;
import io.may4th.tge22.web.payload.SignInRequest;
import io.may4th.tge22.web.payload.SignUpRequest;
import io.may4th.tge22.web.security.UserDetailsImpl;
import io.may4th.tge22.web.services.AuthService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@AllArgsConstructor
@RequestMapping("/api/auth")
@RestController
@ApiResponses({
    @ApiResponse(code = 400, message = "Bad Request", response = ApiErrorResponse.class)
})
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping("/signin")
    public AuthTokenResponse signin(@RequestBody @Valid SignInRequest signInRequest) {
        return authService.authenticate(signInRequest.getUsername(), signInRequest.getPassword());
    }

    @PostMapping("/signup")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK", response = AuthTokenResponse.class),
        @ApiResponse(code = 409, message = "Conflict")
    })
    public AuthTokenResponse signup(@RequestBody @Valid SignUpRequest signUpRequest) {
        return authService.signup(signUpRequest.getUsername(), signUpRequest.getPassword());
    }

    @GetMapping("/me")
    @Secured
    public UserDetailsImpl me(@ApiIgnore @CurrentUser UserDetailsImpl currentUser) {
        return currentUser;
    }
}
