package zw.co.timetracker.icap.model;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
@Table(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "date_of_birth")    
    //@Temporal(TemporalType.DATE)
	private String dateOfBirth;
	
	@Column(name = "job_title")
	private String jobTitle;

	@Column(name = "employee_number")
	private String employeeNumber;

	@Column(name = "immediate_supervisor")
	private long immediateSupervisor;
	
	private String email;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
		            name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "role_id", referencedColumnName = "id"))
	
	private Collection<Role> roles;

	@OneToMany(mappedBy="employee")
    private Set<Timesheet> timesheets;
	
	public Employee() {
		
	}
	
	public Employee(String fullName, 
	String dateOfBirth,
					String jobTitle,
					String employeeNumber,
					long immediateSupervisor,
					String email,
					String password,
					Collection<Role> roles) {
		super();
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.jobTitle = jobTitle;
		this.employeeNumber = employeeNumber;
		this.immediateSupervisor = immediateSupervisor;
		this.email = email;
		this.password = password;
		this.roles = roles;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
}