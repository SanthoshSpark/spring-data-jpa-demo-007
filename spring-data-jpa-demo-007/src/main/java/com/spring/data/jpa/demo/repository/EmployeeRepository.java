package com.spring.data.jpa.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.data.jpa.demo.model.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

}
