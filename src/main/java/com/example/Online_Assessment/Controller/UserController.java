
package com.example.Online_Assessment.Controller;

import com.example.Online_Assessment.DTO.UserDto;
import com.example.Online_Assessment.Service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;
    
    public UserController(UserService userService){
        this.userService=userService;
    }
    
    @PostMapping
    public UserDto createUser(@RequestBody UserDto dto){
        return userService.createUser(dto);
    }
    
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }
    
    @GetMapping
    public List<UserDto> getAll(){
        return userService.getAllUsers();
    }
    
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id,@RequestBody UserDto dto){
        return userService.updateUser(id,dto);
    }
    
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User Deleted Successfully";
    }
    
}
