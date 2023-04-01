
package org.example.service.product;

import java.sql.SQLException;

public interface Products {
    String selectRecordFromProductTableForStatistics()throws SQLException;

    boolean insertRecordIntoProductTable(String clientMessageRecieved) throws SQLException;

    boolean deleteRecordFromProductTable(String clientMessageRecieved) throws SQLException;

    boolean changeRecordIntoProductTable(String clientMessageRecieved) throws SQLException;

    String selectRecordFromProductTable() throws SQLException;
}
