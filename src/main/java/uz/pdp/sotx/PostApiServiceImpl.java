package uz.pdp.sotx;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.BiConsumer;

@Component
public class PostApiServiceImpl implements PostApiService {
    private final RestTemplate restTemplate;

    public PostApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public List<Post> getAllByGetForObject() {
        Post[] postArr = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", Post[].class);
        return postArr == null ? Collections.emptyList() : Arrays.stream(postArr).toList();
    }


    @Override
    public List<Post> getAllByGetForEntity() {
        ResponseEntity<Post[]> forEntity = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", Post[].class);

        System.out.println(forEntity.getStatusCode());
        HttpHeaders headers = forEntity.getHeaders();
        headers.forEach((k, v) -> {
            System.out.println("key: " + k + " value: " + v);
        });
        return forEntity.getBody() == null ? Collections.emptyList() : Arrays.stream(forEntity.getBody()).toList();
    }

    @Override
    public Post getByGetForObject(String id) {
        return restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/{id}", Post.class, id);
    }

    @Override
    public Post getByGetForEntity(String id) {
        ResponseEntity<Post> forEntity = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/{id}", Post.class, id);
        return forEntity.getBody() == null ? null : forEntity.getBody();
    }

    @Override
    public Post postForObject(PostCreateDto dto) {

        return restTemplate.postForObject("https://jsonplaceholder.typicode.com/posts", dto, Post.class);

    }


}
