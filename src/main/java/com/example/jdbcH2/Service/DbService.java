package com.example.jdbcH2.Service;

import com.example.jdbcH2.Models.DbManager;
import com.example.jdbcH2.Models.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbService {
    DbManager dbManager;
    Connection conn;
    Statement statement;

    public DbService() throws SQLException {
        dbManager = new DbManager("mem", "sa", "password", "springdb");
        conn = dbManager.connect();
        statement = conn.createStatement();
        createTable();
    }

    public void createTable() throws SQLException {
        statement.execute("DROP TABLE emlpoyees IF EXISTS");
        statement.execute("CREATE TABLE employees(id INTEGER PRIMARY KEY auto_increment, name VARCHAR(255), lastName VARCHAR(255), email VARCHAR(255))");
        System.out.println("The Table is Created");
        statement.execute("INSERT INTO employees(name,lastName,email) values ('Vania','Pupkin','pupkin@mail')");
        statement.execute("INSERT INTO employees(name,lastName,email) values ('Senia','Bubkin','bubkin@mail')");
    }

    public Iterable<Employee> getAll() throws SQLException {
        String sel = "select * from employees";
        ResultSet set = dbManager.getSelectQuery(sel, conn);
        List<Employee> list = new ArrayList<>();
        while (set.next()) {
            list.add(new Employee(set.getLong("id"), set.getString("name"), set.getString("lastName"), set.getString("email")));
        }
        return list;
    }
}
