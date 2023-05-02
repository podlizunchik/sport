package org.example.service.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.db.MySqlDaoFactory;
import org.example.entity.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Getter
@Setter
@AllArgsConstructor
public class OrdersImpl extends MySqlDaoFactory implements Orders {

    private static OrdersImpl ordersImpl;
    private static Order order = new Order();

    public static synchronized OrdersImpl getInstance() {
        if (ordersImpl == null) {
            ordersImpl = new OrdersImpl();
        }
        return ordersImpl;
    }

    private static Connection dbConnection = null;
    private static Statement statement = null;

    @Override
    public String selectRecordFromOrderTableForStatistics() throws SQLException {
        String res = "";
        int countProt = 0;
        int countCrea = 0;
        int countAmin = 0;
        int countBCAA = 0;
        int countJiro = 0;
        int countGein = 0;
        int countEner = 0;
        int countVita = 0;
        String selectTableSQL = "SELECT type FROM orders";
        try (Connection dbConnection = getConnection(); Statement statement = dbConnection.createStatement()) {
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String type = rs.getString("type");
                if (type.equals("Протеины")) {
                    countProt++;
                }
                if (type.equals("Креатины")) {
                    countCrea++;
                }
                if (type.equals("Аминокислоты")) {
                    countAmin++;
                }
                if (type.equals("BCAA")) {
                    countBCAA++;
                }
                if (type.equals("Жиросжигатели")) {
                    countJiro++;
                }
                if (type.equals("Гейнеры")) {
                    countGein++;
                }
                if (type.equals("Энергетики")) {
                    countEner++;
                }
                if (type.equals("ВитаминныеКомплексы")) {
                    countVita++;
                }
            }
            res = countProt + " " + countCrea + " " + countAmin + " " +
                    countBCAA + " " + countJiro + " " + countGein + " " +
                    countEner + " " + countVita;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return res;
    }

    @Override
    public void insertRecordIntoOrderTable(String clientMessageRecieved) throws SQLException {

        int flag = 0;
        String[] message = clientMessageRecieved.split(" ");
        String region = message[1];
        String type = message[2];
        String name = message[3];
        String numberOfPackages = message[4];
        int iNumberOfPackages = Integer.parseInt(numberOfPackages);
        String weightOfPacking = message[5];
        int iWeightOfPacking = Integer.parseInt(weightOfPacking);
        String taste = message[6];
        String delivery = message[7];
        String status = "В_обработке";
        String insertTableSQL = "INSERT INTO orders"
                + "(region, type, name, numberOfPackages, weightOfPacking, taste, delivery, status) " + "VALUES"
                + "('" + region + "'," + "'" + type + "'," + "'" + name + "'," + "'" + iNumberOfPackages + "'," + "'" + iWeightOfPacking + "'," + "'" + taste + "'," + "'" + delivery + "'," + "'" + status + "');";
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            statement.executeUpdate(insertTableSQL);
            flag = 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        if (flag == 0) System.out.println("Ошибка добавления");
        else System.out.println("Данные успешно добавлены");
    }

