package org.example.service.file;

import java.sql.SQLException;

public interface FilesService {
    boolean writeInFileForRegistration() throws SQLException;

    boolean writeInFileForProduct() throws SQLException;

    boolean writeInFileForOrders() throws SQLException;
}

