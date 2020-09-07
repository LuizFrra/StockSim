package com.luizfrra.stockSim.DTOs.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luizfrra.stockSim.Utils.StringStockUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @JsonIgnore
    HashMap<String, String> validationErros;

    @JsonIgnore
    public void cleanCredentials() {
        password = "";
    }

    @JsonIgnore
    public boolean isValide() {
        validationErros = new HashMap<>();
        boolean isValid = true;

        if(StringStockUtils.isNullOrEmptyOrOnlyWhiteSpace(email)) {
            validationErros.put("email", "Email is Empty");
            isValid = false;
        }
        else if(!StringStockUtils.isEmail(email)) {
            validationErros.put("email", "Email is invalid");
            isValid = false;
        }
        else if(StringStockUtils.isNullOrEmptyOrOnlyWhiteSpace(password)) {
            validationErros.put("password", "Password Is Empty");
            isValid = false;
        }
        else  if(StringStockUtils.isNullOrEmptyOrOnlyWhiteSpace(firstName)) {
            validationErros.put("firstName", "FirstName is Empty");
            isValid = false;
        }

        return isValid;
    }

}
