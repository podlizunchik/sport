package org.example.service.auth.authorization;

import java.sql.SQLException;

public interface AuthorizationService {
    boolean selectRecordsFromRegistrationTable(String str) throws SQLException;
}
