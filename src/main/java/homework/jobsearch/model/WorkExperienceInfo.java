package kg.attractor.jobsearch.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkExperienceInfo {
    Long id;
    Long resumeId;
    Integer years;
    String companyName;
    String position;
    String responsibilities;
}