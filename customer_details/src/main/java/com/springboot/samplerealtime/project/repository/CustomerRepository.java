package com.springboot.samplerealtime.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.samplerealtime.project.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
