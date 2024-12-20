package vn.com.haibazo.userservice.model;
public class UserDTO {
    private Long id ;
    private String username ;
    private String password ;
    private String employeeId ;
    private String token ;
    private String refreshToken ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserDTO(Long id, String refreshToken, String token, String employeeId, String password, String username) {
        this.id = id;
        this.refreshToken = refreshToken;
        this.token = token;
        this.employeeId = employeeId;
        this.password = password;
        this.username = username;
    }

    public UserDTO() {
    }
}
