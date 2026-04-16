package homework.jobsearch.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterDto {

    @NotBlank
    String name;

    @NotBlank
    String surname;

    @NotNull
    @Min(value = 18, message = "Возраст должен быть не менее 18 лет")
    Integer age;

    @NotBlank
    @Email
    String email;

    @NotBlank
    String password;

    @NotBlank
    String phoneNumber;

    @NotBlank
    String accountType;
}