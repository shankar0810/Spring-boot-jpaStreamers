package com.ust.Employee_jpastreamer.controller;

import com.ust.Employee_jpastreamer.model.Employee;
import com.ust.Employee_jpastreamer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/groupByCity")
    public Map<String, List<Employee>> groupbyEmployeeByCity() {
        return employeeService.groupbyEmployeeByCity();
    }

    @PostMapping("/save")
    public List<Employee> saveEmployee(@RequestBody List<Employee> employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/findall")
    public List<Employee> groupbyEmployeeByPaymentTier(){
        return employeeService.groupbyEmployeeByPaymentTier();
    }

    @GetMapping("/genderCount")
    public long getGenderCount(@RequestParam String gender) {
        return employeeService.getGenderCount(gender);
    }

    @GetMapping("/employeesByAgeRange")
    public List<Employee> getEmployeesByAgeRange(@RequestParam int minAge, @RequestParam int maxAge) {
        return employeeService.getEmployeesByAgeRange(minAge, maxAge);
    }

    @GetMapping("/employeesByJoiningYear")
    public List<Employee> getEmployeesByJoiningYear(@RequestParam int year) {
        return employeeService.getEmployeesByJoiningYear(year);
    }

    @GetMapping("/genderCountByJoiningYear")
    public Map<String, Integer> getGenderCountByJoiningYear(@RequestParam int year) {
        return employeeService.getGenderCountByJoiningYear(year);
    }

    @GetMapping("/groupByEducation")
    public Map<String, List<Employee>> groupByEducation() {
        return employeeService.groupByEducation();
    }

    @GetMapping("/filterEmployees")
    public List<Employee> filterEmployees(@RequestParam int joiningYear,
                                          @RequestParam String gender,
                                          @RequestParam boolean experienced,
                                          @RequestParam String education) {
        return employeeService.filterEmployees(joiningYear, gender, experienced, education);
    }
}
