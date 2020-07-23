package healthcheck.application.controller;

import healthcheck.application.dto.LoginRequestDto;
import healthcheck.application.dto.LoginResponseDto;
import healthcheck.application.model.User;
import healthcheck.application.service.LoginService;
import healthcheck.application.service.UserService;
import healthcheck.application.session.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
        log.trace("LoginController: loginUser() loginRequestDto = {}", loginRequestDto);
        try {
            Session newUserSession = loginService.loginUser(loginRequestDto);
            User userLoggedIn = userService.getUserById(newUserSession.getUserId());


            LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                    .userId(newUserSession.getUserId())
                    .userToken(newUserSession.getUserToken())
                    .email(userLoggedIn.getEmail())
                    .username(userLoggedIn.getUsername())
                    .phone(userLoggedIn.getPhone())
                    .firstName(userLoggedIn.getFirstName())
                    .surname(userLoggedIn.getSurname())
                    .isAdmin(userLoggedIn.getIsAdmin())
                    .build();

            log.trace("LoginController: loginUser() loginResponseDto = {}", loginResponseDto);
            return new ResponseEntity<LoginResponseDto>(loginResponseDto, HttpStatus.OK);
        }
        catch (Exception exception) {
            log.trace("LoginController: loginUser() errorMessage = {}", exception.getMessage());
            return new ResponseEntity<LoginResponseDto>(LoginResponseDto.builder()
                    .build(),
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/user/logout", method = RequestMethod.POST)
    ResponseEntity<?> logoutUser(@RequestHeader("AUTHORIZATION") String userToken) {
        log.trace("LoginController: logoutUser() userToken = {}", userToken);
        try {
            loginService.logoutUser(userToken);

            log.trace("LoginController: logoutUser() ");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            log.trace("LoginController: logoutUser() errorMessage = {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
