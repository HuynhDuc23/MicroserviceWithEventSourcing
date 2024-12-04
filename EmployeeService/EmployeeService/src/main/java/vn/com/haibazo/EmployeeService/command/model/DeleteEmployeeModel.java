package vn.com.haibazo.EmployeeService.command.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteEmployeeModel {
    @NotBlank(message = "id mandatory")
    private String id ;
}
