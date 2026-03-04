package uz.pdp.sotx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uz.pdp.sotx.TodoController;
import uz.pdp.sotx.mockData.MockData;
import uz.pdp.sotx.model.dto.TodoSaveDto;
import uz.pdp.sotx.servuce.TodoService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(classes = TodoController.class)
//@AutoConfigureMockMvc
@WebMvcTest(TodoController.class)
class TodoControllerTest {


    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper objectMapper;

    @MockitoBean
    private TodoService todoService;

    @SneakyThrows
    @Test
    void create() {

        objectMapper = new ObjectMapper();

        when(todoService.create(any(TodoSaveDto.class))).thenReturn(MockData.VALID_DTO);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MockData.VALID_CREATE_DTO)))
                .andExpect(status().isOk());

        resultActions.andExpect(jsonPath("$.title").value(MockData.VALID_DTO.getTitle()));


    }


}