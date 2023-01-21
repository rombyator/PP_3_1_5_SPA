package dev.curly.pp_3_1_5.utils.mapper;

import dev.curly.pp_3_1_5.dto.UserDTO;
import dev.curly.pp_3_1_5.model.User;

public class UserMapper {
    public static User fromDTO(UserDTO dto) {
        var user = new User();
        if (dto.id() != null) {
            user.setId(dto.id());
        }
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setAge(dto.age());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setRoles(dto
                .roles()
                .stream()
                .map(RoleMapper::fromDTO)
                .toList()
        );

        return user;
    }

    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getAge(),
                user.getEmail(),
                "",
                user.getRoles()
                        .stream()
                        .map(RoleMapper::toDTO)
                        .toList()
        );
    }

}
