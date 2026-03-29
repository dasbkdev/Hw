package homework.jobsearch.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Long id;
    String name;
    String surname;
    Integer age;
    String email;
    String password;
    String phoneNumber;
    String avatar;
    String accountType;
    Boolean enabled;
    Long roleId;
}