    @Override
    public String calculateOrder(String clientMessageRecieved) throws SQLException {
        String[] message = clientMessageRecieved.split(" ");
        String name = message[3];
        int flagOne = 0;
        int flagTwo;
        int count = 0;
        String costStr = "";
        String price = "";
        String manufacturer = "";
        String accountingDiscounts = "";
        double numK;
        String manK = "";
        String accK = "";
        String regK = "";
        String typK = "";
        String wghK = "";
        String delK = "";
        String selectSQL = "SELECT name FROM product";
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            System.out.println(selectSQL);
            ResultSet rs = statement.executeQuery(selectSQL);
            while (rs.next()) {
                String nameTable = rs.getString("name");
                if (nameTable.equals(name)) {
                    flagOne = 1;
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (flagOne == 1) {
            String selectProductTableSQL = "SELECT price, manufacturer, accountingDiscounts FROM product " +
                    "WHERE name = '" + name + "'";
            try {
                dbConnection = getConnection();
                statement = dbConnection.createStatement();
                System.out.println(selectProductTableSQL);
                ResultSet rs = statement.executeQuery(selectProductTableSQL);
                while (rs.next()) {
                    price = rs.getString("price");
                    manufacturer = rs.getString("manufacturer");
                    accountingDiscounts = rs.getString("accountingDiscounts");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            String selectTrueSQL = "SELECT name FROM orders";
            try {
                dbConnection = getConnection();
                statement = dbConnection.createStatement();
                System.out.println(selectTrueSQL);
                ResultSet rs = statement.executeQuery(selectTrueSQL);
                while (rs.next()) {
                    String nameTrueTable = rs.getString("name");
                    if (nameTrueTable.equals(name)) {
                        count++;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            if (count > 1) {
                flagTwo = 0;
            } else flagTwo = 1;
            if (flagTwo == 1) {
                String selectOrderTableSQL = "SELECT region, type, numberOfPackages, weightOfPacking, delivery FROM orders " +
                        "WHERE name = '" + name + "'";
                try {
                    dbConnection = getConnection();
                    statement = dbConnection.createStatement();
                    System.out.println(selectOrderTableSQL);
                    ResultSet rs = statement.executeQuery(selectOrderTableSQL);
                    while (rs.next()) {
                        String region = rs.getString("region");
                        String type = rs.getString("type");
                        String numberOfPackages = rs.getString("numberOfPackages");
                        String weightOfPacking = rs.getString("weightOfPacking");
                        String delivery = rs.getString("delivery");
                        int num = Integer.parseInt(numberOfPackages);
                        if (num > 5) {
                            numK = 0.85;
                        } else numK = 1;
                        double priceOne = numK * Double.parseDouble(price) * Double.parseDouble(numberOfPackages);
                        switch (manufacturer) {
                            case "Германия":
                                manK += "1.15";
                                break;
                            case "Америка":
                                manK += "1.05";
                                break;
                            case "Япония":
                                manK += "1.1";
                                break;
                            default:
                                manK += "1.0";
                                break;
                        }
                        double priceTwo = priceOne * Double.parseDouble(manK);
                        switch (accountingDiscounts) {
                            case "0":
                                accK += "1";
                                break;
                            case "5":
                                accK += "1.05";
                                break;
                            case "10":
                                accK += "1.1";
                                break;
                        }
                        double priceThree = priceTwo * Double.parseDouble(accK);
                        if (region.equals("Минск")) {
                            regK += "1";
                        } else if (region.equals("Другие")) {
                            regK += "1.3";
                        } else {
                            regK += "1.1";
                        }
                        double priceFour = priceThree * Double.parseDouble(regK);
                        if (type.equals("Креатин")) {
                            typK += "1.05";
                        } else if (type.equals("BCAA")) {
                            typK += "1.1";
                        } else {
                            typK += "1";
                        }
                        double priceFive = priceFour * Double.parseDouble(typK);
                        int weight = Integer.parseInt(weightOfPacking);
                        if (weight < 1000) {
                            wghK += "1";
                        } else if (weight > 1000 && weight < 3000) {
                            wghK += "1.2";
                        } else if (weight > 3000 && weight < 10000) {
                            wghK += "1.6";
                        } else wghK += "2";
                        double priceSix = priceFive * Double.parseDouble(wghK);
                        switch (delivery) {
                            case "БезДоставки":
                                delK += "1";
                                break;
                            case "ОбычнаяДоставка":
                                delK += "1.1";
                                break;
                            case "СрочнаяДоставка":
                                delK += "1.2";
                                break;
                        }
                        double priceSeven = priceSix * Double.parseDouble(delK);
                        costStr += Double.toString(priceSeven);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                } finally {

                    if (statement != null) {
                        statement.close();
                    }

                    if (dbConnection != null) {
                        dbConnection.close();
                    }

                }
            }
        }
        return costStr;
    }

    @Override
    public void insertCostIntoOrders(String res, String clientMessageRecieved) throws SQLException {

        int flag = 0;
        String[] message = clientMessageRecieved.split(" ");
        String name = message[3];
        String selectTableSQL = "SELECT name FROM orders";
        String updateTableSQL = "UPDATE `registrationdb`.`orders` SET `orderCost`='" + res + "'"
                + " WHERE `name`='" + name + "'";
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String nameTable = rs.getString("name");
                if (name.equals(nameTable)) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                statement.execute(updateTableSQL);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        if (flag == 0) System.out.println("Ошибка добавления");
        else System.out.println("Данные успешно добавлены");
    }

    @Override
    public String selectRecordFromOrderTable() throws SQLException {

        Connection dbConnection = null;
        Statement statement = null;
        String selectTableSQL = "SELECT * FROM orders";
        String result = "";
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                order.setId(rs.getString("id"));
                order.setRegion(rs.getString("region"));
                order.setType(rs.getString("type"));
                order.setName(rs.getString("name"));
                order.setNumberOfPackages(rs.getString("numberOfPackages"));
                order.setWeightOfPacking(rs.getString("weightOfPacking"));
                order.setTaste(rs.getString("taste"));
                order.setDelivery(rs.getString("delivery"));
                order.setOrderCost(rs.getString("orderCost"));
                order.setStatus(rs.getString("status"));

                String id = rs.getString("id");
                String region = rs.getString("region");
                String type = rs.getString("type");
                String name = rs.getString("name");
                String numberOfPackages = rs.getString("numberOfPackages");
                String weightOfPacking = rs.getString("weightOfPacking");
                String taste = rs.getString("taste");
                String delivery = rs.getString("delivery");
                String cost = rs.getString("orderCost");
                String status = rs.getString("status");
                result += id + " " + region + " " + type + " " + name + " " + numberOfPackages + " " +
                        weightOfPacking + " " + taste + " " + delivery + " " + cost + " " + status + " ";
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }
        return result;
    }

    @Override
    public boolean processOrders(String clientMessageRecieved) throws SQLException {

        int flag = 0;
        int flagAdd = 0;
        int flagThree = 0;
        int flagBool = 0;
        String[] message = clientMessageRecieved.split(" ");
        String number = message[1];
        String status = "Обработан";
        String name = "Продан";
        String idNumProduct = "";
        String idNumOrder = "";
        String resNum = "";
        String nameFromOrders = "";
        String nameFromProduct = "";
        String selectTableSQL = "SELECT id FROM orders";
        String updateTableSQL = "UPDATE `registrationdb`.`orders` SET `name`='" + name + "'" +
                ", `status`='" + status + "'" + " WHERE `id` = '" + number + "'";
        String selectAddTableSQL = "SELECT status, name FROM orders WHERE id ='" + number + "'";
        String selectNameFromProduct = "SELECT name FROM product";
        String selectNameFromOrders = "SELECT name FROM orders WHERE id = '" + number + "'";
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectNameFromOrders);
            while (rs.next()) {
                nameFromOrders = rs.getString("name");

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectNameFromProduct);
            while (rs.next()) {
                nameFromProduct = rs.getString("name");
                if (nameFromOrders.equals(nameFromProduct)) {
                    flagThree = 1;
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (flagThree == 1) {
            try {
                dbConnection = getConnection();
                statement = dbConnection.createStatement();
                ResultSet rs = statement.executeQuery(selectAddTableSQL);
                while (rs.next()) {
                    String addStatus = rs.getString("status");
                    String addName = rs.getString("name");
                    if (status.equals(addStatus) && name.equals(addName)) {
                        flagAdd = 0;
                        break;
                    } else {
                        flagAdd = 1;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            if (flagAdd == 1) {
                try {
                    String selectFromProduct = "SELECT numberOfPackages FROM product WHERE name = '" + nameFromProduct + "'";
                    dbConnection = getConnection();
                    statement = dbConnection.createStatement();
                    ResultSet rs = statement.executeQuery(selectFromProduct);
                    while (rs.next()) {
                        idNumProduct += rs.getString("numberOfPackages");
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());

                }
                try {
                    String selectFromOrders = "SELECT numberOfPackages FROM orders WHERE name = '" + nameFromOrders + "'";
                    dbConnection = getConnection();
                    statement = dbConnection.createStatement();
                    ResultSet rs = statement.executeQuery(selectFromOrders);
                    while (rs.next()) {
                        idNumOrder += rs.getString("numberOfPackages");
                        int idNumProductInt = Integer.parseInt(idNumProduct);
                        int idNumOrderInt = Integer.parseInt(idNumOrder);
                        int res = idNumProductInt - idNumOrderInt;
                        if (res <= 0) {
                            flagBool = 0;
                            break;
                        } else flagBool = 1;
                        resNum = Integer.toString(res);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());

                }
                if (flagBool == 1) {
                    try {
                        dbConnection = getConnection();
                        statement = dbConnection.createStatement();
                        ResultSet rs = statement.executeQuery(selectTableSQL);
                        while (rs.next()) {
                            String idTable = rs.getString("id");
                            if (number.equals(idTable)) {
                                flag = 1;
                                break;
                            }
                        }
                        if (flag == 1) {
                            statement.execute(updateTableSQL);
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());

                    }
                    try {
                        String updateProduct = "UPDATE `registrationdb`.`product` SET `numberOfPackages`='" + resNum + "'" + " WHERE `name` = '" + nameFromProduct + "'";
                        dbConnection = getConnection();
                        statement = dbConnection.createStatement();
                        statement.execute(updateProduct);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());

                    } finally {
                        if (statement != null) {
                            statement.close();
                        }
                        if (dbConnection != null) {
                            dbConnection.close();
                        }
                    }
                }
            }
        }
        return flag != 0;
    }
}

