// package com.example.employee.controller;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// public class EmployeeController {

//     @GetMapping("/employees")
//     public String getEmployees() {
//         return "Employee API is working";
//     }
// }

// package com.example.employee.controller;

// import com.example.employee.model.Employee;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.Arrays;
// import java.util.List;

// @RestController
// public class EmployeeController {

//     @GetMapping("/employees")
//     public List<Employee> getEmployees() {

//         return Arrays.asList(
//                 new Employee(1, "Rahul", "IT"),
//                 new Employee(2, "Priya", "HR"),
//                 new Employee(3, "Amit", "Finance")
//         );
//     }
// }
// package com.example.employee.controller;

// import com.example.employee.model.Employee;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.Arrays;
// import java.util.List;

// @RestController
// public class EmployeeController {

//     @GetMapping("/employees")
//     public List<Employee> getEmployees() {

//         return Arrays.asList(
//                 new Employee(1, "Rahul", "IT"),
//                 new Employee(2, "Priya", "HR"),
//                 new Employee(3, "Amit", "Finance")
//         );
//     }

//     @GetMapping("/employees/{id}")
//     public Employee getEmployeeById(@PathVariable int id) {

//         return new Employee(id, "Rahul", "IT");
//     }
// }
package com.example.employee.controller;

import com.example.employee.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
public class EmployeeController {

    private List<Employee> employees = new ArrayList<>(
        Arrays.asList(
                new Employee(1, "Rahul", "IT"),
                new Employee(2, "Priya", "HR"),
                new Employee(3, "Amit", "Finance"),
                
                new Employee(4, "Suresh", "DevOps")
        )
);

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {

        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElse(null);
    }
    @PostMapping("/employees")
public Employee addEmployee(@RequestBody Employee employee) {

    employees.add(employee);
    return employee;
}
@PutMapping("/employees/{id}")
public Employee updateEmployee(@PathVariable int id,
                               @RequestBody Employee updatedEmployee) {

    for (int i = 0; i < employees.size(); i++) {

        if (employees.get(i).getId() == id) {
            employees.set(i, updatedEmployee);
            return updatedEmployee;
        }
    }

    return null;
}
@DeleteMapping("/employees/{id}")
public String deleteEmployee(@PathVariable int id) {

    boolean removed = employees.removeIf(employee -> employee.getId() == id);

    if (removed) {
        return "Employee deleted successfully";
    }

    return "Employee not found";
}
}