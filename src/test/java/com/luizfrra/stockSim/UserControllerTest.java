package com.luizfrra.stockSim;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizfrra.stockSim.DTOs.User.UserDTO;
import com.luizfrra.stockSim.EntitiesDomain.User.User;
import com.luizfrra.stockSim.Responses.ObjectResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.LinkedHashMap;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    public void givenUserService_whenSaveAndRetrieveEntity_thenOK() throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@test.com");
        userDTO.setPassword("test");
        userDTO.setFirstName("test");

        MvcResult mvcResultFromSave = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/user")
                        .content(objectMapper.writeValueAsBytes(userDTO))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        System.out.println(mvcResultFromSave.getResponse().getContentAsString());

        Assert.assertEquals(HttpStatus.CREATED.value(), mvcResultFromSave.getResponse().getStatus());

        ObjectResponse response = objectMapper.readValue(mvcResultFromSave.getResponse().getContentAsByteArray(),
                ObjectResponse.class);

        MvcResult mvcResultFromGetById = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/user/" + ((LinkedHashMap)response.data).get("id"))
                    .accept(MediaType.APPLICATION_JSON)
        ).andReturn();

        System.out.println(mvcResultFromGetById.getResponse().getContentAsString());

        Assert.assertEquals(HttpStatus.OK.value(), mvcResultFromGetById.getResponse().getStatus());
    }
}
