package ua.aleksenko.authorizationservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.aleksenko.authorizationservice.model.dto.AuthenticationResponseDto;
import ua.aleksenko.authorizationservice.model.dto.ResetPasswordDto;
import ua.aleksenko.authorizationservice.service.ResetPasswordService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reset-password")
@Validated
public class ResetPasswordController {

  private final ResetPasswordService resetPasswordService;

  @PutMapping
  public AuthenticationResponseDto resetPassword(@Valid @RequestBody ResetPasswordDto dto) {
    return resetPasswordService.resetPassword(dto);
  }
}
