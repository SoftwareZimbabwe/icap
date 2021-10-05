package zw.co.timetracker.icap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "timesheets")
public class Timesheet {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;

	@Column(name = "year_begins")
	private String yearBegins;
	
	@Column(name = "year_ends")
	private String yearEnds;

	/*@Column(name = "carry_over_vacation_days")
	private float carryOverVacationDays;*/
    
    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
	private Employee employee;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getYearBegins() {
		return yearBegins;
	}
	public void setYearBegins(String yearBegins) {
		this.yearBegins = yearBegins;
	}
	public String getYearEnds() {
		return yearEnds;
	}
	public void setYearEnds(String yearEnds) {
		this.yearEnds = yearEnds;
	}
	/*public float getCarryOverVacationDays() {
		return carryOverVacationDays;
	}
	public void setCarryOverVacationDays(float carryOverVacationDays) {
		this.carryOverVacationDays = carryOverVacationDays;
	}*/
}