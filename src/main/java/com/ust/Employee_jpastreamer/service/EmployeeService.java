package com.ust.Employee_jpastreamer.service;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.ust.Employee_jpastreamer.model.Employee;
import com.ust.Employee_jpastreamer.repository.Employeerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private Employeerepo employeerepo;

    private final JPAStreamer jpaStreamer;

    @Autowired
    public EmployeeService(final JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }


    public Map<String, List<Employee>> groupbyEmployeeByCity() {
        return jpaStreamer.stream(Employee.class)
                .collect(Collectors.groupingBy(Employee::getCity));
    }

    public List<Employee> groupbyEmployeeByPaymentTier() {
        return employeerepo.findAll() ;
    }

    public List<Employee> saveEmployee(List<Employee> employee) {
        return employeerepo.saveAll(employee);
    }

    public long getGenderCount(String gender) {
        return jpaStreamer.stream(Employee.class)
                .filter(e -> e.getGender().equalsIgnoreCase(gender))
                .count();
    }

    public List<Employee> getEmployeesByAgeRange(int minAge, int maxAge) {
        return jpaStreamer.stream(Employee.class)
                .filter(e -> {
                    int age = e.getAge();
                    return age >= minAge && age <= maxAge;
                })
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesByJoiningYear(int year) {
        return jpaStreamer.stream(Employee.class)
                .filter(e -> e.getJoiningYear() == year)
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getGenderCountByJoiningYear(int year) {
        return jpaStreamer.stream(Employee.class)
                .filter(e -> e.getJoiningYear() == year)
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.summingInt(e -> 1)));
    }

    public Map<String, List<Employee>> groupByEducation() {
        return jpaStreamer.stream(Employee.class)
                .collect(Collectors.groupingBy(Employee::getEducation));
    }

    public List<Employee> filterEmployees(int joiningYear, String gender, boolean experienced, String education) {
        return jpaStreamer.stream(Employee.class)
                .filter(e -> e.getJoiningYear() == joiningYear)
                .filter(e -> e.getGender().equalsIgnoreCase(gender))
                .filter(e -> (e.getExperienceInCurrentDomain() > 0) == experienced)
                .filter(e -> e.getEducation().equalsIgnoreCase(education))
                .collect(Collectors.toList());
    }
}
