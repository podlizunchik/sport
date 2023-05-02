package org.example.db;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MySqlDaoFactory implements DaoFactory {

    private String loginDB = "root";
    private String passwordDB = "lomako1234";
    private String url = "jdbc:mysql://localhost/registrationdb";
    private String DB = "registrationdb";
    protected Connection dbConnection = null;

    @Override
    public Connection getConnection() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(loginDB);
        dataSource.setPassword(passwordDB);
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName(DB);
        dataSource.setServerTimezone("UTC");
        try {
            dbConnection =  dataSource.getConnection();
        }
        catch (SQLException err) {
            err.printStackTrace();
        }
        return dbConnection;
    }
}
