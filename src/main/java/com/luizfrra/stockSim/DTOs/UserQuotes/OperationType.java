package com.luizfrra.stockSim.DTOs.UserQuotes;

public enum OperationType {
    SELL(0), BUY(1);

    public int operationType;

    OperationType(int i) {
        this.operationType = i;
    }

    OperationType(String type) {
        if(type.equals("SELL"))
            this.operationType = 0;
        else if(type.equals("BUY"))
            this.operationType = 1;
    }
}
