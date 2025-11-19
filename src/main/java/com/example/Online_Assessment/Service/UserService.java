
package com.example.Online_Assessment.Service;

import com.example.Online_Assessment.DTO.UserDto;
import java.util.List;

public interface UserService {
    
    UserDto createUser(UserDto dto);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long id,UserDto dto);
    void deleteUser(Long id);
    
}
