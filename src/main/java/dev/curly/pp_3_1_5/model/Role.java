package dev.curly.pp_3_1_5.model;

import dev.curly.pp_3_1_5.dto.RoleDto;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    public static RoleDto toDto(Role role) {
        return new RoleDto(
                role.getId(),
                role.getName()
        );
    }

    public static Role fromDto(RoleDto dto) {
        var role = new Role();
        role.setId(dto.id());
        role.setName(dto.name());

        return role;
    }

    public static Role adminRole() {
        return new Role("admin");
    }

    public static Role userRole() {
        return new Role("user");
    }

    public Role() {
    }

    public Role(String name) {
        setName(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        name = name.toUpperCase();
        if (name.startsWith("ROLE_")) {
            this.name = name;
        } else {
            this.name = "ROLE_" + name;
        }
    }

    public String getName() {
        return name;
    }

    public String getSimpleName() {
        return name.substring(5);
    }

    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Role other = (Role) obj;

        return Objects.equals(name, other.name);
    }
}
