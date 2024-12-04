package vn.com.haibazo.EmployeeService.command.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdatedEvent {
    private String id ;
    private String firstName ;
    private String lastName ;
    private String Kin ;
    private Boolean isDisciplined ;
}
