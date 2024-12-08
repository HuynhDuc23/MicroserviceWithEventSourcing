package vn.com.haibazo.userservice.service;
import com.auth0.jwt.JWT;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.haibazo.userservice.data.User;
import vn.com.haibazo.userservice.data.UserRepository;
import vn.com.haibazo.userservice.model.UserDTO;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class IUserServiceImpls implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder ;




    @Override
    public List<User> getAllUser() {
         return this.userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  this.userRepository.save(user);
    }

    @Override
    public UserDTO login(String username, String password) {
        User user = this.userRepository.findByUsername(username);
        UserDTO userDTO = new UserDTO() ;
        if(user!=null){
            BeanUtils.copyProperties(user,userDTO);
            if(passwordEncoder.matches(password,userDTO.getPassword())){
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) // 10 minutes expiration
                        .withIssuer("com.haibazo")
                        .sign(algorithm);

                String refreshToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000)) // 30 days expiration
                        .withIssuer("com.haibzo")
                        .sign(algorithm);

                userDTO.setToken(accessToken);
                userDTO.setRefreshToken(refreshToken);
                return userDTO;
            }
        }
        throw new RuntimeException("Invalid username or password");
    }


}
