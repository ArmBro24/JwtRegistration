package com.example.javaregistration.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    public String getFirstName() {
        return firstname;
    }

    public String getLastName(){
        return lastname;
    }
}
