package com.tracer85.shop.controller;

import com.tracer85.shop.entities.Employee;
import com.tracer85.shop.service.EmployeeService;
import com.tracer85.shop.service.UserExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = {"", "employee"})
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        //modelMap.put("employees",employeeService.findAll());
        //return findPaginated(1,model);
        return findPaginated(1, "id", "asc", model);
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

//    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
//        int pageSize = 5;
//
//        Page< Employee > page = employeeService.findPaged(pageNo, pageSize);
//        List< Employee > employees = page.getContent();
//        model.addAttribute("number", page.getNumber());
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//        model.addAttribute("employees", employees);
//        return "employee/index";
//    }

    @GetMapping("/employee/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employees_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Employee> listUsers = employeeService.listAll();

        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);

        excelExporter.export(response);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page < Employee > page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List < Employee > employees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("employees", employees);
        return "employee/index";
    }
}
