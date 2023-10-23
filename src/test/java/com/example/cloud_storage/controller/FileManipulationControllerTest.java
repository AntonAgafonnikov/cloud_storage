package com.example.cloud_storage.controller;

import com.example.cloud_storage.config.ConfigurationForAbs;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static com.example.cloud_storage.controller.AuthenticationControllerTest.authToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileManipulationControllerTest extends ConfigurationForAbs {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void a_postFile() throws Exception {

        byte[] fileContent = "wall.jpg".getBytes(StandardCharsets.UTF_8);
        MockMultipartFile filePart = new MockMultipartFile("file", "orig", null, fileContent);

        byte[] json = "{\"name\":\"wall.jpg\"}".getBytes(StandardCharsets.UTF_8);
        MockMultipartFile jsonPart = new MockMultipartFile("json", "json", "application/json", json);

        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/file").file(filePart).file(jsonPart)
                        .queryParam("filename", "wall.jpg")
                        .header("auth-token", authToken)
                )
                .andExpect(status().is2xxSuccessful());

        Thread.sleep(10_000);
    }

    @Test
    public void b_getListFilesAuthenticatedUser() throws Exception {
        this.mockMvc
                .perform(
                        get("/list")
                                .queryParam("limit", "3")
                                .header("auth-token", authToken)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void c_getFile() throws Exception {
        this.mockMvc
                .perform(
                        get("/file")
                                .queryParam("filename", "wall.jpg")
                                .header("auth-token", authToken)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void d_editFile() throws Exception {
        this.mockMvc
                .perform(
                        put("/file")
                                .queryParam("filename", "wall.jpg")
                                .header("auth-token", authToken)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content("{\"filename\": \"AnotherFilename.jpg\"}")
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void e_deleteFile() throws Exception {
        this.mockMvc
                .perform(
                        delete("/file")
                                .queryParam("filename", "AnotherFilename.jpg")
                                .header("auth-token", authToken)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}