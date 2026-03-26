package kg.attractor.jobsearch.model;

import kg.attractor.jobsearch.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Long id;
    String name;
    String email;
    String phoneNumber;
    String password;
    String avatar;
    Role role;
    Boolean enabled;
}