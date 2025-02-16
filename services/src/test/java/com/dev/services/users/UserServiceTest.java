package com.dev.services.users;

import com.dev.services.dto.UserDto;
import com.dev.services.services.UserService;
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
class UserServiceTest {

    @Mock
    private UserService userService;

    @Test
    void getUsers() {
        when(userService.getUsers()).thenReturn(List.of(new UserDto()));
        List<UserDto> userList = userService.getUsers();

        Assertions.assertEquals(1, userList.size());
    }

    @Test
    void getUser() {
        when(userService.getUser(anyInt())).thenReturn(new UserDto());
        UserDto user = userService.getUser(1);

        Assertions.assertNotNull(user);
    }

    @Test
    void createUser() {
        UserDto user = new UserDto();
        user.setNom("John Doe");

        when(userService.createUser(any())).thenReturn(user);
        UserDto userSaved = userService.createUser(user);

        Assertions.assertNotNull(userSaved);
        Assertions.assertEquals("John Doe", userSaved.getNom());
    }

    @Test
    void updateUser() {
        UserDto user = new UserDto();
        user.setNom("Jane Doe");

        when(userService.updateUser(anyInt(), any())).thenReturn(user);
        UserDto userUpdated = userService.updateUser(2, user);

        Assertions.assertEquals("Jane Doe", userUpdated.getNom());
    }

    @Test
    void deleteUser() {
        doNothing().when(userService).deleteUser(isA(Integer.class));
        userService.deleteUser(5);

        verify(userService, times(1)).deleteUser(5);
    }
}
