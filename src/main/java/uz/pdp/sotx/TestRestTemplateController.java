package uz.pdp.sotx;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TestRestTemplateController {

    private final PostApiService postApiService;

    public TestRestTemplateController(PostApiService postApiService) {
        this.postApiService = postApiService;
    }


    @PostMapping("/create")
    public Post testGetForObject(@RequestBody PostCreateDto dto) throws Exception {
        return postApiService.create(dto);
    }

    @GetMapping("/getAll")
    public List<Post> getAll() {
        return postApiService.getAll();
    }

    @PutMapping("/update")
    public List<Post> testGetForEntity() {
        return null;
    }


    @DeleteMapping("/delete/{id}")
    public Post testGetForObject(@PathVariable String id) {
        return null;
    }



    @GetMapping("/get/{id}")
    public Post testGetForEntity(@PathVariable String id) {
        return null;
    }


    // HttpClient - sinxron

    // RestTemplate - sinxron

    // WebClient - sinxron , asinxron -> (reactive)

    // FeignClient - sinxron default, asinxron -> (microservice)


}
