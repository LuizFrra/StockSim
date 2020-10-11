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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(value = AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(length = 100)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "Decimal(10,2) default 10000.00")
    @Setter(value = AccessLevel.NONE)
    private double cash = 10000.00;

    @Column(columnDefinition = "boolean default true")
    private boolean isActive = true;

    @OneToMany(mappedBy = "user")
    Set<UserQuotes> quotes;

    @Version
    private Integer version;

    //protected User() {}

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public double debitQuote(double value, int quantity) {
        double totalDebit = value * quantity;
        return cash - totalDebit;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

    public void clearCredentials() {
        this.password = "";
    }
}
