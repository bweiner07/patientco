package com.patientco.bradhomework.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patientco.bradhomework.business.DefaultBusinessService;
import com.patientco.bradhomework.business.Splitinator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CsvWeb.class)
public class CsvWebTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DefaultBusinessService service;

    @Test
    void testRead() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        given(service.read(anyString(), anyString())).willReturn(getForTestRead());

        FileData data = FileData.builder().filePath("test").delimiter(",").build();
        this.mockMvc.perform(post("/v1/csv")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(data)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(getForTestRead())));
    }


    private List<Map<String, Object>> getForTestRead() {
        return List.of(Map.of("ID", 1, "Name", "Bob", "Age", 23, "Favorite Color", "Red"),
                Map.of("ID", 2, "Name", "Doe, John", "Age", 99, "Favorite Color", "\"Turquoise\""));
    }
}
