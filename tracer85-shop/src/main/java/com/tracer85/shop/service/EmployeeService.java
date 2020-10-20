package com.tracer85.shop.service;

import com.tracer85.shop.entities.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    public Iterable<Employee> findAll();

    public Employee find(int id);

    public Employee save(Employee employee);

    public void delete(int id);

    public List<Employee> listAll();

    Page<Employee> findPaged(int pageNo, int pageSize);

    Page <Employee > findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
