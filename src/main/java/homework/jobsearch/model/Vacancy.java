package kg.attractor.jobsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vacancy {
    Long id;
    Long userId;
    Long categoryId;
    String name;
    String description;
    String salary;
    Integer experienceFrom;
    Integer experienceTo;
    Boolean active;
    LocalDateTime createdDate;
    LocalDateTime updateTime;
}