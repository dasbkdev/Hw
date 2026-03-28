package homework.jobsearch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VacancyDto {
    @NotBlank
    String name;

    @NotBlank
    String description;

    @NotNull
    Long categoryId;

    @NotNull
    BigDecimal salary;

    @NotNull
    Integer expFrom;

    @NotNull
    Integer expTo;

    @NotNull
    Boolean isActive;
}