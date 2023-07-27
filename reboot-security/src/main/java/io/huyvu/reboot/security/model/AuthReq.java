package io.huyvu.reboot.security.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AuthReq {
    @NotNull(message = "param 'idToken' required.")
    @NotEmpty(message = "param 'idToken' not empty.")
    @NotBlank(message = "param 'idToken' not blank.")
    private String idToken;
}
