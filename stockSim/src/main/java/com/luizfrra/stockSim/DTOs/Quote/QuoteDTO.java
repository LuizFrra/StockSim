package com.luizfrra.stockSim.DTOs.Quote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luizfrra.stockSim.DTOs.Commons.CommonDTO;
import com.luizfrra.stockSim.Utils.StringStockUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuoteDTO extends CommonDTO {

    public String symbol;

    public String name;

    public double price;

    public Date updatedAt;

    @JsonIgnore
    public HashMap<String, String> validationErros;

    @JsonIgnore
    public boolean isValide() {
        validationErros = new HashMap<>();
        boolean isValid = true;
        if(StringStockUtils.isNullOrEmptyOrOnlyWhiteSpace(symbol)) {
            validationErros.put("symbol", "Symbol is Required");
            isValid = false;
        }
        return isValid;
    }
}
