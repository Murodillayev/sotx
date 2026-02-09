package uz.pdp.sotx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.sotx.model.Todo;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {


    List<Todo> findAllByOwnerId(Long userId);
}
