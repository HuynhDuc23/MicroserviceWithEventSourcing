package vn.com.haibazo.EmployeeService.command.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.com.haibazo.EmployeeService.command.command.CreateEmployeeCommand;
import vn.com.haibazo.EmployeeService.command.command.DeleteEmployeeCommand;
import vn.com.haibazo.EmployeeService.command.model.CreateEmployeeModel;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "Employee Command")
public class EmployeeCommandController {
    @Autowired
    private CommandGateway commandGateway ;

    @PostMapping
    @Operation(
            summary = "Create a new employee",
            description = "Create a new employee with given details"
    )
    public String addEmployee(@RequestBody CreateEmployeeModel model){
        CreateEmployeeCommand command = new CreateEmployeeCommand();
        command.setId(UUID.randomUUID().toString());
        command.setFirstName(model.getFirstName());
        command.setLastName(model.getLastName());
        command.setKin(model.getKin());
        command.setIsDisciplined(false);
        return commandGateway.sendAndWait(command);
    }
    @Operation(
            summary = "Update an existing employee",
            description = "Update an existing employee with given details"
    )
    @PutMapping("/{employeeId}")
    public String updateEmployee(@PathVariable String employeeId, @RequestBody CreateEmployeeModel model){
        CreateEmployeeCommand command = new CreateEmployeeCommand();
        command.setId(employeeId);
        command.setFirstName(model.getFirstName());
        command.setLastName(model.getLastName());
        command.setKin(model.getKin());
        return commandGateway.sendAndWait(command);
    }
    @DeleteMapping("/{employeeID}")
    @Hidden
    @Operation(
            summary = "Delete an existing employee",
            description = "Delete an existing employee by ID"
    )
    public String deleteEmployee(@PathVariable("employeeID") String employeeId){
        return commandGateway.sendAndWait(new DeleteEmployeeCommand(employeeId));
    }

}
