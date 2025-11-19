
package com.example.Online_Assessment.Mapper;

import com.example.Online_Assessment.DTO.UserDto;
import com.example.Online_Assessment.Entity.User;


public class UserMapper {
    
    public static User toEntity(UserDto dto){
        return User.builder().name(dto.getName())
                .email(dto.getEmail())
                .build();
    }
    
    public static UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
    
}
