package vn.com.haibazo.userservice.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.com.haibazo.userservice.data.User;
import vn.com.haibazo.userservice.model.UserDTO;
import vn.com.haibazo.userservice.service.IUserService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private IUserService userService ;

    @GetMapping("/listUser")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return this.userService.saveUser(user);
    }
    @PostMapping("/login")
    public UserDTO login(@RequestBody UserDTO dto){
        return this.userService.login(dto.getUsername(),dto.getPassword());
    }
}
