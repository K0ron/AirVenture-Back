package com.airventure.airventureback.activity;

import jakarta.servlet.http.Cookie;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties"
)
public class ActivityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    public String getAuthenticationCookie() throws Exception {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("email", "john@doe.com");
        jsonUser.put("password", "johndoe");

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.post("/login")
                        .content(jsonUser.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                );

        MvcResult result = resultActions.andReturn();
        return result.getResponse().getCookie("token").getValue();
    }

    @Test
    public void testGetActivities() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/activities")
                        .cookie(new Cookie("token", getAuthenticationCookie()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    @Test
    public void testGetActivityById() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/activities/0")
                        .cookie(new Cookie("token", getAuthenticationCookie()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Parapente"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Le parapente se pratique avec une voile"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.duration").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").value("Pyrénées, France"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(170))
        ;
    }
}