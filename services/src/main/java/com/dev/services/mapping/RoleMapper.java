package com.dev.services.mapping;

import com.dev.services.dto.RoleDto;
import com.dev.services.entities.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {
    RoleDto toRolesDto(Role role);
    Role fromRoles(RoleDto roleDto);
}