package com.luizfrra.stockSim.EntitiesDomain.UserQuotes;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserQuotesKey implements Serializable {
    @Column(name = "user_id")
    Long userId;

    @Column(name = "quote_id")
    String quotedId;
}
