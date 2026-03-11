package uz.pdp.sotx;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import uz.pdp.sotx.model.dto.TodoDto;
import uz.pdp.sotx.model.dto.TodoSaveDto;
import uz.pdp.sotx.servuce.TodoService;

import java.util.List;

@Controller
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

//    @SchemaMapping(typeName = "Query", value = "getAll")
    @QueryMapping("getAll")
    public List<TodoDto> getAll() {
        return service.getAll();

    }

    @SchemaMapping(typeName = "Query", value = "get")
    public TodoDto get(@Argument Long id) {
        return service.get(id);

    }

    @SchemaMapping(typeName = "Mutation", value = "create")
    public TodoDto create(@Argument TodoSaveDto dto) {
        return service.create(dto);
    }

    @SchemaMapping(typeName = "Mutation", value = "update")
    public TodoDto update(@Argument Long id, @Argument TodoSaveDto dto) {
        return service.update(id, dto);
    }

//    @SchemaMapping(typeName = "Mutation", value = "delete")
    @MutationMapping("delete")
    public Boolean delete(@Argument Long id) {
        service.delete(id);
        return true;
    }


}
