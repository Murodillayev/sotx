package uz.pdp.sotx.servuce;

import org.springframework.stereotype.Service;
import uz.pdp.sotx.mapper.TodoMapper;
import uz.pdp.sotx.model.Todo;
import uz.pdp.sotx.model.dto.TodoDto;
import uz.pdp.sotx.model.dto.TodoSaveDto;
import uz.pdp.sotx.repository.TodoRepository;

import java.util.List;

@Service
public class TodoService {

    private final TodoMapper mapper;
    private final TodoRepository repository;

    public TodoService(TodoMapper mapper, TodoRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public TodoDto get(Long id) {
        Todo todo = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Todo not found")
        );

        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .completed(todo.isCompleted())
                .description(todo.getDescription())
                .build();
    }

    public List<TodoDto> getAll() {
        List<Todo> todos = repository.findAll();
        return todos.stream().map(
                todo ->
                        TodoDto.builder()
                                .id(todo.getId())
                                .title(todo.getTitle())
                                .completed(todo.isCompleted())
                                .description(todo.getDescription())
                                .build()
        ).toList();
    }

    public TodoDto create(TodoSaveDto dto) {
        Todo todo = mapper.fromDto(dto);

        if (dto == null) {
            throw new RuntimeException("dto is null");
        }

        if (dto.getTitle() == null) {
            throw new RuntimeException("Title is required");
        }
        Todo save = repository.save(todo);
        TodoDto dto1 = mapper.toDto(save);
        System.out.println(dto1);
        return dto1;
    }


    public void update(Long id, TodoSaveDto dto) {
        Todo todo = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Todo not found")
        );
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(false);
        repository.save(todo);

    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void completed(Long id) {
        Todo todo = repository.findById(id).orElseThrow();
        todo.setCompleted(true);
        repository.save(todo);
    }

}
