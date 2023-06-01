package com.supinfo.jee.casino.launches;

import com.supinfo.jee.casino.gambler.Gambler;
import com.supinfo.jee.casino.gambler.GamblerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext
public class LaunchesControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GamblerRepository gamblerRepository;

    @Test
    void whenPlay_givenEmptyPseudo_thenError412() throws Exception {
        // Given
        String requestContent = "{}";

        // When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/launches").contentType(MediaType.APPLICATION_JSON).content(requestContent));


        // Then
        String expectedContent = "Pseudo attribute should have a valid value !";
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is4xxClientError()).andExpect(MockMvcResultMatchers.status().is(HttpStatus.PRECONDITION_FAILED.value()))
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    void whenPlay_givenWrongBet_thenError412() throws Exception {
        // Given
        String requestContent = "{\"pseudo\": \"toto\"}";

        // When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/launches").contentType(MediaType.APPLICATION_JSON).content(requestContent));


        // Then
        String expectedContent = "Bet attribute should have a valid value !";
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is4xxClientError()).andExpect(MockMvcResultMatchers.status().is(HttpStatus.PRECONDITION_FAILED.value()))
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    public void whenPlay_givenLooser_thenError402() throws Exception {
        // Given
        String requestContent = Files.readString(Path.of("src", "test", "resources", "inputs", "looserLaunches.json"));

        // When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/launches").contentType(MediaType.APPLICATION_JSON).content(requestContent));


        // Then
        String expectedContent = Files.readString(Path.of("src", "test", "resources", "expectations", "paymentRequiredLaunches.json"));
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is4xxClientError()).andExpect(MockMvcResultMatchers.status().is(HttpStatus.PAYMENT_REQUIRED.value()))
                .andExpect(MockMvcResultMatchers.content().json(expectedContent));

    }

    @Test
    public void whenPlay_givenCorrectValues() throws Exception {
        // Given
        String requestContent = Files.readString(Path.of("src", "test", "resources", "inputs", "correctLaunches.json"));

        // When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/launches").contentType(MediaType.APPLICATION_JSON).content(requestContent));


        // Then
        String expectedContent = Files.readString(Path.of("src", "test", "resources", "expectations", "correctGamblerLaunches.json"));
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(expectedContent));

        String pseudo = "pigeon";

        Gambler gambler = this.gamblerRepository.findByPseudo(pseudo);

        Assertions.assertThat(gambler).isNotNull().hasFieldOrPropertyWithValue("pseudo", pseudo)
                .hasFieldOrPropertyWithValue("balance", 4985L);
    }
}
