package uz.pdp.sotx.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uz.pdp.sotx.model.AuthUser;
import uz.pdp.sotx.model.Todo;
import uz.pdp.sotx.model.dto.TodoDto;
import uz.pdp.sotx.model.dto.TodoSaveDto;
import uz.pdp.sotx.repository.AuthUserRepository;
import uz.pdp.sotx.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository repository;
    private final AuthUserRepository authUserRepository;


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
    @Cacheable(value = "todos")
    /// Map todos = ConcurentHasMap.  String , List<TodoDto>. put(#userId, result)
    public List<TodoDto> getAll(Long userId) {
        ArrayList<TodoDto> todos = repository.findAllByOwnerId(userId).stream().map(
                todo ->
                        TodoDto.builder()
                                .id(todo.getId())
                                .title(todo.getTitle())
                                .completed(todo.isCompleted())
                                .description(todo.getDescription())
                                .ownerId(todo.getOwner().getId())
                                .build()
        ).collect(Collectors.toCollection(ArrayList::new));
        Thread.sleep(1000);
        return todos;
    }

    @CachePut(value = "todos", key = "#userId")
    public List<TodoDto> create(TodoSaveDto dto, Long userId) {
        AuthUser authUser = authUserRepository.findById(userId).orElseThrow();

        Todo todo = new Todo();
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(false);
        todo.setOwner(authUser);
        repository.save(todo);

        return repository.findAllByOwnerId(userId).stream().map(
                t ->
                        TodoDto.builder()
                                .id(t.getId())
                                .title(t.getTitle())
                                .completed(t.isCompleted())
                                .description(t.getDescription())
                                .ownerId(t.getOwner().getId())
                                .build()
        ).collect(Collectors.toCollection(ArrayList::new));

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

    @CacheEvict(value = "todos", key = "#userId")
    public void delete(Long id, Long userId) {
        repository.deleteById(id);

    }

    public void completed(Long id) {
        Todo todo = repository.findById(id).orElseThrow();
        todo.setCompleted(true);
        repository.save(todo);
    }

}
