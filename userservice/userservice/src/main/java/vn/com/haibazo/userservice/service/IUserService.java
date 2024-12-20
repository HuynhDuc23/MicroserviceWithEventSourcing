package vn.com.haibazo.userservice.service;

import org.reactivestreams.Publisher;
import vn.com.haibazo.userservice.data.User;
import vn.com.haibazo.userservice.model.UserDTO;

import java.util.List;

public interface IUserService {
    public List<User> getAllUser() ;
    public User saveUser(User user);
    public UserDTO login(String username , String password);
}
