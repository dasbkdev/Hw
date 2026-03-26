package homework.jobsearch.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EducationInfo {
    Long id;
    Long resumeId;
    String institution;
    String program;
    LocalDate startDate;
    LocalDate endDate;
    String degree;
}