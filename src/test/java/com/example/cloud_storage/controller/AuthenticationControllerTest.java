package com.example.cloud_storage.controller;

import com.example.cloud_storage.config.ConfigurationForAbs;
import com.example.cloud_storage.model.User;
import com.example.cloud_storage.model.request.AuthenticationRequest;
import com.example.cloud_storage.repository.UserRepository;
import com.example.cloud_storage.role.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
public class AuthenticationControllerTest extends ConfigurationForAbs {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    public static String authToken;

    @Test
    public void correctCredentials() throws Exception {

        User user = new User(1L, "bob",
                "$2a$10$yfOj9fqUxKAJdoA4rAcfS.WiBAR1BQze2kbA.15Zkw7wncYL.TVnG", Role.USER, null);
        userRepository.save(user);

        MvcResult mvcResult = this.mockMvc
                .perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(asJsonString(new AuthenticationRequest("bob", "1234")))
                        //                           .with(csrf())
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        authToken = mvcResult.getResponse().getContentAsString();
        authToken = "Bearer " + authToken.substring(15, authToken.length() - 2);
        System.out.println("---->>>>>" + authToken);
    }

    @Test
    public void badCredentials() throws Exception {
        this.mockMvc
                .perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(asJsonString(new AuthenticationRequest("noname", "simplepass")))
                        //      .with(csrf())
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


//    @Test
//    public void testtt() {
//
//    }

//    @Autowired
//    private WebApplicationContext context;
//
//    protected MockMvc mockMvc;
//
//    @Before
//    public void setup() {
//        this.mockMvc = MockMvcBuilders
//                .webAppContextSetup(this.context)
//                .apply(springSecurity())
//                .build();
//    }


//    @Autowired
//    protected ObjectMapper objectMapper;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Before
//    public void init() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
//
//    }
//    @Test
//    public void correctCredentials() throws Exception {
//        this.mockMvc
//                .perform(
//                    post("/login")
//                            .contentType(MediaType.APPLICATION_JSON_UTF8)
//                            .content(asJsonString(new AuthenticationRequest("bob", "1234")))
//                            .with(csrf())
//                )
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//    }

//    @Test
//    public void hello() throws Exception {
//        this.mockMvc.perform(get("http://localhost:8080/hello")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"login\": \"b@m.ru\", \"password\": \"1234\"}"))
//                .andExpect(status().is2xxSuccessful());
//    }
//
//    @Test
//    public void hi() throws Exception {
//        this.mockMvc.perform(get("http://localhost:8080"))
//                .andExpect(status().is2xxSuccessful());
//    }

//    @Test
//    public void contextLoad() {
//
//    }
}
