package uz.pdp.sotx;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sotx.model.dto.TodoDto;
import uz.pdp.sotx.model.dto.TodoSaveDto;
import uz.pdp.sotx.servuce.TodoService;

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
    public List<TodoDto> getAll() {

        return service.getAll();

    }
    @PostMapping
    public TodoDto create(@RequestBody TodoSaveDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}/completed")
    public void completed(@PathVariable Long id) {
        service.completed(id);
    }


    @PutMapping("/{id}")
    public void update(@Valid @RequestBody TodoSaveDto dto, @PathVariable Long id) {
        service.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
