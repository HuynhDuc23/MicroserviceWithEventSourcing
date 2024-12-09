package vn.com.haibazo.EmployeeService.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.haibazo.EmployeeService.command.aggregate.EmployeeAggregate;
import vn.com.haibazo.EmployeeService.command.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
public class RestoreController {
    @Autowired
    private EmployeeService employeeService ;
    @GetMapping("/restore/{id}")
    public void restore(@PathVariable String id){
        EmployeeAggregate employeeAggregate = this.employeeService.restoreProductToCreationState(id);
        System.out.println(employeeAggregate.toString());
    }

}
