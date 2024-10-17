package com.titouanaclr.gameshelf.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.titouanaclr.gameshelf.controller.WithMockUser;
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
class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // GET /users/current/locations
    @Test
    @WithMockUser
    void testFindAllLocationForCurrentUser_Success() throws Exception {
        mockMvc.perform(get("/users/current/locations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("My bedroom"))
                .andExpect(jsonPath("$[0].description").value("Under my bed"));
    }

    @Test
    void testFindAllLocationForCurrentUser_Unauthorized() throws Exception {
        // Not authenticated
        mockMvc.perform(get("/users/current/locations"))
                .andExpect(status().isUnauthorized());
    }

    // POST /users/current/locations
    @Test
    @WithMockUser
    void testSaveLocationForCurrentUser_Success() throws Exception {
        LocationCreateRequest newLocation = new LocationCreateRequest("Garage", "In the corner");

        mockMvc.perform(post("/users/current/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newLocation)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNumber());
    }

    @Test
    @WithMockUser
    void testSaveLocationForCurrentUser_InvalidInput() throws Exception {
        LocationCreateRequest invalidLocation = new LocationCreateRequest("", ""); // Champs invalides

        mockMvc.perform(post("/users/current/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidLocation)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveLocationForCurrentUser_Unauthorized() throws Exception {
        // Not authenticated
        LocationCreateRequest newLocation = new LocationCreateRequest("Garage", "In the corner");

        mockMvc.perform(post("/users/current/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newLocation)))
                .andExpect(status().isUnauthorized());
    }

    // PUT /users/current/locations/{location-id}
    @Test
    @WithMockUser
    void testUpdateLocationForCurrentUser_Success() throws Exception {
        LocationUpdateRequest updateRequest = new LocationUpdateRequest(103, "Updated My bedroom", "Updated description");

        mockMvc.perform(put("/users/current/locations/{locationId}", 103)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Updated My bedroom"))
                .andExpect(jsonPath("$.description").value("Updated description"));
    }

    @Test
    @WithMockUser
    void testUpdateLocationForCurrentUser_NotFound() throws Exception {
        LocationUpdateRequest updateRequest = new LocationUpdateRequest(9999, "Updated My bedroom", "Updated description");

        mockMvc.perform(put("/users/current/locations/{locationId}", 9999)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void testUpdateLocationForCurrentUser_InvalidIdMismatch() throws Exception {
        // ID from path and body doesn't match
        LocationUpdateRequest updateRequest = new LocationUpdateRequest(102, "Updated My bedroom", "Updated description");

        mockMvc.perform(put("/users/current/locations/{locationId}", 103)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void testUpdateLocationForCurrentUser_InvalidInput() throws Exception {
        LocationUpdateRequest updateRequest = new LocationUpdateRequest(103, null, "Updated description");

        mockMvc.perform(put("/users/current/locations/{locationId}", 103)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void testUpdateLocationForCurrentUser_Forbidden() throws Exception {
        // Cannot update someone else's location
        LocationUpdateRequest updateRequest = new LocationUpdateRequest(102, "Forbidden Update", "You should not see this");

        mockMvc.perform(put("/users/current/locations/{locationId}", 102)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isForbidden());
    }

    @Test
    void testUpdateLocationForCurrentUser_Unauthorized() throws Exception {
        // Not authenticated
        LocationUpdateRequest updateRequest = new LocationUpdateRequest(103, "Unauthorized Update", "You should not see this");

        mockMvc.perform(put("/users/current/locations/{locationId}", 103)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isUnauthorized());
    }

    // DELETE /users/current/location/{location-id}
    @Test
    @WithMockUser
    void testDeleteLocationForCurrentUser_Success() throws Exception {
        mockMvc.perform(delete("/users/current/locations/{locationId}", 103))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    void testDeleteLocationForCurrentUser_NotFound() throws Exception {
        mockMvc.perform(delete("/users/current/locations/{locationId}", 9999))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void testDeleteLocationForCurrentUser_Forbidden() throws Exception {
        // Cannot delete someone else's location
        mockMvc.perform(delete("/users/current/locations/{locationId}", 102))
                .andExpect(status().isForbidden());
    }

    @Test
    void testDeleteLocationForCurrentUser_Unauthorized() throws Exception {
        // Not authenticated
        mockMvc.perform(delete("/users/current/locations/{locationId}", 103))
                .andExpect(status().isUnauthorized());
    }

}