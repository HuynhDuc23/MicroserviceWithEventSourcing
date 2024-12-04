package vn.com.haibazo.EmployeeService.command.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {
    public List<Employee> findAllByIsDisciplined(Boolean isDisciplined);
}
