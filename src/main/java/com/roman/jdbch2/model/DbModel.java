package com.roman.jdbch2.model;

//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Connection;
public class DbModel {
    private String url;
    private String host;     // server address
    private String user;     // user name
    private String pass;     // user password
    private String dbName;   // DB name
//    private Connection conn; // connection object

    public DbModel(String url ,String host, String user, String pass, String dbName) {
//        this.conn = null;
        this.url = url;
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.dbName = dbName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    //    public Connection connect() {
//        String url = "jdbc:h2:" + this.host + ":" + this.dbName;
//        try {
//            this.conn = DriverManager.getConnection(url, this.user, this.pass);
//            return this.conn;
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//            return null;
//        }
//    }
}