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
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/hal+json"))
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
    public void whenPostGames_givenIndebtedPseudo_thenError402() throws Exception {
        // Given
        String requestContent = "{\"pseudo\": \"indebted\", \"password\": \"password\"}";

        // When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/games").contentType(MediaType.APPLICATION_JSON).content(requestContent));


        // Then
        String expectedContent = Files.readString(Path.of("src", "test", "resources", "expectations", "paymentRequiredGame.json"));
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is4xxClientError()).andExpect(MockMvcResultMatchers.status().is(HttpStatus.PAYMENT_REQUIRED.value()))
                .andExpect(MockMvcResultMatchers.content().json(expectedContent));

    }

    @Test
    public void whenAuthenticates_givenWrongPassword_thenError403() throws Exception {
        // Given
        String requestContent = "{\"pseudo\": \"pigeon\", \"password\": \"wrongPassword\"}";

        // When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/authenticates").contentType(MediaType.APPLICATION_JSON).content(requestContent));


        // Then
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is4xxClientError()).andExpect(MockMvcResultMatchers.status().is(HttpStatus.FORBIDDEN.value()));

    }

    @Test
    public void whenAuthenticates_givenGoodPassword_then202() throws Exception {
        // Given
        String requestContent = "{\"pseudo\": \"pigeon\", \"password\": \"password\"}";

        // When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/authenticates").contentType(MediaType.APPLICATION_JSON).content(requestContent));


        // Then
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.status().is(HttpStatus.ACCEPTED.value()));

    }
}

