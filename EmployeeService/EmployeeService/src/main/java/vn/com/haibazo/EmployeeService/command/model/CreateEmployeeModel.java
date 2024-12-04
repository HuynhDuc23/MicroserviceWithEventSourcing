package vn.com.haibazo.EmployeeService.command.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeModel {
    @NotBlank(message = "First name is mandatory")
    private String firstName ;
    @NotBlank(message = "Last name is mandatory")
    private String lastName ;
    @NotBlank(message = "Kin is mandatory")
    private String kin ;
}
