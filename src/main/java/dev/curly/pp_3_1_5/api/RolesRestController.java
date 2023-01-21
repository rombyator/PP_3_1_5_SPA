package dev.curly.pp_3_1_5.api;

import dev.curly.pp_3_1_5.dto.RoleDTO;
import dev.curly.pp_3_1_5.service.RoleService;
import dev.curly.pp_3_1_5.utils.mapper.RoleMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RolesRestController {
    private final RoleService roleService;

    public RolesRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    List<RoleDTO> allRoles() {
        return roleService
                .getAll()
                .stream()
                .map(RoleMapper::toDTO)
                .toList();
    }
}
