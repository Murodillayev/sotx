package uz.pdp.sotx.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sotx.config.CustomUserDetails;
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
    public List<TodoDto> getAll(@AuthenticationPrincipal CustomUserDetails user) {

//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        User sessionUser = (User) securityContext.getAuthentication().getPrincipal();

        return service.getAll(user.getId());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TodoSaveDto dto, @AuthenticationPrincipal CustomUserDetails user) {
        service.create(dto, user.getId());

        return ResponseEntity.ok().build();
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
    public void delete(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails user) {

        service.delete(id, user.getId());
    }


    // Authorization: Basic <username:password> -> base64
}
