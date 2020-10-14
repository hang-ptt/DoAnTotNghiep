package com.tracer85.shop.restcontroller;

import com.tracer85.shop.entities.Employee;
import com.tracer85.shop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "api/employee")
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "find/{id}", method = RequestMethod.GET,produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> find(@PathVariable("id") int id){
        try{
            return new ResponseEntity<Employee>(employeeService.find(id),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
        }
    }



}
