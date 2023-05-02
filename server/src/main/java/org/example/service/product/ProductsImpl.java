// класс Products со всем функционалом
package org.example.service.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.db.MySqlDaoFactory;
import org.example.entity.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Getter
@Setter
@AllArgsConstructor
public class ProductsImpl extends MySqlDaoFactory implements Products {

    private static ProductsImpl productsImpl;
    private static Product product = new Product();

    public static synchronized ProductsImpl getInstance() {
        if (productsImpl == null) {
            productsImpl = new ProductsImpl();
        }
        return productsImpl;
    }

    private static Connection dbConnection = null;
    private static Statement statement = null;

    @Override
    public String selectRecordFromProductTableForStatistics() {
        String res = "";
        int countUSA = 0;
        int countRussia = 0;
        int countGermany = 0;
        int countItaly = 0;
        int countChina = 0;
        int countCanada = 0;
        int countGreece = 0;
        int countJapan = 0;
        String selectTableSQL = "SELECT manufacturer FROM product";
        try (Connection dbConnection = getConnection(); Statement statement = dbConnection.createStatement()) {
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String manufacturer = rs.getString("manufacturer");
                if (manufacturer.equals("Америка")) {
                    countUSA++;
                }
                if (manufacturer.equals("Россия")) {
                    countRussia++;
                }
                if (manufacturer.equals("Германия")) {
                    countGermany++;
                }
                if (manufacturer.equals("Италия")) {
                    countItaly++;
                }
                if (manufacturer.equals("Китай")) {
                    countChina++;
                }
                if (manufacturer.equals("Канада")) {
                    countCanada++;
                }
                if (manufacturer.equals("Греция")) {
                    countGreece++;
                }
                if (manufacturer.equals("Япония")) {
                    countJapan++;
                }
            }
            res = countUSA + " " + countRussia + " " + countGermany + " " +
                    countItaly + " " + countChina + " " + countCanada + " " +
                    countGreece + " " + countJapan;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return res;
    }

    @Override
    public boolean insertRecordIntoProductTable(String clientMessageRecieved) throws SQLException {

        int flag = 0;
        String[] message = clientMessageRecieved.split(" ");
        String type = message[1];
        String name = message[2];
        String price = message[3];
        int iPrice = Integer.parseInt(price);
        String numberOfPackages = message[4];
        int iNumberOfPackages = Integer.parseInt(numberOfPackages);
        String weightOfPacking = message[5];
        int iWeightOfPacking = Integer.parseInt(weightOfPacking);
        String manufacturer = message[6];
        String accountingDiscounts = message[7];
        int iAccountingDiscounts = Integer.parseInt(accountingDiscounts);
        String insertTableSQL = "INSERT INTO product"
                + "(type, name, price, numberOfPackages, WeightOfPacking, manufacturer, accountingDiscounts) " + "VALUES"
                + "('" + type + "'," + "'" + name + "'," + "'" + iPrice + "'," + "'" + iNumberOfPackages + "'," + "'" + iWeightOfPacking + "'," + "'" + manufacturer + "'," + "'" + iAccountingDiscounts + "');";
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
        return flag != 0;
    }

    @Override
    public boolean deleteRecordFromProductTable(String clientMessageRecieved) throws SQLException {

        int flag = 0;
        String[] message = clientMessageRecieved.split(" ");
        String id = message[1];
        String selectTableSQL = "SELECT id FROM product";
        String deleteTableSQL = "DELETE FROM `registrationdb`.`product` WHERE `id`='" + id + "'";
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String idTable = rs.getString("id");
                if (id.equals(idTable)) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                statement.execute(deleteTableSQL);
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
        return flag != 0;
    }

