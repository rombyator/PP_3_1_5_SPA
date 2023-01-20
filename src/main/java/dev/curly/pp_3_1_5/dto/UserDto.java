package dev.curly.pp_3_1_5.dto;

import java.util.List;

public record UserDto(
        Long id,
        String firstName,
        String lastName,
        Integer age,
        String email,
        String password,
        List<RoleDto> roles
) {
}
