package com.example.springboothibernate.model;

import com.example.springboothibernate.model.enums.RolesType;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RolesType rolesType;

    public Role() {
    }

    public Role(RolesType rolesType) {
        this.rolesType = rolesType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RolesType getRolesType() {
        return rolesType;
    }

    public void setRolesType(RolesType rolesType) {
        this.rolesType = rolesType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && rolesType == role.rolesType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rolesType);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rolesType=" + rolesType +
                '}';
    }

    @Override
    public String getAuthority() {
        return rolesType.name();
    }
}
