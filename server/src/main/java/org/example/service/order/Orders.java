package org.example.service.order;

import java.sql.SQLException;

public interface Orders {
    String selectRecordFromOrderTableForStatistics()throws SQLException;

    void insertRecordIntoOrderTable(String clientMessageRecieved) throws SQLException;

    String calculateOrder(String clientMessageRecieved) throws SQLException;

    void insertCostIntoOrders(String res, String clientMessageRecieved) throws SQLException;

    String selectRecordFromOrderTable() throws SQLException;

    boolean processOrders(String clientMessageRecieved) throws SQLException;
}
