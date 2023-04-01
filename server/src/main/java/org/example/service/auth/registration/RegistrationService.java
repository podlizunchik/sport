package org.example.service.auth.registration;

import java.sql.SQLException;

public interface RegistrationService {
    boolean insertRecordIntoRegistrationTable(String clientMessageRecieved) throws SQLException;
}
