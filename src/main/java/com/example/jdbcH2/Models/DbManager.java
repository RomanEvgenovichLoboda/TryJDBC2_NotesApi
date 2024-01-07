package com.example.jdbcH2.Models;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
public class DbManager {
    private String host;     // server address
    private String user;     // user name
    private String pass;     // user password
    private String dbName;   // DB name
    private Connection conn; // connection object

    public DbManager(String host, String user, String pass, String dbName) {
        this.conn = null;
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.dbName = dbName;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver ");
//        } catch (ClassNotFoundException ex) {
//            System.err.println(ex.getMessage());
//        }
    }

    public Connection connect() {
        String url = "jdbc:h2:" + this.host + ":" + this.dbName;
        try {
            this.conn = DriverManager.getConnection(url, this.user, this.pass);
            return this.conn;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    public ResultSet getSelectQuery(String sql, Connection conn) {
        Statement comm = null;
        ResultSet set = null;
        try {
            comm = (Statement) conn.createStatement();
            set = comm.executeQuery(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return set;
    }
}
