package com.barstech.financeProject.models.login;

import lombok.*;

@Data
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class LoginRequest {

    private  String email;
    private  String password;

    protected void setEmail(String email) {
        // if programmer add @NotNull attribute for each field it Check for null (=> NullPointerException)
        // and valid email code (=> IllegalArgumentException)
        this.email = email;
    }

}
