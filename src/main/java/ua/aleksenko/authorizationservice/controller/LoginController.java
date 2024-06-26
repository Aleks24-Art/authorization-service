package ua.aleksenko.authorizationservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.aleksenko.authorizationservice.model.dto.AuthenticationResponseDto;
import ua.aleksenko.authorizationservice.model.dto.LoginRequestDto;
import ua.aleksenko.authorizationservice.service.LoginService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
@Validated
public class LoginController {

  private final LoginService loginService;

  @PostMapping
  public AuthenticationResponseDto login(@Valid @RequestBody LoginRequestDto dto) {
    return loginService.login(dto);
  }
}
