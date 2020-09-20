package com.luizfrra.stockSim.EntitiesDomain.UserQuotes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luizfrra.stockSim.EntitiesDomain.Quote.Quote;
import com.luizfrra.stockSim.EntitiesDomain.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "tbl_userQuotes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserQuotes {
    @EmbeddedId
    UserQuotesKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;

    @ManyToOne
    @MapsId("quoteId")
    @JoinColumn(name = "quote_id")
    @JsonIgnore
    Quote quote;

    @Column(nullable = false, columnDefinition = "Decimal(10,2)")
    double averagePrice = 0.0;

    @Column(nullable = false)
    int numberOfQuotes = 0;
}
