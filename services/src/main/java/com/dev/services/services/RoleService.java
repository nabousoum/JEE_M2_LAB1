package com.dev.services.services;

import com.dev.services.dao.IRoleRepository;
import com.dev.services.dto.RoleDto;
import com.dev.services.exception.EntityNotFoundException;
import com.dev.services.exception.RequestException;
import com.dev.services.mapping.RoleMapper;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@CacheConfig(cacheNames = "roles")
public class RoleService {
    private IRoleRepository iRoleRepository;
    private RoleMapper roleMapper;
    MessageSource messageSource;

    public RoleService(IRoleRepository iRoleRepository, RoleMapper roleMapper, MessageSource messageSource) {
        this.iRoleRepository = iRoleRepository;
        this.roleMapper = roleMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<RoleDto> getRoles() {
        return StreamSupport.stream(iRoleRepository.findAll().spliterator(), false)
                .map(roleMapper::toRolesDto)
                .collect(Collectors.toList());
    }

    @Cacheable(key = "#id")
    @Transactional(readOnly = true)
    public RoleDto getRole(int id) {
        return roleMapper.toRolesDto(iRoleRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("role.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Transactional
    public RoleDto createRoles(RoleDto roles) {
        return roleMapper.toRolesDto(iRoleRepository.save(roleMapper.fromRole(roles)));
    }

    @CachePut(key = "#id")
    @Transactional
    public RoleDto updateRoles(int id, RoleDto roles) {
        return iRoleRepository.findById(id)
                .map(entity -> {
                    roles.setId(id);
                    return roleMapper.toRolesDto(
                            iRoleRepository.save(roleMapper.fromRole(roles)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("role.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @CacheEvict(key = "#id")
    @Transactional
    public void deleteRoles(int id) {
        try {
            iRoleRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("role.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}