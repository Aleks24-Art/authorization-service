package ua.aleksenko.authorizationservice.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record LoginRequestDto(@Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$") String email,
                              @NotBlank String password) {
}
