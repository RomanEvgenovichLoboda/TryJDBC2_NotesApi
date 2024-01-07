package SpringJDBC_Notes.TryJDBC2.Controllers;

import SpringJDBC_Notes.TryJDBC2.Models.Employee;
import SpringJDBC_Notes.TryJDBC2.Service.DbService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    DbService dbService;
    {
        try {
            dbService = new DbService();
        } catch (SQLException e) {
           System.err.println(e.getMessage());
        }
    }
    @GetMapping(path = "/getAll")
    public ResponseEntity<Iterable<Employee>> getAll(){
        try {
            return ResponseEntity.ok(dbService.getAll());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
