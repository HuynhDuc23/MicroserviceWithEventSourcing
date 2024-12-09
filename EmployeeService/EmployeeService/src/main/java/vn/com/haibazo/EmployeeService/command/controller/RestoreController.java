package vn.com.haibazo.EmployeeService.command.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.haibazo.EmployeeService.command.aggregate.EmployeeAggregate;
import vn.com.haibazo.EmployeeService.command.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "Restore Employee")
public class RestoreController {
    @Autowired
    private EmployeeService employeeService ;
    @GetMapping("/restore/{id}")
    @Operation(
            summary = "restore with id",
            description = "restore employee with given id"
    )
    public void restore(@PathVariable String id){
        EmployeeAggregate employeeAggregate = this.employeeService.restoreProductToCreationState(id);
        System.out.println(employeeAggregate.toString());
    }

}
