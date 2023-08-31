package io.huyvu.reboot.security.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AuthReq {
    @NotNull(message = "param 'idToken' required.")
    @NotEmpty(message = "param 'idToken' not empty.")
    @NotBlank(message = "param 'idToken' not blank.")
    private String idToken;
}
