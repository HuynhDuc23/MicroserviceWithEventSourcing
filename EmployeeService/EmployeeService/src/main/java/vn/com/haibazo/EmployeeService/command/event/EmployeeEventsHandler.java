package vn.com.haibazo.EmployeeService.command.event;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.DisallowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.haibazo.EmployeeService.command.data.Employee;
import vn.com.haibazo.EmployeeService.command.data.EmployeeRepository;

@Component
@Slf4j
public class EmployeeEventsHandler {
    @Autowired
    private EmployeeRepository employeeRepository;

    @EventHandler
    public void on (EmployeeCreatedEvent event){
        Employee employee = new Employee();
        BeanUtils.copyProperties(event, employee);
        this.employeeRepository.save(employee);
    }
    @EventHandler
    public void on (EmployeeUpdatedEvent event){
        Employee employee = this.employeeRepository.findById(event.getId()).orElse(null);
        if(employee!=null){
            employee.setFirstName(event.getFirstName());
            employee.setLastName(event.getLastName());
            employee.setKin(event.getKin());
            employee.setIsDisciplined(event.getIsDisciplined());
            this.employeeRepository.save(employee);
        }
    }
    @EventHandler
    @DisallowReplay
    public void on (EmployeeDeleteEvent event) throws Exception {
        try  {
            employeeRepository.findById(event.getId()).orElseThrow(()-> new Exception("Could not find employee"));
            this.employeeRepository.deleteById(event.getId());
        }catch(Exception ex){
            log.error("Error occurred while deleting employee: {}", ex.getMessage());
        }
    }
}
