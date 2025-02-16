package com.dev.services.services;

import com.dev.services.dao.IUserRepository;
import com.dev.services.dto.UserDto;
import com.dev.services.entities.User;
import com.dev.services.exception.EntityNotFoundException;
import com.dev.services.exception.RequestException;
import com.dev.services.mapping.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@CacheConfig(cacheNames = "users")
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final MessageSource messageSource;

    @Transactional(readOnly = true)
    public List<UserDto> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Cacheable(key = "#id")
    @Transactional(readOnly = true)
    public UserDto getUser(int id) {
        return userMapper.toUserDto(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id}, Locale.getDefault()))));
    }

    @Transactional
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.fromUser(userDto);
        return userMapper.toUserDto(userRepository.save(user));
    }

    @CachePut(key = "#id")
    @Transactional
    public UserDto updateUser(int id, UserDto userDto) {
        return userRepository.findById(id)
                .map(entity -> {
                    userDto.setId(id);
                    return userMapper.toUserDto(userRepository.save(userMapper.fromUser(userDto)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id}, Locale.getDefault())));
    }

    @CacheEvict(key = "#id")
    @Transactional
    public void deleteUser(int id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("user.errordeletion", new Object[]{id}, Locale.getDefault()), HttpStatus.CONFLICT);
        }
    }
}
