package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDTO implements Serializable {
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;

    public String getEmail() {
        return this.email;
    }
    public String getPhone() {
        return this.phone;
    }
}
