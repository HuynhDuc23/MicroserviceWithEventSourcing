package vn.com.haibazo.EmployeeService.command.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeModel {
    @NotBlank(message = "First name is mandatory")
    private String firstName ;
    @NotBlank(message = "Last name is mandatory")
    private String lastName ;
    @NotBlank(message = "Kin is mandatory")
    private String kin ;
    @NotBlank(message = "isDisciplined is mandatory")
    private Boolean isDisciplined ;

}