    @Override
    public boolean changeRecordIntoProductTable(String clientMessageRecieved) throws SQLException {

        int flag = 0;
        String[] message = clientMessageRecieved.split(" ");
        String id = message[1];
        String type = message[2];
        String name = message[3];
        String price = message[4];
        int iPrice = Integer.parseInt(price);
        String numberOfPackages = message[5];
        int iNumberOfPackages = Integer.parseInt(numberOfPackages);
        String weightOfPacking = message[6];
        int iWeightOfPacking = Integer.parseInt(weightOfPacking);
        String manufacturer = message[7];
        String accountingDiscounts = message[8];
        int iAccountingDiscounts = Integer.parseInt(accountingDiscounts);
        String selectTableSQL = "SELECT id FROM product";
        String updateTableSQL = "UPDATE `registrationdb`.`product` SET `type`='" + type + "'" +
                ", `name`='" + name + "'" + ", `price`='" + iPrice + "'" +
                ", `numberOfPackages`='" + iNumberOfPackages + "'" + ", `weightOfPacking`='" + iWeightOfPacking + "'" +
                ", `manufacturer`='" + manufacturer + "'" + ", `accountingDiscounts`='" + iAccountingDiscounts + "'" + " WHERE `id`='" + id + "'";
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String idTable = rs.getString("id");
                if (id.equals(idTable)) {
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
        return flag != 0;
    }

    @Override
    public String selectRecordFromProductTable() throws SQLException {

        Connection dbConnection = null;
        Statement statement = null;
        String selectTableSQL = "SELECT * FROM product";
        String result = "";
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                product.setId(rs.getString("id"));
                product.setType(rs.getString("type"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setNumberOfPackages(rs.getString("numberOfPackages"));
                product.setWeightOfPacking(rs.getString("weightOfPacking"));
                product.setManufacturer(rs.getString("manufacturer"));
                product.setAccountingDiscounts(rs.getString("accountingDiscounts"));

                String id = rs.getString("id");
                String type = rs.getString("type");
                String name = rs.getString("name");
                String price = rs.getString("price");
                String numberOfPackages = rs.getString("numberOfPackages");
                String weightOfPacking = rs.getString("weightOfPacking");
                String manufacturer = rs.getString("manufacturer");
                String accountingDiscounts = rs.getString("accountingDiscounts");
                result += id + " " + type + " " + name + " " + price + " " + numberOfPackages + " " +
                        weightOfPacking + " " + manufacturer + " " + accountingDiscounts + " ";
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
    public String selectRecordFromProductTableForSearch(String clientMessageRecieved) {
        String[] message = clientMessageRecieved.split(" ");
        String searchID = message[1];
        int searchIntID = Integer.parseInt(searchID);
        String searchData = message[2];
        String result = "";
        switch (searchIntID) {
            case 1: {
                String selectTableSQL = "SELECT * FROM product WHERE type LIKE '%" + searchData + "%'";
                try {
                    dbConnection = getConnection();
                    statement = dbConnection.createStatement();
                    ResultSet rs = statement.executeQuery(selectTableSQL);
                    while (rs.next()) {
                        String id = rs.getString("id");
                        String type = rs.getString("type");
                        String name = rs.getString("name");
                        String price = rs.getString("price");
                        String numberOfPackages = rs.getString("numberOfPackages");
                        String weightOfPacking = rs.getString("weightOfPacking");
                        String manufacturer = rs.getString("manufacturer");
                        String accountingDiscounts = rs.getString("accountingDiscounts");
                        result += id + " " + type + " " + name + " " + price + " " + numberOfPackages + " " +
                                weightOfPacking + " " + manufacturer + " " + accountingDiscounts + " ";
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 2: {
                String selectTableSQL = "SELECT * FROM product WHERE name LIKE '%" + searchData + "%'";
                try {
                    dbConnection = getConnection();
                    statement = dbConnection.createStatement();
                    ResultSet rs = statement.executeQuery(selectTableSQL);
                    while (rs.next()) {
                        String id = rs.getString("id");
                        String type = rs.getString("type");
                        String name = rs.getString("name");
                        String price = rs.getString("price");
                        String numberOfPackages = rs.getString("numberOfPackages");
                        String weightOfPacking = rs.getString("weightOfPacking");
                        String manufacturer = rs.getString("manufacturer");
                        String accountingDiscounts = rs.getString("accountingDiscounts");
                        result += id + " " + type + " " + name + " " + price + " " + numberOfPackages + " " +
                                weightOfPacking + " " + manufacturer + " " + accountingDiscounts + " ";
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 3: {
                String selectTableSQL = "SELECT * FROM product WHERE price LIKE '%" + searchData + "%'";
                try {
                    dbConnection = getConnection();
                    statement = dbConnection.createStatement();
                    ResultSet rs = statement.executeQuery(selectTableSQL);
                    while (rs.next()) {
                        String id = rs.getString("id");
                        String type = rs.getString("type");
                        String name = rs.getString("name");
                        String price = rs.getString("price");
                        String numberOfPackages = rs.getString("numberOfPackages");
                        String weightOfPacking = rs.getString("weightOfPacking");
                        String manufacturer = rs.getString("manufacturer");
                        String accountingDiscounts = rs.getString("accountingDiscounts");
                        result += id + " " + type + " " + name + " " + price + " " + numberOfPackages + " " +
                                weightOfPacking + " " + manufacturer + " " + accountingDiscounts + " ";
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 4: {
                String selectTableSQL = "SELECT * FROM product WHERE numberOfPackages LIKE '%" + searchData + "%'";
                try {
                    dbConnection = getConnection();
                    statement = dbConnection.createStatement();
                    ResultSet rs = statement.executeQuery(selectTableSQL);
                    while (rs.next()) {
                        String id = rs.getString("id");
                        String type = rs.getString("type");
                        String name = rs.getString("name");
                        String price = rs.getString("price");
                        String numberOfPackages = rs.getString("numberOfPackages");
                        String weightOfPacking = rs.getString("weightOfPacking");
                        String manufacturer = rs.getString("manufacturer");
                        String accountingDiscounts = rs.getString("accountingDiscounts");
                        result += id + " " + type + " " + name + " " + price + " " + numberOfPackages + " " +
                                weightOfPacking + " " + manufacturer + " " + accountingDiscounts + " ";
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 5: {
                String selectTableSQL = "SELECT * FROM product WHERE weightOfPacking LIKE '%" + searchData + "%'";
                try {
                    dbConnection = getConnection();
                    statement = dbConnection.createStatement();
                    ResultSet rs = statement.executeQuery(selectTableSQL);
                    while (rs.next()) {
                        String id = rs.getString("id");
                        String type = rs.getString("type");
                        String name = rs.getString("name");
                        String price = rs.getString("price");
                        String numberOfPackages = rs.getString("numberOfPackages");
                        String weightOfPacking = rs.getString("weightOfPacking");
                        String manufacturer = rs.getString("manufacturer");
                        String accountingDiscounts = rs.getString("accountingDiscounts");
                        result += id + " " + type + " " + name + " " + price + " " + numberOfPackages + " " +
                                weightOfPacking + " " + manufacturer + " " + accountingDiscounts + " ";
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 6: {
                String selectTableSQL = "SELECT * FROM product WHERE manufacturer LIKE '%" + searchData + "%'";
                try {
                    dbConnection = getConnection();
                    statement = dbConnection.createStatement();
                    ResultSet rs = statement.executeQuery(selectTableSQL);
                    while (rs.next()) {
                        String id = rs.getString("id");
                        String type = rs.getString("type");
                        String name = rs.getString("name");
                        String price = rs.getString("price");
                        String numberOfPackages = rs.getString("numberOfPackages");
                        String weightOfPacking = rs.getString("weightOfPacking");
                        String manufacturer = rs.getString("manufacturer");
                        String accountingDiscounts = rs.getString("accountingDiscounts");
                        result += id + " " + type + " " + name + " " + price + " " + numberOfPackages + " " +
                                weightOfPacking + " " + manufacturer + " " + accountingDiscounts + " ";
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            default: {
                System.out.println("Введено неверное значение!!!");
                break;
            }
        }
        return result;
    }


}
