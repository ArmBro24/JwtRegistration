package com.example.javaregistration.DTO;


import com.example.javaregistration.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"id", "email"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    Long id;

    String name;

    String email;

    String password;

    Role role;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}


