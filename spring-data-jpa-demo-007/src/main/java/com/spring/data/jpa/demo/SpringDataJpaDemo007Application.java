package com.spring.data.jpa.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.spring.data.jpa.demo.model.Employee;
import com.github.javafaker.Faker;
import com.spring.data.jpa.demo.model.Address;
import com.spring.data.jpa.demo.repository.EmployeeRepository;

// paging

@SpringBootApplication
public class SpringDataJpaDemo007Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaDemo007Application.class, args);
	}

	@Autowired
	private EmployeeRepository employeeRepository;
	
	//@Autowired
	private Faker facker = new Faker();
	
	@Override
	public void run(String... args) throws Exception {
		
		Stream<Employee> employees = IntStream.rangeClosed(1, 100)
				.mapToObj(i -> new Employee(
						facker.name().firstName(),
						facker.name().lastName(),
						facker.phoneNumber().cellPhone(),
						facker.internet().emailAddress(),
						new Address(
								facker.address().streetAddress(),
								facker.address().city(),
								facker.address().state(),
								facker.address().zipCode()
						)
		));
		

		List<Employee> employeeList=new ArrayList<>();
		employees.forEach(emp -> { 
			Employee e = (Employee)emp;
			employeeList.add(e);
		});
		
		
		employeeRepository.saveAll(employeeList);
		
		int i=0;
		
		while(i<=9) {
			System.out.println("\n\nThis page: " + (i + 1));
			int pageNo = i++; // always page no starts with zero
	        int pageSize = 10;
	
	        // create pageable object
	        Pageable pageable = PageRequest.of(pageNo, pageSize);
	
	        // findAll method and pass pageable instance
	        Page<Employee> page = employeeRepository.findAll(pageable);
	
	        List<Employee> employeesPage = page.getContent();
	
	        employeesPage.forEach((e) ->{
	            System.out.println(e);
	        });
	
	        // total pages
	        int totalPage = page.getTotalPages();
	        // total elements
	        long totalElements = page.getTotalElements();
	        // number of elements
	        int numberOfElements = page.getNumberOfElements();
	        // size
	        int size = page.getSize();
	
	        // last
	        boolean isLast = page.isLast();
	        // first
	        boolean isFirst = page.isFirst();
	        
	        System.out.println("total page -> " + totalPage);
	        System.out.println("totalElements -> " + totalElements);
	        System.out.println("numberOfElements -> " + numberOfElements);
	        System.out.println(" size ->" + size);
	        System.out.println(" isLast -> " + isLast);
	        System.out.println(" isFirst -> " + isFirst);
			
		}
	}

}
