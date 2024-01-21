package com.roman.jdbch2.service;

import com.roman.jdbch2.model.DbModel;

import java.sql.*;

@SuppressWarnings("SqlNoDataSourceInspection")
public class DbNoteService {
    private DbModel dbModel;
    public Connection connection;
//    private Statement statement;

    public DbNoteService() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        this.dbModel = new DbModel("jdbc:h2:/", "tmp", "sa", "", "test-database");
//        setConnection();
//        statement = connection.createStatement();
        createTable();
    }

    private void setConnection() throws SQLException {
        this.connection = DriverManager.getConnection(dbModel.getUrl() + dbModel.getHost() + "/" + dbModel.getDbName(), dbModel.getUser(), dbModel.getPass());
//        statement = connection.createStatement();
    }

    private void createTable() {
        try {
            setConnection();
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS notes");
            statement.execute("CREATE TABLE IF NOT EXISTS notes(id INTEGER PRIMARY KEY auto_increment, author VARCHAR(50), text VARCHAR(255), datetime TIMESTAMP)");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO notes(author, text, datetime) VALUES(?, ?, ?)");
            preparedStatement.setString(1, "Roman");
            preparedStatement.setString(2, "Hello World");
            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
//            preparedStatement.setTimestamp(3,Timestamp.valueOf(LocalDateTime.now())); //second variant
            preparedStatement.executeUpdate();
            System.out.println("Notes Are Created");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public ResultSet getSelectQuery(String sql) {
        ResultSet set = null;
        try {
            setConnection();
            Statement statement = connection.createStatement();
            set = statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return set;
    }
    public String execute(String sql){
        try {
            setConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            return "OK";
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
