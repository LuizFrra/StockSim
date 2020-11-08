package com.luizfrra.stockSim.EntitiesDomain.User;

import com.luizfrra.stockSim.EntitiesDomain.UserQuotes.UserQuotes;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tbl_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Setter(value = AccessLevel.NONE)
    private String id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(columnDefinition = "Decimal(10,2) default 10000.00")
    @Setter(value = AccessLevel.NONE)
    private double cash = 10000.00;

    @OneToMany(mappedBy = "user")
    Set<UserQuotes> quotes;

    @Version
    private Integer version;

    public User(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public double debitQuote(double value, int quantity) {
        double totalDebit = value * quantity;
        cash -= totalDebit;
        return cash;
    }

    public double creditQuote(double value, int quantity) {
        double earn = value * quantity;
        cash += earn;
        return cash;
    }
}
