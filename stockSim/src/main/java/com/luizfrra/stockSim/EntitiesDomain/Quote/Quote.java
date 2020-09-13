package com.luizfrra.stockSim.EntitiesDomain.Quote;

import com.luizfrra.stockSim.EntitiesDomain.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tbl_quotes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

    @Id
    private String symbol;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, columnDefinition = "DECIMAL (10, 2)")
    private double price;

    @Column(nullable = false)
    private Date updatedAt;

}
