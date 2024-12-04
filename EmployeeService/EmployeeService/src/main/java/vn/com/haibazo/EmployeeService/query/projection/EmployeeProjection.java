package vn.com.haibazo.EmployeeService.query.projection;

import io.swagger.v3.oas.annotations.Operation;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.haibazo.EmployeeService.command.data.Employee;
import vn.com.haibazo.EmployeeService.command.data.EmployeeRepository;
import vn.com.haibazo.EmployeeService.query.model.EmployeeResponseModel;
import vn.com.haibazo.EmployeeService.query.queries.GetAllEmployeeQuery;
import vn.com.haibazo.EmployeeService.query.queries.GetDetailEmployeeQuery;
import java.util.ArrayList;
import java.util.List;
@Component
public class EmployeeProjection {
    @Autowired
    private EmployeeRepository employeeRepository;

    @QueryHandler
    @Operation(
            summary = "Get all employees",
            description = "Get all employees by disciplined status"
    )
    public List<EmployeeResponseModel> handle(GetAllEmployeeQuery query){
         List<Employee> list = this.employeeRepository.findAllByIsDisciplined(query.getIsDisciplined());
         List<EmployeeResponseModel> results = new ArrayList<>() ;
         list.stream().forEach(item -> {
            EmployeeResponseModel model = new EmployeeResponseModel();
            BeanUtils.copyProperties(item, model);
            results.add(model);
        });
         return results;
    }
    @QueryHandler
    @Operation(
            summary = "Get employee detail",
            description = "Get employee detail by id"
    )
    public EmployeeResponseModel handle(GetDetailEmployeeQuery query) throws Exception {
         EmployeeResponseModel model = new EmployeeResponseModel();
         Employee employee = employeeRepository.findById(query.getId()).orElseThrow(()->  new Exception("Employee not found"));
         BeanUtils.copyProperties(employee,model);
         return model;
    }

}
