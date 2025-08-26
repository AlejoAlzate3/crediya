package co.com.bancolombia.usecase.user;

import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserRepository;
import co.com.bancolombia.r2dbc.excepcion.EmailExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase  {

    private final UserRepository userRepository;

    @Transactional
    public Mono<User> registerUser(User user){
        return userRepository.existsByEmail(user.getEmail())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new EmailExistsException("Email ya existente"));
                    } else {
                        return userRepository.save(user);
                    }
                });
    }
}
