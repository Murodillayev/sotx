package uz.pdp.sotx.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import uz.pdp.sotx.CacheService;
import uz.pdp.sotx.model.AuthUser;
import uz.pdp.sotx.model.Todo;
import uz.pdp.sotx.model.dto.TodoDto;
import uz.pdp.sotx.model.dto.TodoSaveDto;
import uz.pdp.sotx.repository.AuthUserRepository;
import uz.pdp.sotx.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final CacheService cache;
    private final TodoRepository repository;
    private final AuthUserRepository authUserRepository;

    public TodoService(CacheService cache, TodoRepository repository, AuthUserRepository authUserRepository) {
        this.cache = cache;
        this.repository = repository;
        this.authUserRepository = authUserRepository;
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

    @SneakyThrows
    public List<TodoDto> getAll() {
        Long sesUserId = 1L;
        List<TodoDto> cacheTodos = cache.getTodos(sesUserId);
        if (cacheTodos != null) {
            return cacheTodos;
        }

        ArrayList<TodoDto> todos = repository.findAll().stream().map(
                todo ->
                        TodoDto.builder()
                                .id(todo.getId())
                                .title(todo.getTitle())
                                .completed(todo.isCompleted())
                                .description(todo.getDescription())
                                .build()
        ).collect(Collectors.toCollection(ArrayList::new));
        Thread.sleep(3000);

        cache.putTodos(sesUserId, todos);
        return todos;
    }

    public void create(TodoSaveDto dto) {
        Long sessionUserId = 1L;
        AuthUser authUser = authUserRepository.findById(sessionUserId).orElseThrow();

        Todo todo = new Todo();
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(false);
        todo.setOwner(authUser);
        repository.save(todo);
        cache.clearSessionUserTodos();

    }

    public void update(Long id, TodoSaveDto dto) {
        Todo todo = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Todo not found")
        );
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(false);
        repository.save(todo);
        cache.clearSessionUserTodos();

    }

    public void delete(Long id) {

        repository.deleteById(id);

        CompletableFuture.runAsync(cache::clearSessionUserTodos);
    }

    public void completed(Long id) {
        Todo todo = repository.findById(id).orElseThrow();
        todo.setCompleted(true);
        repository.save(todo);
        cache.clearSessionUserTodos();
    }

}
