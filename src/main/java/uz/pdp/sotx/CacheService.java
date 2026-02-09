package uz.pdp.sotx;

import org.springframework.stereotype.Service;
import uz.pdp.sotx.model.dto.TodoDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CacheService {

    private final Map<Long, List<TodoDto>> TODOS = new HashMap<>();

    public List<TodoDto> getTodos(Long userId) {
        return TODOS.get(userId);
    }

    public void putTodos(Long userId, List<TodoDto> todos) {
        TODOS.put(userId, todos);
    }

    public void clearSessionUserTodos() {
        Long sesUserId = 1L;
        TODOS.remove(sesUserId);
    }

    public void clearAll() {
        TODOS.clear();
    }
}
