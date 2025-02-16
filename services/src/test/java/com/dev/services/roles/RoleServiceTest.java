package com.dev.services.roles;


import com.dev.services.dto.RoleDto;
import com.dev.services.services.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleService roleService;

    @Test
    void getRoles() {

        when(roleService.getRoles()).thenReturn(List.of(new RoleDto()));
        List<RoleDto> appRolesList = roleService.getRoles();

        Assertions.assertEquals(1, appRolesList.size());
    }

    @Test
    void getRole() {
        when(roleService.getRole(anyInt())).thenReturn(new RoleDto());
        RoleDto roles = roleService.getRole(1);

        Assertions.assertNotNull(roles);
    }

    @Test
    void createRoles() {

        RoleDto roles = new RoleDto();
        roles.setNom("ROLE_USER");

        when(roleService.createRoles(any())).thenReturn(roles);
        RoleDto rolesSave = roleService.createRoles(roles);

        Assertions.assertNotNull(rolesSave);
        Assertions.assertEquals(roles.getNom(), rolesSave.getNom());
    }

    @Test
    void updateRoles() {
        RoleDto roles = new RoleDto();
        roles.setNom("ROLE_TECH");
        when(roleService.updateRoles(anyInt() ,any())).thenReturn(roles);
        RoleDto rolesSave = roleService.updateRoles(3, roles);

        Assertions.assertEquals("ROLE_TECH", rolesSave.getNom());

    }

    @Test
    void deleteRoles() {

        doNothing().when(roleService).deleteRoles(isA(Integer.class));
        roleService.deleteRoles(3);

        verify(roleService, times(1)).deleteRoles(3);
    }
}