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
public class Resume {
    Long id;
    Long userId;
    Long categoryId;
    String name;
    String salary;
    String aboutMe;
    String telegram;
    String email;
    String phoneNumber;
    String facebookLink;
    String linkedInLink;
    Boolean active;
    LocalDateTime createdDate;
    LocalDateTime updateTime;
}