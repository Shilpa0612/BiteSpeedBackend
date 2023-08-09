package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDTO {
    private String email;
    private String phone;

    public String getEmail() {
        return this.email;
    }
    public String getPhone() {
        return this.phone;
    }
}
