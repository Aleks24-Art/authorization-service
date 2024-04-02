package ua.aleksenko.authorizationservice.controller;

import com.auth0.jwt.JWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.aleksenko.authorizationservice.model.dto.UserDto;
import ua.aleksenko.authorizationservice.model.entity.User;
import ua.aleksenko.authorizationservice.service.UserService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  private final UserService userService;

  @GetMapping("/profile/{token}")
  @ResponseStatus(HttpStatus.OK)
  public UserDto getUserInfo(@PathVariable String token) {
    log.info("Try to get User info by token: {}", token);
    return userService.findUserDto(JWT.decode(token).getSubject());
  }

  @PutMapping("/profile/update")
  @ResponseStatus(HttpStatus.OK)
  public UserDto getUserInfo(@RequestBody User user) {
    return userService.updateUser(user);
  }

}
