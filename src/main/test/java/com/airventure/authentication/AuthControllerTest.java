package com.airventure.airventureback.authentication;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties"
)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegister() throws Exception {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("lastName", "brave");
        jsonUser.put("firstName", "goyave");
        jsonUser.put("password", "admin");
        jsonUser.put("email", "goyave@brave.com");
        jsonUser.put("role_id", 1);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/register")
                        .content(jsonUser.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("brave"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("goyave"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("goyave@brave.com"))
        ;
    }

    @Test
    public void testLogin() throws Exception {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("email", "john@doe.com");
        jsonUser.put("password", "johndoe");

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/login")
                        .content(jsonUser.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.cookie().exists("token"))
                .andReturn()
                ;
    }
 
/*    @Test
    public void testLoginWrongCredentials() throws Exception {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("email", "john@doe.com");
        jsonUser.put("password", "wrongpassword");

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/login")
                        .content(jsonUser.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andReturn()
                ;
    }*/
}