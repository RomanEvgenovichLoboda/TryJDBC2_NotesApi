package SpringJDBC_Notes.TryJDBC2.Controllers;

import SpringJDBC_Notes.TryJDBC2.Exception.ResourceNotFoundException;
import SpringJDBC_Notes.TryJDBC2.Models.Employee;
import SpringJDBC_Notes.TryJDBC2.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping(path = "/add")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Employee> updateEmployee(@RequestParam long id, @RequestBody Employee employeeDetails) {
        Employee updateEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id = " + id));
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(updateEmployee);
        return ResponseEntity.ok(updateEmployee);
    }

        @GetMapping(path = "/getAll")
    public ResponseEntity<Iterable<Employee>> getAll(){
        return ResponseEntity.ok(employeeRepository.findAll());
    }
}
