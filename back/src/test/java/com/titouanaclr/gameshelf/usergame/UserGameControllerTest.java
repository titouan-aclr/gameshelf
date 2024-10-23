package com.titouanaclr.gameshelf.usergame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.titouanaclr.gameshelf.common.WithMockUser;
import com.titouanaclr.gameshelf.game.Game;
import com.titouanaclr.gameshelf.location.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserGameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserGameRepository userGameRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // GET /users/current/games
    @Test
    @WithMockUser
    void testFindAllUserGamesForCurrentUser_Success() throws Exception {
        mockMvc.perform(get("/users/current/games")
                        .param("page", "0")
                        .param("size", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.content[0].id").value(102))
                .andExpect(jsonPath("$.content[1].id").value(103))
                .andExpect(jsonPath("$.first").value(true))
                .andExpect(jsonPath("$.last").value(true));
    }

    @Test
    @WithMockUser
    void testFindAllUserGamesForCurrentUser_BadRequest() throws Exception {
        // Invalid pagination parameters
        mockMvc.perform(get("/users/current/games")
                        .param("page", "-1")
                        .param("size", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testFindAllUserGamesForCurrentUser_Unauthorized() throws Exception {
        // Not authenticated
        mockMvc.perform(get("/users/current/games")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    // POST /users/current/games
    @Test
    @WithMockUser
    void testSaveUserGameForCurrentUser_Success() throws Exception {
        UserGameCreateRequest request = new UserGameCreateRequest(Game.builder().id(103).build(), null);

        mockMvc.perform(post("/users/current/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber());

        mockMvc.perform(get("/users/current/games")
                        .param("page", "0")
                        .param("size", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(3)))
                .andExpect(jsonPath("$.totalElements").value(3));
    }

    @Test
    @WithMockUser
    void testSaveUserGameForCurrentUser_SuccessWithLocation() throws Exception {
        UserGameCreateRequest request = new UserGameCreateRequest(Game.builder().id(103).build(), Location.builder().id(103).build());

        mockMvc.perform(post("/users/current/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber());

        mockMvc.perform(get("/users/current/games")
                        .param("page", "0")
                        .param("size", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(3)))
                .andExpect(jsonPath("$.totalElements").value(3))
                .andExpect(jsonPath("$.content[2].location.id").value(103));
    }

    @Test
    @WithMockUser
    void testSaveUserGameForCurrentUser_InvalidInput() throws Exception {
        // Invalid request : game cannot be null
        UserGameCreateRequest request = new UserGameCreateRequest(null, null);

        mockMvc.perform(post("/users/current/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveUserGameForCurrentUser_Unauthorized() throws Exception {
        UserGameCreateRequest request = new UserGameCreateRequest(Game.builder().id(103).build(), null);
        // Not authenticated
        mockMvc.perform(post("/users/current/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }

    // DELETE /users/current/games/{user-game-id}
    @Test
    @WithMockUser
    void testDeleteUserGameForCurrentUser_Success() throws Exception {
        mockMvc.perform(delete("/users/current/games/{user-game-id}", 103)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Optional<UserGame> deletedGame = userGameRepository.findById(103);
        assertThat(deletedGame).isEmpty();
    }

    @Test
    @WithMockUser
    void testDeleteUserGameForCurrentUser_Forbidden() throws Exception {
        // Cannot delete someone else's usergame
        mockMvc.perform(delete("/users/current/games/{user-game-id}", 101)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void testDeleteUserGameForCurrentUser_NotFound() throws Exception {
        mockMvc.perform(delete("/users/current/games/{user-game-id}", 9999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("UserGame not found with ID 9999"));
    }

    @Test
    void testDeleteLocationForCurrentUser_Unauthorized() throws Exception {
        // Not authenticated
        mockMvc.perform(delete("/users/current/games/{user-game-id}", 103))
                .andExpect(status().isUnauthorized());
    }
}