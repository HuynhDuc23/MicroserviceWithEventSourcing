package vn.com.haibazo.EmployeeService.query.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseModel {
    private String id ;
    private String firstName ;
    private String lastName ;
    private String Kin ;
    private Boolean isDisciplined;
}
