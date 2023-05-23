package com.supinfo.jee.casino.party;

import com.supinfo.jee.casino.gambler.Gambler;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Party {
    @Id
    @GeneratedValue
    private Long id;
    private int initialValue;
    private int diceThrowCounter;
    @ManyToOne
    @JoinColumn(name = "GAMBLER_ID", nullable = false)
    private Gambler gambler;

}
