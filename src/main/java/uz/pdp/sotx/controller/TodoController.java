package uz.pdp.sotx.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sotx.model.dto.TodoDto;
import uz.pdp.sotx.model.dto.TodoSaveDto;
import uz.pdp.sotx.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public TodoDto get(@PathVariable Long id) {

        return service.get(id);
    }

    @GetMapping
    public List<TodoDto> getAll(@RequestParam Long userId) {
        return service.getAll(userId);
    }

    @PostMapping
    public void create(@Valid @RequestBody TodoSaveDto dto, @RequestParam Long userId) {
        service.create(dto, userId);
    }

    @PutMapping("/{id}/completed")
    public void completed(@PathVariable Long id) {
        service.completed(id);
    }


    @PutMapping("/{id}")
    public void update(@Valid @RequestBody TodoSaveDto dto, @PathVariable Long id) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @RequestParam Long userId) {

        service.delete(id, userId);
    }

}
