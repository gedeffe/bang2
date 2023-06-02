package com.supinfo.jee.casino.gambler;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GamblerManagerImplIntegrationTests {

    @Autowired
    private GamblerManager gamblerManager;

    @Test
    void whenGetGambler_givenExistingPseudo() {
        // Given
        String pseudo = "pigeon";

        // When
        Gambler gambler = this.gamblerManager.getGambler(pseudo);

        // Then
        Assertions.assertThat(gambler).isNotNull().hasFieldOrPropertyWithValue("pseudo", pseudo)
                .hasFieldOrPropertyWithValue("balance", 5000L).hasFieldOrPropertyWithValue("bet", 25);
    }

    @Test
    void whenGetGambler_givenUnknownPseudo() {
        // Given
        String pseudo = "unknown";

        // When + Then
        Assertions.assertThatThrownBy(() -> this.gamblerManager.getGambler(pseudo)).isInstanceOf(EmptyPseudoException.class);
    }
}
