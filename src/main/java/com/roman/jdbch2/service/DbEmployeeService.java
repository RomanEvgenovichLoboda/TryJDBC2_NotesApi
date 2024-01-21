package com.roman.jdbch2.service;

import com.roman.jdbch2.model.DbModel;
import com.roman.jdbch2.model.EmployeeModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbEmployeeService {
    DbModel dbModel;
    Connection connection;
//    Statement statement;

    public DbEmployeeService(){
        dbModel = new DbModel("jdbc:h2:","mem", "sa", "password", "test-database");
//        setConnection();
//        statement = connection.createStatement();
        createTable();
    }

    public void createTable() {
        try {
            setConnection();
            Statement statement = this.connection.createStatement();
            statement.execute("DROP TABLE emlpoyees IF EXISTS");
            statement.execute("CREATE TABLE employees(id INTEGER PRIMARY KEY auto_increment, name VARCHAR(255), lastName VARCHAR(255), email VARCHAR(255))");
            System.out.println("Employees are Created");
            statement.execute("INSERT INTO employees(name,lastName,email) values ('Vania','Pupkin','pupkin@mail')");
            statement.execute("INSERT INTO employees(name,lastName,email) values ('Senia','Bubkin','bubkin@mail')");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                System.err.println(e.getMessage());
//            }
        }

    }

    public Iterable<EmployeeModel> selectAll() throws SQLException {
        setConnection();
        String sel = "select * from employees";
        ResultSet set = getSelectQuery(sel, this.connection);
        List<EmployeeModel> list = new ArrayList<>();
        while (set.next()) {
            list.add(new EmployeeModel(set.getLong("id"), set.getString("name"), set.getString("lastName"), set.getString("email")));
        }
        this.connection.close();
        return list;
    }

    public String insertOne(EmployeeModel employee) {
        try {
            setConnection();
            Statement statement = this.connection.createStatement();
            statement.execute("INSERT INTO employees(name,lastName,email) values ('" + employee.getName() + "','" + employee.getLastName() + "','" + employee.getEmail() + "')");
            this.connection.close();
            return "Ok";
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
               System.err.println(e.getMessage());
            }
        }
    }

    public String updateEmail(EmployeeModel employee) {
        try {
            setConnection();
            Statement statement = this.connection.createStatement();
            statement.execute("UPDATE employees SET email = '" + employee.getEmail() + "' WHERE name = '" + employee.getName() + "' AND lastName = '" + employee.getLastName() + "'");
            return employee.toString();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
        finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public String deleteById(long id){
        try {
            setConnection();
            Statement statement = this.connection.createStatement();
            statement.execute("DELETE FROM employees WHERE id = "+id);
            return "Deleed";
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private ResultSet getSelectQuery(String sql, Connection conn) {
        ResultSet set = null;
        try {
            Statement statement = this.connection.createStatement();
            set = statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return set;
    }
    private  void setConnection() {
        String url = dbModel.getUrl() + dbModel.getHost() + ":" + dbModel.getDbName();
        try {
            this.connection = DriverManager.getConnection(url, dbModel.getUser(), dbModel.getPass());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
