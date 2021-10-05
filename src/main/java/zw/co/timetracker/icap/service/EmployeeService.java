package zw.co.timetracker.icap.service;

import java.util.List;
import java.util.Optional;


import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import zw.co.timetracker.icap.model.Employee;
import zw.co.timetracker.icap.model.Role;
import zw.co.timetracker.icap.repository.EmployeeRepository;
import zw.co.timetracker.icap.dto.EmployeeRegistrationDto;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;
	}

	@Override
	public void deleteEmployeeById(long id) {
		this.employeeRepository.deleteById(id);
	}

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.employeeRepository.findAll(pageable);
	}

    /* User function*/    
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee save(EmployeeRegistrationDto registrationDto) {
		Employee employee = new Employee(
                registrationDto.getFullName(), 
				registrationDto.getDateOfBirth(),
                registrationDto.getJobTitle(),
                registrationDto.getEmployeeNumber(),
                registrationDto.getImmediateSupervisor(),
                registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()),
                Arrays.asList(new Role("ROLE_USER")));
		
		return employeeRepository.save(employee);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Employee employee = employeeRepository.findByEmail(username);

		if(employee == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
        
		return new org.springframework.security.core.userdetails.User(employee.getEmail(), employee.getPassword(), mapRolesToAuthorities(employee.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}