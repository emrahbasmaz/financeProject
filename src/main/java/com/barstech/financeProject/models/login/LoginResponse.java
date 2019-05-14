package com.barstech.financeProject.models.login;

import lombok.*;

@Data
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String status;
}