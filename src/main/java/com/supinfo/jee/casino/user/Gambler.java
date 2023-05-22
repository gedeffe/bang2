package com.supinfo.jee.casino.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Gambler {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pseudo;
    private long balance;
    private int bet;

    public Gambler(String pseudoParam, long balanceParam, int betParam) {
        this.pseudo = pseudoParam;
        this.balance = balanceParam;
        this.bet = betParam;
    }

}
