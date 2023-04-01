package org.example.service.auth.authorization;

import lombok.NoArgsConstructor;
import org.example.service.auth.Authentication;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor
public class AuthorizationServiceImpl extends Authentication implements AuthorizationService {

    private static AuthorizationService aut;

    public static synchronized AuthorizationService getInstance() {
        if (aut == null)
            aut = new AuthorizationServiceImpl();
        return aut;
    }

    @Override
    public boolean selectRecordsFromRegistrationTable(String clientMessageRecieved) throws SQLException {
        String[] message = clientMessageRecieved.split(" ");
        String login = message[1];
        String password = md5Custom(message[2]);
        int flag = 0;
        String selectTableSQL = "SELECT login, password FROM registration";
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String loginTable = rs.getString("login");
                String passwordTable = rs.getString("password");
                if (login.equals(loginTable) && password.equals(passwordTable)) {
                    flag = 1;
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null)
                statement.close();

            if (dbConnection != null)
                dbConnection.close();
        }
        if (flag == 1) {
            System.out.println("Вы успешно авторизованы!!!");
            return true;
        } else
            return false;
    }
}
