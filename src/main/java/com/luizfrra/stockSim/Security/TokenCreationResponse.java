package com.luizfrra.stockSim.Security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenCreationResponse {

    private String access_token;

    private long exp;

    private String token_type;

}
