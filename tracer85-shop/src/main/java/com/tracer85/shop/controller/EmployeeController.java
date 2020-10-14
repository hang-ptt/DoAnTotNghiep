package com.tracer85.shop.controller;

import com.tracer85.shop.entities.Employee;
import com.tracer85.shop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"", "employee"})
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap){
        modelMap.put("employees",employeeService.findAll());
        return "employee/index";
    }

    @RequestMapping(value = "create",method = RequestMethod.POST)
    public String create(HttpServletRequest request){
        Employee employee = new Employee();
        employee.setAddress(request.getParameter("address").trim());
        employee.setEmail(request.getParameter("email").trim());
        employee.setName(request.getParameter("name").trim());
        employee.setPhone(request.getParameter("phone").trim());
        employeeService.save(employee);
        return "redirect:/employee";
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public String delete(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        employeeService.delete(id);
        return "redirect:/employee";
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.find(id);
        employee.setEmail(request.getParameter("email").trim());
        employee.setName(request.getParameter("name").trim());
        employee.setPhone(request.getParameter("phone").trim());
        employee.setAddress(request.getParameter("address").trim());
        employeeService.save(employee);
        return "redirect:/employee";
    }

}
