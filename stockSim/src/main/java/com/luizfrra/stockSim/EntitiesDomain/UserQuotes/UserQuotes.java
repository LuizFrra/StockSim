package com.luizfrra.stockSim.EntitiesDomain.UserQuotes;

import com.luizfrra.stockSim.EntitiesDomain.Quote.Quote;
import com.luizfrra.stockSim.EntitiesDomain.User.User;

import javax.persistence.*;

@Entity(name = "tbl_userQuotes")
public class UserQuotes {
    @EmbeddedId
    UserQuotesKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("quoteId")
    @JoinColumn(name = "quote_id")
    Quote quote;

    @Column(precision = 2, nullable = false)
    double averagePrice = 0.0;

    @Column(nullable = false)
    int numberOfQuotes = 0;
}
