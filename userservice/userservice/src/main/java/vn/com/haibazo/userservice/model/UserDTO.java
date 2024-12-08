package vn.com.haibazo.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id ;
    private String username ;
    private String password ;
    private String employeeId ;
    private String token ;
    private String refreshToken ;
}
