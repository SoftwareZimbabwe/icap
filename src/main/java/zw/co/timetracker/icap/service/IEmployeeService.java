package zw.co.timetracker.icap.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import zw.co.timetracker.icap.model.Employee;
import zw.co.timetracker.icap.dto.EmployeeRegistrationDto;

public interface IEmployeeService extends UserDetailsService {
	List<Employee> getAllEmployees();

	void saveEmployee(Employee employee);

	Employee getEmployeeById(long id);

	void deleteEmployeeById(long id);

	Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
	Employee save(EmployeeRegistrationDto employeeRegistrationDto);
    
}