package org.example.service.file;

import lombok.NoArgsConstructor;
import org.example.db.MySqlDaoFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
public class FilesServiceImpl extends MySqlDaoFactory implements FilesService {

    private static FilesServiceImpl file;

    public static synchronized FilesServiceImpl getInstance(){
        if(file == null){
            file = new FilesServiceImpl();
        }
        return file;
    }

    private static Connection dbConnection = null;
    private static Statement statement = null;

    @Override
    public boolean writeInFileForRegistration() throws SQLException {

        int flag = 0;
        String selectTableSQL = "SELECT * FROM registration";
        File FirstFileName = new File("users.txt");
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            System.out.println(selectTableSQL);
            ResultSet rs = statement.executeQuery(selectTableSQL);
            BufferedWriter writer = new BufferedWriter(new FileWriter(FirstFileName));
            while (rs.next()) {

                String loginTable = rs.getString("login");
                String passwordTable = rs.getString("password");
                String emailTable = rs.getString("email");
                writer.write("Логин: " + loginTable + '\n');
                writer.write("Пароль: " + passwordTable + '\n');
                writer.write("E-mail: " + emailTable + '\n' + '\n');
            }
            writer.close();
            flag = 1;
        } catch (FileNotFoundException e) {
            System.out.println("Невозможно записать в файл: " + FirstFileName);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
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
        if (flag == 0) return false;
        else return true;
    }

    @Override
    public boolean writeInFileForProduct() throws SQLException {

        int flag = 0;
        String selectTableSQL = "SELECT * FROM product";
        File FirstFileName = new File("products.txt");
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            System.out.println(selectTableSQL);
            ResultSet rs = statement.executeQuery(selectTableSQL);
            BufferedWriter writer = new BufferedWriter(new FileWriter(FirstFileName));
            while (rs.next()) {

                String idTable = rs.getString("id");
                String typeTable = rs.getString("type");
                String nameTable = rs.getString("name");
                String priceTable = rs.getString("price");
                String numberOfPackagesTable = rs.getString("numberOfPackages");
                String weightOfPackingTable = rs.getString("weightOfPacking");
                String manufacturerTable = rs.getString("manufacturer");
                String accountingDiscounts = rs.getString("accountingDiscounts");
                writer.write("ID: " + idTable + '\n');
                writer.write("Тип спортивного питания: " + typeTable + '\n');
                writer.write("Название: " + nameTable + '\n');
                writer.write("Цена: " + priceTable  + '\n');
                writer.write("Количество упаковок: " + numberOfPackagesTable + '\n');
                writer.write("Вес упаковки: " + weightOfPackingTable + '\n');
                writer.write("Производитель: " + manufacturerTable +  '\n');
                writer.write("Скидки(%): " + accountingDiscounts +  '\n' + '\n');
            }
            writer.close();
            flag = 1;
        } catch (FileNotFoundException e) {
            System.out.println("Невозможно записать в файл: " + FirstFileName);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
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
        if (flag == 0) return false;
        else return true;
    }

    @Override
    public boolean writeInFileForOrders() throws SQLException {

        int flag = 0;
        String selectTableSQL = "SELECT * FROM orders";
        File FirstFileName = new File("orders.txt");
        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();
            System.out.println(selectTableSQL);
            ResultSet rs = statement.executeQuery(selectTableSQL);
            BufferedWriter writer = new BufferedWriter(new FileWriter(FirstFileName));
            while (rs.next()) {

                String idTable = rs.getString("id");
                String regionTable = rs.getString("region");
                String typeTable = rs.getString("type");
                String nameTable = rs.getString("name");
                String numberOfPackagesTable = rs.getString("numberOfPackages");
                String weightOfPackingTable = rs.getString("weightOfPacking");
                String tasteTable = rs.getString("taste");
                String delivery = rs.getString("delivery");
                String orderCost = rs.getString("orderCost");
                String status = rs.getString("status");

                writer.write("ID: " + idTable  + '\n');
                writer.write("Регион: " + regionTable  + '\n');
                writer.write("Тип спортивного питания: " + typeTable + '\n');
                writer.write("Название: " + nameTable + '\n');
                writer.write("Количество упаковок: " + numberOfPackagesTable + '\n');
                writer.write("Вес упаковки: " + weightOfPackingTable + '\n');
                writer.write("Вкус: " + tasteTable +  '\n');
                writer.write("Условия доставки: " + delivery +  '\n');
                writer.write("Стоимость заказа: " + orderCost +  '\n');
                writer.write("Статус заказа: " + status +  '\n' + '\n');
            }
            writer.close();
            flag = 1;
        } catch (FileNotFoundException e) {
            System.out.println("Невозможно записать в файл: " + FirstFileName);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
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
        if (flag == 0) return false;
        else return true;
    }
}
