package com.titouanaclr.gameshelf.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.titouanaclr.gameshelf.common.WithMockAdmin;
import com.titouanaclr.gameshelf.common.WithMockUser;
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
    private ObjectMapper objectMapper;

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
        CategoryCreateRequest request = new CategoryCreateRequest("Strategy", "Games that require planning");

        mockMvc.perform(post("/admin/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
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
        CategoryCreateRequest request = new CategoryCreateRequest("", "Description missing name");

        mockMvc.perform(post("/admin/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }


    @Test
    @WithMockUser
    void testSaveCategory_Forbidden() throws Exception {
        CategoryCreateRequest request = new CategoryCreateRequest("Strategy", "Games that require planning");

        mockMvc.perform(post("/admin/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }
    @Test
    void testSaveCategory_Unauthorized() throws Exception {
        CategoryCreateRequest request = new CategoryCreateRequest("Strategy", "Games that require planning");

        mockMvc.perform(post("/admin/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }

    // PUT /admin/categories/{category-id}
    @Test
    @WithMockAdmin
    void testUpdateCategory_Success() throws Exception {
        CategoryUpdateRequest request = new CategoryUpdateRequest(102, "Negotiation", "Updated description");

        mockMvc.perform(put("/admin/categories/{id}", 102)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
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
        CategoryUpdateRequest request = new CategoryUpdateRequest(9999, "Unknown Category", "This category does not exist");

        mockMvc.perform(put("/admin/categories/{id}", 9999)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockAdmin
    void testUpdateCategory_InvalidInput() throws Exception {
        CategoryUpdateRequest request = new CategoryUpdateRequest(102, "Invalid Category", "");

        mockMvc.perform(put("/admin/categories/{id}", 102)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockAdmin
    void testUpdateCategory_InvalidIdMismatch() throws Exception {
        CategoryUpdateRequest request = new CategoryUpdateRequest(102, "Invalid Category", "Mismatched ID");

        mockMvc.perform(put("/admin/categories/{id}", 103)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void testUpdateCategory_Forbidden() throws Exception {
        CategoryUpdateRequest request = new CategoryUpdateRequest(102, "Strategy", "Games that require planning");

        mockMvc.perform(post("/admin/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    void testUpdateCategory_Unauthorized() throws Exception {
        CategoryUpdateRequest request = new CategoryUpdateRequest(102, "Strategy", "Games that require planning");

        mockMvc.perform(post("/admin/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }
}