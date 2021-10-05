package zw.co.timetracker.icap.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import zw.co.timetracker.icap.model.Employee;
import zw.co.timetracker.icap.model.Timesheet;
import zw.co.timetracker.icap.repository.EmployeeRepository;
import zw.co.timetracker.icap.service.EmployeeService;
import zw.co.timetracker.icap.service.IEmployeeService;
import zw.co.timetracker.icap.service.ITimesheetService;

@Controller
public class TimeSheetController {
	    
	@Autowired
	private ITimesheetService timesheetService;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// display list of timesheets
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "yearBegins", "asc", model);		
	}
	
	@GetMapping("/showNewTimesheetForm")
	public String showNewTimesheetForm(Model model) {
		// create model attribute to bind form data
		Timesheet timesheet = new Timesheet();
		model.addAttribute("timesheet", timesheet);
		return "new_timesheet";
	}
	
	@PostMapping("/saveTimesheet")
	public String saveTimesheet(@ModelAttribute("timesheet") Timesheet timesheet, Principal principal) {
		// save timesheet to database
        timesheet.setEmployee(employeeRepository.findByEmail(principal.getName()));

		timesheetService.saveTimesheet(timesheet);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get timesheet from the service
		Timesheet timesheet = timesheetService.getTimesheetById(id);
		
		// set timesheet as a model attribute to pre-populate the form
		model.addAttribute("timesheet", timesheet);
		return "update_timesheet";
	}
	
	@GetMapping("/deleteTimesheet/{id}")
	public String deleteTimesheet(@PathVariable (value = "id") long id) {
		
		// call delete timesheet method 
		this.timesheetService.deleteTimesheetById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Timesheet> page = timesheetService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Timesheet> listTimesheets = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listTimesheets", listTimesheets);
		return "index";
	}

}