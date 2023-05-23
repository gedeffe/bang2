package com.supinfo.jee.casino.gambler;

import com.supinfo.jee.casino.party.Party;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Gambler {

    @Id
    @GeneratedValue
    private Long id;
    private String pseudo;
    private long balance;
    private int bet;
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gambler")
    private List<Party> partyList = new ArrayList<>();

    public Gambler(String pseudoParam, long balanceParam, int betParam) {
        this.pseudo = pseudoParam;
        this.balance = balanceParam;
        this.bet = betParam;
    }

    public Gambler(String pseudoParam) {
        this(pseudoParam, 0, 0);
    }

}
