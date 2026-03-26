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
public class Resume {
    Long id;
    Long applicantId;
    String name;
    Long categoryId;
    BigDecimal salary;
    Boolean isActive;
    LocalDateTime createdDate;
    LocalDateTime updateTime;
}