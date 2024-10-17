package com.titouanaclr.gameshelf.game;

import com.titouanaclr.gameshelf.common.WithMockAdmin;
import com.titouanaclr.gameshelf.common.WithMockUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String gameJson = """
            {
                "name": "New Game",
                "description": "A new game description",
                "minPlayers": 2,
                "maxPlayers": 4,
                "yearPublished": 2024,
                "playingTime": 60
            }
        """;

    // POST /admin/games
    @Transactional
    @Test
    @WithMockAdmin
    void admin_canCreateGame() throws Exception {
        mockMvc.perform(post("/admin/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gameJson))
                .andExpect(status().isCreated());
    }

    @Transactional
    @Test
    @WithMockUser
    void simpleUser_cannotCreateGame() throws Exception {
        mockMvc.perform(post("/admin/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gameJson))
                .andExpect(status().isForbidden());
    }

    @Transactional
    @Test
    void notLoggedIn_cannotCreateGame() throws Exception {
        mockMvc.perform(post("/admin/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gameJson))
                .andExpect(status().isUnauthorized());
    }

    // GET /games
    @Test
    @WithMockUser
    void user_canGetAllGames() throws Exception {
        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(3))
                .andExpect(jsonPath("$.content[0].name").value("Cabanga!"));
    }

    @Test
    @WithMockUser
    void user_canGetGamesWithPagination() throws Exception {
        mockMvc.perform(get("/games?page=0&size=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(2))  // Paginated size of 2
                .andExpect(jsonPath("$.totalElements").value(3));   // Total 3 games in DB
    }

    // GET /games/{game-id}
    @Test
    @WithMockUser
    void user_canGetGameById() throws Exception {
        mockMvc.perform(get("/games/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Catan"))
                .andExpect(jsonPath("$.minPlayers").value(3))
                .andExpect(jsonPath("$.maxPlayers").value(4));
    }

    @Test
    @WithMockUser
    void user_receivesNotFoundForInvalidGameId() throws Exception {
        mockMvc.perform(get("/games/999"))
                .andExpect(status().isNotFound());
    }
}