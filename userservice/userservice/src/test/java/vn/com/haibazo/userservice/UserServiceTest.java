package vn.com.haibazo.userservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import vn.com.haibazo.userservice.data.User;
import vn.com.haibazo.userservice.data.UserRepository;
import vn.com.haibazo.userservice.model.UserDTO;
import vn.com.haibazo.userservice.service.IUserServiceImpls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository ;
    @Mock
    private PasswordEncoder passwordEncoder ;

    @InjectMocks
    private IUserServiceImpls iUserServiceImpls ;

    private User user = new User(1L,"Test","123456","1D") ;
    private UserDTO userDTO = new UserDTO(1L,"Test","123456","token","refreshToken","1D") ;

    @BeforeEach
    public void setUp() {

        ReflectionTestUtils.setField(iUserServiceImpls,"userRepository",userRepository);
        ReflectionTestUtils.setField(iUserServiceImpls,"passwordEncoder",passwordEncoder);
         User user = new User() ;
         user.setId(1L);
         user.setUsername("Test");
         user.setPassword("123456");
         user.setEmployeeId("1D");

         UserDTO userDTO1 = new UserDTO();
         userDTO1.setId(1L);
         userDTO1.setUsername("Test");
         userDTO1.setPassword("123456");
         userDTO1.setToken("token");
         userDTO1.setRefreshToken("refreshToken");
         userDTO1.setEmployeeId("1D");


    }
    @Test
    public void getAllUser() {
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        List<User> tests = iUserServiceImpls.getAllUser();
        assertNotNull(users);
    }
    @Test
    public void saveUser() {
        when(passwordEncoder.encode(any())).thenReturn("123456");
        user.setPassword("123456");
        when(userRepository.save(user)).thenReturn(user);

        User userSave = iUserServiceImpls.saveUser(user);

        assertNotNull(userSave);
        assertEquals(user.getUsername(),userSave.getUsername());
        assertEquals(user.getPassword(),userSave.getPassword());
    }
    @Test
    public void loginSuccess(){
        when(userRepository.findByUsername(any())).thenReturn(user);
        when(passwordEncoder.matches(any(),any())).thenReturn(true);
        UserDTO userDTO1 = this.iUserServiceImpls.login(userDTO.getUsername(), userDTO.getPassword());
        assertNotNull(userDTO1);
        assertEquals(userDTO.getUsername(),userDTO1.getUsername());
        assertNotNull(userDTO1.getToken());
    }
    @Test
    public void loginFail() {
        // Giả lập hành vi của userRepository để trả về null khi tìm kiếm người dùng
        when(userRepository.findByUsername(any())).thenReturn(null);

        // Kiểm tra rằng phương thức login ném ra ngoại lệ RuntimeException khi không tìm thấy người dùng
        assertThrows(RuntimeException.class, () -> {
            this.iUserServiceImpls.login("nonExistentUser", "somePassword");
        });
    }
}
