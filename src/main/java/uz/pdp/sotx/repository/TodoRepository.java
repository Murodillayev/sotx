package uz.pdp.sotx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.sotx.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
