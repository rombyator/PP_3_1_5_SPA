package dev.curly.pp_3_1_5.utils.mapper;

import dev.curly.pp_3_1_5.dto.RoleDTO;
import dev.curly.pp_3_1_5.model.Role;

public class RoleMapper {
    public static RoleDTO toDTO(Role role) {
        return new RoleDTO(
                role.getId(),
                role.getName()
        );
    }

    public static Role fromDTO(RoleDTO dto) {
        var role = new Role();
        role.setId(dto.id());
        role.setName(dto.name());

        return role;
    }
}
