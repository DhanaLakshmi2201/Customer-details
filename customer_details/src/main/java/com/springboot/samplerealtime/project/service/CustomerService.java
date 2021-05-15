package com.springboot.samplerealtime.project.service;


import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.samplerealtime.project.model.Customer;

public interface CustomerService {
	List<Customer> getAllCustomers();
	void saveCustomer(Customer customer);
	Customer getEmployeeById(long id);
	void deleteCustomerById(long id);
	Page<Customer> findpaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
