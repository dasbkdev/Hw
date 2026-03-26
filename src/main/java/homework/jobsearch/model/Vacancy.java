package homework.jobsearch.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vacancy {
    Long id;
    String name;
    String description;
    Long categoryId;
    BigDecimal salary;
    Integer expFrom;
    Integer expTo;
    Boolean isActive;
    Long authorId;
    LocalDateTime createdDate;
    LocalDateTime updateTime;
}