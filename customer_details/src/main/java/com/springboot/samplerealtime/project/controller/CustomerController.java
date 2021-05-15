package com.springboot.samplerealtime.project.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.samplerealtime.project.model.Customer;
import com.springboot.samplerealtime.project.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findpaginated(1, "CustomerName", "asc", model);
	}

	@GetMapping("/showNewCustomerForm")
	public String showNewCustomerForm(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "new_customer";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer")Customer customer) {
		customerService.saveCustomer(customer);
		return "redirect:/" ;
		
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		Customer customer = customerService.getEmployeeById(id);
		model.addAttribute("customer", customer);
		return "update-employee";
	}
	
	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable(value = "id")long id) {
		this.customerService.deleteCustomerById(id);
		return "redirect:/";
	}

	@GetMapping("/page/{pageNo}")
	public String findpaginated(@PathVariable(value = "pageno") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
	int pageSize = 5;
		
	Page<Customer> page = customerService.findpaginated(pageNo,pageSize,sortField,sortDir);
	List<Customer> listCustomers = page.getContent();
	
	model.addAttribute("currentPage", page);
	model.addAttribute("totalPages", page.getTotalPages());
	model.addAttribute("totalItems", page.getTotalElements());
	
	model.addAttribute("sortField", sortField);
	model.addAttribute("sortDir", sortDir);
	model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
	
	model.addAttribute("listCustomers",listCustomers);
	
		return "index" ;
	}
}
