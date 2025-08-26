package co.com.bancolombia.api.mapper;

import co.com.bancolombia.api.dto.RegisterUserDTO;
import co.com.bancolombia.api.dto.UserDTO;
import co.com.bancolombia.model.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserDTOMapper {
    public abstract UserDTO toResponse(User user);
    abstract User toModel(UserDTO userDTO);
    public abstract User toModel(RegisterUserDTO registerUserDTO);
}
