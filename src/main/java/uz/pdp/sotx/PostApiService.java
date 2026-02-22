package uz.pdp.sotx;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.sotx.model.Post;
import uz.pdp.sotx.model.PostCreateDto;

import java.util.List;

@FeignClient(url = "https://jsonplaceholder.typicode.com/posts", name = "jsonplaceholder")
public interface PostApiService {

    @PostMapping
    ResponseEntity<Post> create(@RequestBody PostCreateDto dto);


    @GetMapping
    List<Post> getAll();
}
