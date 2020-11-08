package com.luizfrra.stockSim.DTOs.UserQuotes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luizfrra.stockSim.DTOs.Commons.CommonDTO;
import com.luizfrra.stockSim.Utils.StringStockUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserQuotesDTO extends CommonDTO {
    public String userId;

    public String symbol;

    public int quantity;

    public OperationType operationType;

    @JsonIgnore
    public boolean isValide() {
        validationErros = new HashMap<>();
        boolean isValide = true;

        if(StringStockUtils.isNullOrEmptyOrOnlyWhiteSpace(symbol)) {
            validationErros.put("symbol", "Symbol Is Empty");
            isValide = false;
        } else if(StringStockUtils.isNullOrEmptyOrOnlyWhiteSpace(userId)) {
            validationErros.put("userId", "Invalid UserId");
            isValide = false;
        } else if(quantity <= 0) {
            validationErros.put("userId", "quantity need be greater than 0");
            isValide = false;
        }

        return isValide;
    }
}
