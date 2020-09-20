package com.luizfrra.stockSim.DTOs.Commons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public abstract class CommonDTO {

    @JsonIgnore
    public HashMap<String, String> validationErros;

    @JsonIgnore
    public abstract boolean isValide();

}
