package uz.pdp.sotx;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class PostApiServiceImpl implements PostApiService {

    private final WebClient webClient;
    private final AppProps appProps;

    public PostApiServiceImpl(WebClient webClient, AppProps appProps) {
        this.webClient = webClient;
        this.appProps = appProps;
    }

    // FLUX, MONO
    @Override
    public Post create(PostCreateDto dto) {
        Mono<Post> monoPost = webClient
                .post()
                .uri(appProps.getPostApi())
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(Post.class);

        return monoPost.block();
    }

    @Override
    public List<Post> getAll() {
        return webClient.get()
                .uri(appProps.getPostApi())
                .retrieve()
                .bodyToFlux(Post.class)
                .collectList()
                .block();
    }
}
