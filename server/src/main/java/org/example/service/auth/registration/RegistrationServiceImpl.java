package org.example.service.auth.registration;

import lombok.NoArgsConstructor;
import org.example.service.auth.Authentication;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor
public class RegistrationServiceImpl extends Authentication implements RegistrationService {
    private static RegistrationService reg;

    public static synchronized RegistrationService getInstance() {
        if (reg == null)
            reg = new RegistrationServiceImpl();
        return reg;
    }

    @Override
    public boolean insertRecordIntoRegistrationTable(String clientMessageRecieved) throws SQLException {
        int flagOne = 0;
        int flagTwo = 0;
        String[] message = clientMessageRecieved.split(" ");
        String login = message[1];
        String password = md5Custom(message[2]);
        String email = message[3];
        String selectTableSQL = "SELECT * FROM registration";
        String insertTableSQL = "INSERT INTO registration"
                + "(login, password, email) " + "VALUES"
                + "('" + login + "'," + "'" + password + "'," + "'" + email + "');";
        RegistrationServiceImpl reg = new RegistrationServiceImpl();
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                reg.setLogin(rs.getString("login"));
                reg.setPassword(rs.getString("password"));
                reg.setEmail(rs.getString("email"));

                String loginTable = rs.getString("login");
                if (login.equals(loginTable)) {
                    flagOne = 1;
                    break;
                }
            }
            if (flagOne == 0) {
                System.out.println(insertTableSQL);
                statement.executeUpdate(insertTableSQL);
                System.out.println("Данные успешно добавлены!");
                flagTwo = 1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) statement.close();

            if (dbConnection != null) dbConnection.close();
        }

        return flagTwo != 0;
    }
}
