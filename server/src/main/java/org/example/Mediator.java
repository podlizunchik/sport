package org.example;

import org.example.service.auth.authorization.AuthorizationServiceImpl;
import org.example.service.auth.registration.RegistrationServiceImpl;
import org.example.service.order.OrdersImpl;
import org.example.service.product.ProductsImpl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Mediator {
    public ServerSocket serverSocket = null;
    public Socket clientAccepted = null;
    public ObjectOutputStream output = null;
    public ObjectInputStream input = null;

    public void connectionWithClient() {
        try {
            System.out.println("запуск сервера....");
            serverSocket = new ServerSocket(3308);
            clientAccepted = serverSocket.accept();
            System.out.println("соединение установлено....");
            input = new ObjectInputStream(clientAccepted.getInputStream());
            output = new ObjectOutputStream(clientAccepted.getOutputStream());
            String clientMessageRecieved = (String) input.readObject();
            String autoMessage = "";
            String[] message = clientMessageRecieved.split(" ");
            String id = message[0];
            int choice = Integer.parseInt(id);
            switch (choice) {
                case 1: {
                    try {
                        boolean bool;
                        bool = RegistrationServiceImpl.getInstance().insertRecordIntoRegistrationTable(clientMessageRecieved);
                        if (bool == true) {
                            autoMessage = "Вы успешно зарегистрировались!!!  1";
                        } else autoMessage = "Пользователь с таким логином уже существует!!!  0";
                        output.writeObject(autoMessage);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 2: {
                    try {
                        boolean bool;
                        bool = AuthorizationServiceImpl.getInstance().selectRecordsFromRegistrationTable(clientMessageRecieved);
                        if (bool == true) {
                            autoMessage = "Вы успешно авторизованы!!!  1";
                        } else autoMessage = "Неверный логин или пароль!!!  0";
                        output.writeObject(autoMessage);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 3: {
                    try {
                        boolean bool;
                        bool = ProductsImpl.getInstance().insertRecordIntoProductTable(clientMessageRecieved);
                        if (bool == true) {
                            autoMessage = "Данные добавлены успешно!!!  1";
                        } else autoMessage = "Возникла ошибка при добавлении!!  0";
                        output.writeObject(autoMessage);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 4: {
                    try {
                        boolean bool;
                        bool = ProductsImpl.getInstance().deleteRecordFromProductTable(clientMessageRecieved);
                        if (bool == true) {
                            autoMessage = "Данные удалены успешно!!!  1";
                        } else autoMessage = "Товара с данным id не существует!!  0";
                        output.writeObject(autoMessage);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 5: {
                    try {
                        boolean bool;
                        bool = ProductsImpl.getInstance().changeRecordIntoProductTable(clientMessageRecieved);
                        if (bool == true) {
                            autoMessage = "Данные успешно изменены!!!  1";
                        } else autoMessage = "Товара с данным id не существует!!!  0";
                        output.writeObject(autoMessage);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 6: {
                    try {
                        String result;
                        result = ProductsImpl.getInstance().selectRecordFromProductTable();
                        output.writeObject(result);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 9: {
                    String res = "";
                    int flag = 0;
                    try {
                        OrdersImpl.getInstance().insertRecordIntoOrderTable(clientMessageRecieved);
                        res = OrdersImpl.getInstance().calculateOrder(clientMessageRecieved);
                        if (checkString(res) == false) {
                            flag = 0;
                        } else flag = 1;
                        if (flag == 1) {
                            autoMessage = res + "  1";
                            OrdersImpl.getInstance().insertCostIntoOrders(res, clientMessageRecieved);
                        } else {
                            autoMessage = "Данного товара нету в ассортименте " +
                                    "либо Ваш заказ уже находится в обработке 0";
                        }
                        output.writeObject(autoMessage);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 13: {
                    String result;
                    result = ProductsImpl.getInstance().selectRecordFromProductTableForStatistics();
                    output.writeObject(result);
                    break;
                }
                default: {
                    System.out.println("Введено неверное значение!!!");
                    break;
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                input.close();
                output.close();
                clientAccepted.close();
                serverSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean checkString(String string) {
        try {
            Double.parseDouble(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
