package com.codeline.Salary.System.Controller;


import com.codeline.Salary.System.Models.Employee;
import com.codeline.Salary.System.RequestObjects.GetEmployeeRequestObject;
import com.codeline.Salary.System.ResponseObjects.GetEmployeeResponse;
import com.codeline.Salary.System.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping(value = "employee")
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("create")
    public void saveEmployee(@RequestBody GetEmployeeRequestObject employeeRequestObject) {
        createEmployee(employeeRequestObject);
    }

    @RequestMapping("get")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    //Path Variable
    @RequestMapping("get/{employeeId}")
    public GetEmployeeResponse createEmployee(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    //Delete Employee
    @RequestMapping("delete/{Id}")
    public void deleteEmployee (@PathVariable Long Id) {
        employeeService.deleteEmployeeById(Id);
    }


    public void createEmployee(GetEmployeeRequestObject employeeRequestObject) {

        Employee employee = new Employee();
        employee.setName(employeeRequestObject.getName());
        employee.setGender(employeeRequestObject.getGender());
        employee.setDesignation(employeeRequestObject.getDesignation());
        employee.setSalary(employeeRequestObject.getSalary());
        employee.setDepartment(employeeRequestObject.getDepartment());
        employee.setCompanyName(employeeRequestObject.getCompanyName());
        employee.setCreatedDate(new Date());
        employee.setIsActive(true);
        employeeService.saveEmployee(employee);
    }
    @RequestMapping(value = "getByDepartment")
    public List<Employee> getAllEmployeesByDepartment(@RequestParam String departmentName) {
        return employeeService.getEmployeesByDept(departmentName);
    }

}
