package uz.pdp.sotx.mockData;

import uz.pdp.sotx.model.Todo;
import uz.pdp.sotx.model.dto.TodoDto;
import uz.pdp.sotx.model.dto.TodoSaveDto;

public class MockData {
    public static final TodoSaveDto VALID_CREATE_DTO = TodoSaveDto
            .builder()
            .description("Test description")
            .title("Test title")
            .build();

    public static final TodoSaveDto TITLE_IS_NULL_CREATE_DTO = TodoSaveDto
            .builder()
            .description("Test description")
            .build();

    public static final TodoDto VALID_DTO = TodoDto
            .builder()
            .id(1L)
            .completed(false)
            .description("Test description")
            .title("Test title")
            .build();

    public static final Todo VALID_TODO = new Todo(1L, "Test title", "Test description", false);
    public static final Todo TITLE_IS_NULL_TODO = new Todo(1L, null, "Test description", false);
}
