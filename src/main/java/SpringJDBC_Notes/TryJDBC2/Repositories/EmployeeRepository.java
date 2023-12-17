package SpringJDBC_Notes.TryJDBC2.Repositories;

import SpringJDBC_Notes.TryJDBC2.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // all crud database methods
}
