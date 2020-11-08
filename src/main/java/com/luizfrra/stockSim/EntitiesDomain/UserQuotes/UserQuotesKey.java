package com.luizfrra.stockSim.EntitiesDomain.UserQuotes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserQuotesKey implements Serializable {
    @Column(name = "user_id")
    String userId;

    @Column(name = "quote_id")
    String quotedId;
}
