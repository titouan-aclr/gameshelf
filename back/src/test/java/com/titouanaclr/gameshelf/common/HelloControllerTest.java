package com.titouanaclr.gameshelf.common;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerTest {

    @Autowired
    private MockMvc api;

    @Test
    void anyoneCanViewPublicEndpoint() throws Exception {
        api.perform(get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("Hello")));
    }

    @Test
    void notLoggedIn_shouldNotSeeSecuredEndpoint() throws Exception {
        api.perform(get("/secured"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void loggedIn_shouldSeeSecuredEndpoint() throws Exception {
        api.perform(get("/secured"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("User ID : 1")));
    }

    @Test
    void notLoggedIn_shouldNotSeeAdminEndpoint() throws Exception {
        api.perform(get("/admin"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void simpleUser_shouldNotSeeAdminEndpoint() throws Exception {
        api.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockAdmin
    void admin_shouldSeeAdminEndpoint() throws Exception {
        api.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("User id : 1")));
    }

}