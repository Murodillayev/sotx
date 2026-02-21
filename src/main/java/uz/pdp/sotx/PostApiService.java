package uz.pdp.sotx;

import java.util.List;

public interface PostApiService {
    Post create(PostCreateDto dto);
    List<Post> getAll();
}
