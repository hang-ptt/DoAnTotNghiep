package com.tracer85.shop.service;

import com.tracer85.shop.entities.Employee;

public interface EmployeeService {
    public Iterable<Employee> findAll();

    public Employee find(int id);

    public Employee save(Employee employee);

    public void delete(int id);
}
