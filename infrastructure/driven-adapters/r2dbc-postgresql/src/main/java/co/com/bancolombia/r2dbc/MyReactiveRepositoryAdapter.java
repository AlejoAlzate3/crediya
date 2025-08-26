package co.com.bancolombia.r2dbc;

import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserRepository;
import co.com.bancolombia.r2dbc.entity.UserEntity;
import co.com.bancolombia.r2dbc.excepcion.EmailExistsException;
import co.com.bancolombia.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MyReactiveRepositoryAdapter extends ReactiveAdapterOperations<User, UserEntity, Long, MyReactiveRepository> implements UserRepository {

    public MyReactiveRepositoryAdapter(MyReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, User.class));
    }

    @Override
    public Mono<User> save(User user) {
        return repository.save(toData(user)).map(this::toModel);
    }

    @Override
    public Mono<Boolean> existsByEmail(String email) {
        return repository.findByEmail(email)
                .map(u -> true)
                .defaultIfEmpty(false);
    }

    private User toModel(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .birthDate(entity.getBirthDate())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .salaryBase(entity.getSalaryBase())
                .build();
    }
}
