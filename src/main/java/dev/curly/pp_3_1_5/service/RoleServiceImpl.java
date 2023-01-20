package dev.curly.pp_3_1_5.service;

import dev.curly.pp_3_1_5.dto.RoleDto;
import dev.curly.pp_3_1_5.model.Role;
import dev.curly.pp_3_1_5.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }
}
