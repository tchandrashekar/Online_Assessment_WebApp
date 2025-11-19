
package com.example.Online_Assessment.Service.Impl;

import com.example.Online_Assessment.DTO.UserDto;
import com.example.Online_Assessment.Entity.User;
import com.example.Online_Assessment.Mapper.UserMapper;
import com.example.Online_Assessment.Repository.UserRepository;
import com.example.Online_Assessment.Service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    
    private final UserRepository userRepository;
    
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    
    @Override
    public UserDto createUser(UserDto dto){
        User user=UserMapper.toEntity(dto);
        User saved=userRepository.save(user);
        return UserMapper.toDto(saved);
    }
    
    @Override
    public UserDto getUserById(Long id){
        User user=userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not found"));
        return UserMapper.toDto(user);
    }
    
    @Override
    public List<UserDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public UserDto updateUser(Long id,UserDto dto){
        User user=userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        User updated=userRepository.save(user);
        return UserMapper.toDto(updated);
    }
    
    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    
}
