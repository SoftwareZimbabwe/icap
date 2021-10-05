package zw.co.timetracker.icap.dto;

import java.util.Date;

public class EmployeeRegistrationDto {
    
	private long id;	
	private String fullName;	
	private String dateOfBirth;	
	private String jobTitle;
	private String employeeNumber;
	private long immediateSupervisor;	
	private String email;
	private String password;
	
	public EmployeeRegistrationDto() {
		
	}
	
	public EmployeeRegistrationDto(String fullName, String dateOfBirth, String jobTitle, String employeeNumber, long immediateSupervisor, String email, String password) {
		super();
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.jobTitle = jobTitle;
		this.employeeNumber = employeeNumber;
		this.immediateSupervisor = immediateSupervisor;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFullName() {
		return this.fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDateOfBirth() {
		return this.dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getEmployeeNumber() {
		return this.employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public long getImmediateSupervisor() {
		return this.immediateSupervisor;
	}
	public void setImmediateSupervisor(long immediateSupervisor) {
		this.immediateSupervisor = immediateSupervisor;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
