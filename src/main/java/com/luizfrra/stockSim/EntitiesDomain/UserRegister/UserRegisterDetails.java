package com.luizfrra.stockSim.EntitiesDomain.UserRegister;

import lombok.Getter;

@Getter
public class UserRegisterDetails {

    private final String realmId;

    private final String clientId;

    private final String userId;

    private final String email;

    private final String userName;


    public UserRegisterDetails(String realmId, String clientId, String userId, String email, String userName) {
        this.realmId = realmId;
        this.clientId = clientId;
        this.userId = userId;
        this.email = email;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "{" +
                "realmId:'" + realmId + '\'' +
                ", clientId:'" + clientId + '\'' +
                ", userId:'" + userId + '\'' +
                ", email:'" + email + '\'' +
                ", userName:'" + userName + '\'' +
                '}';
    }

}
