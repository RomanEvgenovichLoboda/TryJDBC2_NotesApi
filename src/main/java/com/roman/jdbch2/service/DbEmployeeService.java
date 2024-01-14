package com.roman.jdbch2.service;

import com.roman.jdbch2.model.DbModel;
import com.roman.jdbch2.model.EmployeeModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbEmployeeService {
    DbModel dbModel;
    Connection conn;
    Statement statement;

    public DbEmployeeService() throws SQLException {
        dbModel = new DbModel("jdbc:h2:","mem", "sa", "password", "test-database");
        conn = connect();
        statement = conn.createStatement();
        createTable();
    }

    public void createTable() throws SQLException {
        statement.execute("DROP TABLE emlpoyees IF EXISTS");
        statement.execute("CREATE TABLE employees(id INTEGER PRIMARY KEY auto_increment, name VARCHAR(255), lastName VARCHAR(255), email VARCHAR(255))");
        System.out.println("Employees are Created");
        statement.execute("INSERT INTO employees(name,lastName,email) values ('Vania','Pupkin','pupkin@mail')");
        statement.execute("INSERT INTO employees(name,lastName,email) values ('Senia','Bubkin','bubkin@mail')");
    }

    public Iterable<EmployeeModel> selectAll() throws SQLException {
        String sel = "select * from employees";
        ResultSet set = getSelectQuery(sel, conn);
        List<EmployeeModel> list = new ArrayList<>();
        while (set.next()) {
            list.add(new EmployeeModel(set.getLong("id"), set.getString("name"), set.getString("lastName"), set.getString("email")));
        }
        return list;
    }

    public String insertOne(EmployeeModel employee) {
        try {
            statement.execute("INSERT INTO employees(name,lastName,email) values ('" + employee.getName() + "','" + employee.getLastName() + "','" + employee.getEmail() + "')");
            return "Ok";
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    public String updateEmail(EmployeeModel employee) {
        try {
            statement.execute("UPDATE employees SET email = '" + employee.getEmail() + "' WHERE name = '" + employee.getName() + "' AND lastName = '" + employee.getLastName() + "'");
            return employee.toString();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    public String deleteById(long id){
        try {
            statement.execute("DELETE FROM employees WHERE id = "+id);
            return "Deleed";
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    private ResultSet getSelectQuery(String sql, Connection conn) {
        Statement statement = null;
        ResultSet set = null;
        try {
            statement = (Statement) conn.createStatement();
            set = statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return set;
    }
    private  Connection connect() {
        String url = "jdbc:h2:" + dbModel.getHost() + ":" + dbModel.getDbName();
        try {
            this.conn = DriverManager.getConnection(url, dbModel.getUser(), dbModel.getPass());
            return this.conn;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
}
