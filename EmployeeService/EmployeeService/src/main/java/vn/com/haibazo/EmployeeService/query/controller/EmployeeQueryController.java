package vn.com.haibazo.EmployeeService.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.com.haibazo.EmployeeService.query.model.EmployeeResponseModel;
import vn.com.haibazo.EmployeeService.query.queries.GetAllEmployeeQuery;
import vn.com.haibazo.EmployeeService.query.queries.GetDetailEmployeeQuery;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "Employee Query")
public class EmployeeQueryController {
    @Autowired
    private QueryGateway queryGateway ;

    @Operation(
            summary = "Get all employees", // bản tóm tắt
            description = "Get all employees with optional disciplined filter", // Sự miêu tả
            responses = { // danh sách phản hồi
                    @ApiResponse(
                            description = "success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "unauthorized / valid token"
                    )
            }
    )
    @GetMapping
    public List<EmployeeResponseModel> getAllEmployee(@RequestParam(name = "isDisciplined" , required = false , defaultValue = "false") Boolean isDisciplined){
        List<EmployeeResponseModel> list = queryGateway.query(new GetAllEmployeeQuery(isDisciplined) , ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
        return list ;
    }
    @Operation(
            summary = "Get employee by id",
            description = "Get employee by id Microservice Provider",
            responses = { // danh sách phản hồi
                    @ApiResponse(
                            description = "success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "unauthorized / valid token"
                    )
            }
    )
    @GetMapping("/{employeeId}")
    public EmployeeResponseModel getDetailEmployee(@PathVariable("employeeId") String id){
        // join giup cho de lay ket qua tra ve
        return queryGateway.query(new GetDetailEmployeeQuery(id),ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
    }

}
