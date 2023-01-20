package dev.curly.pp_3_1_5.service;

import dev.curly.pp_3_1_5.dto.RoleDto;
import dev.curly.pp_3_1_5.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();

    Role add(Role role);
}
