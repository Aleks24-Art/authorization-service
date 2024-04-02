package ua.aleksenko.authorizationservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private String firstName;

  private String lastName;

  private Integer age;

  private String gender;

  private String address;

  private String website;

  private String email;
}
