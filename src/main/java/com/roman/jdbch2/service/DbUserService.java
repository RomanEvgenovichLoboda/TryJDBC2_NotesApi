package com.roman.jdbch2.service;

import com.roman.jdbch2.controller.UserController;
import com.roman.jdbch2.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class DbUserService implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DbUserService.class);
    @Autowired
    JdbcTemplate jdbcTemplate;
    public Iterable<UserModel> getAll(){
        log.info("Select All");
        return jdbcTemplate.query("SELECT * FROM users",(rs,rowNum)-> new UserModel(rs.getLong("id"),rs.getString("login"),rs.getString("password"))).stream().toList();
    }
    public String insertOne(UserModel user){
        log.info("Insert One: "+ user.toString());
        jdbcTemplate.batchUpdate("INSERT INTO users(login,password) VALUES ('"+user.getLogin()+"','"+user.getPassword()+"')");
        return "Ok";
    }
    public String DeleteById(long id){
        log.info("Delete where id = "+id);
        jdbcTemplate.execute("DELETE FROM users WHERE id = "+ id);
        return "Ok";
    }
    @Override
    public void run(String... args) throws Exception {
        log.info("Creating table");
        jdbcTemplate.execute("DROP TABLE users IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE users(id SERIAL, login VARCHAR(20), password VARCHAR(20))");
        log.info("Insert users");
        jdbcTemplate.batchUpdate("INSERT INTO users(login, password) VALUES ('Roman','qwerty')");
        jdbcTemplate.batchUpdate("INSERT INTO users(login, password) VALUES ('Valik','12345')");
        log.info("Get users");
        jdbcTemplate.query("SELECT * FROM users",(rs,rowNum)-> new UserModel(rs.getLong("id"),rs.getString("login"),rs.getString("password"))).forEach(user->log.info(user.toString()));



    }
}
