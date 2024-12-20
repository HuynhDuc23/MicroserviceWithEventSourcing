package vn.com.haibazo.userservice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import vn.com.haibazo.userservice.controller.UserController;
import vn.com.haibazo.userservice.data.User;
import vn.com.haibazo.userservice.model.UserDTO;
import vn.com.haibazo.userservice.service.IUserService;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
public class UserControllerTest {
    @Mock
    private IUserService userService ;

    @InjectMocks
    private UserController userController ;

    private User user ;
    private UserDTO userDto = new UserDTO(1L,"duc","password","1","token","refresherToken");

    @BeforeEach
    public void setUp() {
        User user = new User(1L,"1","ductrantad23@gmail.com","1");
        ReflectionTestUtils.setField(userController,"userService",userService);
    }
    @Test
    public void getAllUser() {
        List<User> users = new ArrayList<>();
        users.add(user);
        // gia lap hanh vi
        when(userService.getAllUser()).thenReturn(users);

        Assertions.assertEquals(users,userController.getAllUser());
    }
    @Test
    public void addUser() {
        when(userService.saveUser(user)).thenReturn(user);
        Assertions.assertEquals(user,userController.addUser(user));
    }
    @Test
    public void login(){
        when(userService.login(userDto.getUsername(), userDto.getPassword())).thenReturn(userDto);
        Assertions.assertEquals(userDto,userController.login(userDto));
    }
}