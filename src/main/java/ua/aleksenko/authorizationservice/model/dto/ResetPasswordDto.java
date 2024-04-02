package ua.aleksenko.authorizationservice.model.dto;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordDto(@NotBlank
                               String email,
                               @NotBlank
                               String password) {

}
