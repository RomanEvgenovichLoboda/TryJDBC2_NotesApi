package com.roman.jdbch2.controller;

import com.roman.jdbch2.model.EmployeeModel;
import com.roman.jdbch2.service.DbService;
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
    public ResponseEntity<Iterable<EmployeeModel>> getAll() {
        try {
            return ResponseEntity.ok(dbService.selectAll());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addOne(@RequestBody EmployeeModel employee) {
        return ResponseEntity.ok(dbService.insertOne(employee));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<String> updateEmail(@RequestBody EmployeeModel employee) {
        return ResponseEntity.ok(dbService.updateEmail(employee));
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<String> deleteById(long id) {
        return ResponseEntity.ok(dbService.deleteById(id));
    }
}
