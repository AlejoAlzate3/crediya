package co.com.bancolombia.api;

import co.com.bancolombia.api.dto.RegisterUserDTO;
import co.com.bancolombia.api.mapper.UserDTOMapper;
import co.com.bancolombia.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
public class Handler {

    private  final UserUseCase userUseCase;
    private  final UserDTOMapper userDTOMapper;

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest request) {
        return request.bodyToMono(RegisterUserDTO.class)
                .map(userDTOMapper::toModel)
                .flatMap(userUseCase::registerUser)
                .map(userDTOMapper::toResponse)
                .flatMap(userDto -> ServerResponse.status(HttpStatus.CREATED).bodyValue(userDto))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                        .bodyValue(Map.of("error", e.getMessage())));
    }
}
