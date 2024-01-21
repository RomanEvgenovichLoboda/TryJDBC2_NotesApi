package com.roman.jdbch2.controller;

import com.roman.jdbch2.model.UserModel;
import com.roman.jdbch2.service.DbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController  {
    @Autowired
    DbUserService userService;
    @GetMapping(path = "/getAll")
    public ResponseEntity<Iterable<UserModel>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }
    @PostMapping(path = "/addOne")
    public ResponseEntity<String> addOne(@RequestBody UserModel user){
        return ResponseEntity.ok(userService.insertOne(user));
    }
    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<String> deleteById(@RequestParam long id){
        return ResponseEntity.ok(userService.DeleteById(id));
    }

}
