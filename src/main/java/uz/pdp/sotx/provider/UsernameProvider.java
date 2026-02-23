package uz.pdp.sotx.provider;

import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProvider;
import org.springframework.stereotype.Component;
import uz.pdp.sotx.AuthUserRepository;

import java.util.List;

@Component
public class UsernameProvider implements ValueProvider {
    private final AuthUserRepository authUserRepository;

    public UsernameProvider(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public List<CompletionProposal> complete(CompletionContext completionContext) {

        String word = completionContext.currentWord();
        return authUserRepository.findAll().stream()
                .filter(authUser -> authUser.getUsername().contains(word))
                .map(a -> {
                            return new CompletionProposal(a.getUsername());
                        }
                ).toList();


    }

}
