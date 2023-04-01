package org.example.service.order;

import java.sql.SQLException;

public interface Orders {
    void insertRecordIntoOrderTable(String clientMessageRecieved) throws SQLException;

    String calculateOrder(String clientMessageRecieved) throws SQLException;

    void insertCostIntoOrders(String res, String clientMessageRecieved) throws SQLException;
}
