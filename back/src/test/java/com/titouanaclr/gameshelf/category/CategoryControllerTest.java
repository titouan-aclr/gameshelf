package com.titouanaclr.gameshelf.category;

import com.titouanaclr.gameshelf.controller.WithMockAdmin;
import com.titouanaclr.gameshelf.controller.WithMockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository.save(new Category(101, "Economic", "Just be the richest by the end of the game.", null));
        categoryRepository.save(new Category(102, "Negotiation", "Be ready to negotiate", null));
        categoryRepository.save(new Category(103, "Card Game", "Game with different kind of cards", null));
    }

    // GET /categories
    @Test
    @WithMockUser
    void testFindAllCategories_Success() throws Exception {
        mockMvc.perform(get("/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(3))))
                .andExpect(jsonPath("$[0].name", is("Economic")))
                .andExpect(jsonPath("$[1].name", is("Negotiation")));
    }

    @Test
    void testFindAllCategories_Unauthorized() throws Exception {
        mockMvc.perform(get("/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    // POST /categories
    @Test
    @WithMockAdmin
    void testSaveCategory_Success() throws Exception {
        String newCategoryJson = """
            {
                "name": "Strategy",
                "description": "Games that require planning"
            }
        """;

        mockMvc.perform(post("/admin/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newCategoryJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(1)));

        mockMvc.perform(get("/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(6))))
                .andExpect(jsonPath("$[0].name", is("Strategy")));
    }

    @Test
    @WithMockAdmin
    void testSaveCategory_InvalidInput() throws Exception {
        String invalidCategoryJson = """
            {
                "name": "",
                "description": "Description missing name"
            }
        """;

        mockMvc.perform(post("/admin/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidCategoryJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveCategory_Unauthorized() throws Exception {
        String newCategoryJson = """
            {
                "name": "Adventure",
                "description": "Games about adventure"
            }
        """;

        mockMvc.perform(post("/admin/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newCategoryJson))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void testSaveCategory_Forbidden() throws Exception {
        String newCategoryJson = """
            {
                "name": "Adventure",
                "description": "Games about adventure"
            }
        """;

        mockMvc.perform(post("/admin/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newCategoryJson))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockAdmin
    void testUpdateCategory_Success() throws Exception {
        String updateCategoryJson = """
            {
                "id": 102,
                "name": "Negotiation",
                "description": "Updated description"
            }
        """;

        mockMvc.perform(put("/admin/categories/{id}", 102)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateCategoryJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Negotiation")))
                .andExpect(jsonPath("$.description", is("Updated description")));

        mockMvc.perform(get("/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].name", is("Negotiation")))
                .andExpect(jsonPath("$[1].description", is("Updated description")));
    }

    @Test
    @WithMockAdmin
    void testUpdateCategory_NotFound() throws Exception {
        String updateCategoryJson = """
            {
                "id": 9999,
                "name": "Unknown Category",
                "description": "This category does not exist"
            }
        """;

        mockMvc.perform(put("/admin/categories/{id}", 9999)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateCategoryJson))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockAdmin
    void testUpdateCategory_InvalidIdMismatch() throws Exception {
        String updateCategoryJson = """
            {
                "id": 102,
                "name": "Invalid Category",
                "description": "Mismatched ID"
            }
        """;

        mockMvc.perform(put("/admin/categories/{id}", 103)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateCategoryJson))
                .andExpect(status().isBadRequest());
    }

}