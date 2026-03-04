package uz.pdp.sotx.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.sotx.model.Todo;
import uz.pdp.sotx.model.dto.TodoDto;
import uz.pdp.sotx.model.dto.TodoSaveDto;

@Component
public class TodoMapper {
    public TodoDto toDto(Todo save) {
        return TodoDto.builder()
                .id(save.getId())
                .title(save.getTitle())
                .description(save.getDescription())
                .build();
    }

    public Todo fromDto(TodoSaveDto dto) {
        Todo todo = new Todo();
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(false);
        return todo;
    }
}
