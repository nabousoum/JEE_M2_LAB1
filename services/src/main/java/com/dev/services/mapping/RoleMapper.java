package com.dev.services.mapping;

import com.dev.services.dto.RoleDto;
import com.dev.services.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toRolesDto(Role role);
    Role fromRole(RoleDto roleDto);

}
