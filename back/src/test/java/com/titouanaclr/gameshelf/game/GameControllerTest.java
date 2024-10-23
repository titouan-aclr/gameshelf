package com.titouanaclr.gameshelf.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.titouanaclr.gameshelf.category.Category;
import com.titouanaclr.gameshelf.common.WithMockAdmin;
import com.titouanaclr.gameshelf.common.WithMockUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // GET /games
    @Test
    @WithMockUser
    void testFindAllGames_Success() throws Exception {
        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(3))
                .andExpect(jsonPath("$.content[0].name").value("Cabanga!"));
    }

    @Test
    @WithMockUser
    void testFindAllGames_SuccessWithPagination() throws Exception {
        mockMvc.perform(get("/games")
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.totalElements").value(3));
    }

    @Test
    @WithMockUser
    void testFindAllGames_BadRequest() throws Exception {
        mockMvc.perform(get("/games")
                        .param("page", "-1")
                        .param("size", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testFindAllGames_Unauthorized() throws Exception {
        mockMvc.perform(get("/games")
                        .param("page", "0")
                        .param("size", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    // GET /games/{game-id}
    @Test
    @WithMockUser
    void testFindGameById_Success() throws Exception {
        mockMvc.perform(get("/games/{game-id}", 101))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Catan"))
                .andExpect(jsonPath("$.minPlayers").value(3))
                .andExpect(jsonPath("$.maxPlayers").value(4));
    }

    @Test
    @WithMockUser
    void testFindGameById_NotFound() throws Exception {
        mockMvc.perform(get("/games/{game-id}", 9999))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("No game found with ID 9999"));
    }

    @Test
    void testFindGameById_Unauthorized() throws Exception {
        mockMvc.perform(get("/games/{game-id}", 101))
                .andExpect(status().isUnauthorized());
    }

    // POST /admin/games
    @Transactional
    @Test
    @WithMockAdmin
    void testSaveGame_Success() throws Exception {
        GameCreateRequest request = new GameCreateRequest("New Game", "A new game description", 2, 4, 2024, 60, null, null, null);

        mockMvc.perform(post("/admin/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isNumber());
    }

    @Transactional
    @Test
    @WithMockAdmin
    void testSaveGame_SuccessWithCategories() throws Exception {
        GameCreateRequest request = new GameCreateRequest("New Game", "A new game description", 2, 4, 2024, 60, null, null, List.of(Category.builder().id(102).build(), Category.builder().id(105).build()));

        MvcResult result = mockMvc.perform(post("/admin/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isNumber())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        int gameId = JsonPath.parse(jsonResponse).read("$", Integer.class);

        mockMvc.perform(get("/games/{game-id}", gameId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Game"))
                .andExpect(jsonPath("$.categories").isArray())
                .andExpect(jsonPath("$.categories.length()").value(2));
    }

    @Test
    @WithMockAdmin
    void testSaveGame_InvalidInput() throws Exception {
        GameCreateRequest request = new GameCreateRequest("", null, 2, 4, 0, 60, null, null, null);

        mockMvc.perform(post("/admin/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void testSaveGame_Forbidden() throws Exception {
        GameCreateRequest request = new GameCreateRequest("New Game", "A new game description", 2, 4, 2024, 60, null, null, null);

        mockMvc.perform(post("/admin/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    void testSaveGame_Unauthorized() throws Exception {
        GameCreateRequest request = new GameCreateRequest("New Game", "A new game description", 2, 4, 2024, 60, null, null, null);

        mockMvc.perform(post("/admin/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }
}
