package com.supinfo.jee.casino.game;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GameControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenPostGames_givenCorrectGambler_thenReturnValues() throws Exception {
        // Given
        String requestContent = Files.readString(Path.of("src", "test", "resources", "inputs", "correctGamblerGame.json"));

        // When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/games").contentType(MediaType.APPLICATION_JSON).content(requestContent));


        // Then
        String expectedContent = Files.readString(Path.of("src", "test", "resources", "expectations", "correctGamblerGame.json"));
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(expectedContent));

    }

    @Test
    public void whenPostGames_givenEmptyPseudo_thenError412() throws Exception {
        // Given
        String requestContent = "{}";

        // When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/games").contentType(MediaType.APPLICATION_JSON).content(requestContent));


        // Then
        String expectedContent = "Pseudo attribute should have a valid value !";
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is4xxClientError()).andExpect(MockMvcResultMatchers.status().is(HttpStatus.PRECONDITION_FAILED.value()))
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));

    }

    @Test
    public void whenPostGames_givenUnknownPseudo_thenError402() throws Exception {
        // Given
        String requestContent = "{\"pseudo\": \"unknown\"}";

        // When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/games").contentType(MediaType.APPLICATION_JSON).content(requestContent));


        // Then
        String expectedContent = "Please, pay to play ! Your balance is 0 €";
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is4xxClientError()).andExpect(MockMvcResultMatchers.status().is(HttpStatus.PAYMENT_REQUIRED.value()))
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));

    }
}

