package com.example.reactive.reactivemongoexample.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.reactive.reactivemongoexample.model.Employee;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee,String>{
}
