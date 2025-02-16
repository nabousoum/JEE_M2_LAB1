package com.dev.web.controllers;


import com.dev.services.dto.RoleDto;
import com.dev.services.entities.Role;
import com.dev.services.services.RoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {
    private RoleService roleService;

    @GetMapping
    public List<RoleDto> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping("/{id}")
    public RoleDto getAppRoles(@PathVariable("id") int id) {
        return roleService.getRole(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public RoleDto createRoles(@Valid @RequestBody RoleDto roles) {
        return roleService.createRoles(roles);
    }

    @PutMapping("/{id}")
    public RoleDto updateRoles(@PathVariable("id") int id, @Valid @RequestBody RoleDto roles) {
        return roleService.updateRoles(id, roles);
    }

    @DeleteMapping("/{id}")
    public void deleteRoles(@PathVariable("id") int id) {
        roleService.deleteRoles(id);
    }
}