package zw.co.timetracker.icap.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import  zw.co.timetracker.icap.dto.EmployeeRegistrationDto;
import zw.co.timetracker.icap.service.EmployeeService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private EmployeeService userService;

	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Date.class,     
							new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));   
   }

	public UserRegistrationController(EmployeeService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public EmployeeRegistrationDto userRegistrationDto() {
        return new EmployeeRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") EmployeeRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}