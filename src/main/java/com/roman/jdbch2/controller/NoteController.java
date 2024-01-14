package com.roman.jdbch2.controller;

import com.roman.jdbch2.model.NoteModel;
import com.roman.jdbch2.repository.NoteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(path = "/notes")
public class NoteController {
    NoteRepository repository = new NoteRepository();

    @GetMapping(path = "/getAll")
    public ResponseEntity<Iterable<NoteModel>> getAll() throws SQLException {
        return ResponseEntity.ok(repository.selectAll());
    }

    @GetMapping(path = "/getById")
    public ResponseEntity<NoteModel> getById(@RequestParam long id) throws SQLException {
        return ResponseEntity.ok(repository.selectById(id));
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<String> deleteById(@RequestParam long id) throws SQLException {
        return ResponseEntity.ok(repository.deleteById(id));
    }

    @PutMapping(path = "/updateById")
    public ResponseEntity<String> updateById(@RequestParam long id, @RequestParam String text) {
        return ResponseEntity.ok(repository.updateById(id, text));
    }

    @PostMapping(path = "/addNew")
    public ResponseEntity<String> addNew(@RequestBody NoteModel model) {
        return ResponseEntity.ok(repository.addNew(model));
    }
}
