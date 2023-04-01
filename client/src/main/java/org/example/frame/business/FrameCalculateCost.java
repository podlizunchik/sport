package org.example.frame.business;

import org.example.page.User;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FrameCalculateCost extends User {

    private JPanel jContentPaneCalculateProduct = null;
    private JButton jButtonCalculateProduct = null;
    private JButton jButtonClearProduct = null;
    private JButton jButtonBackProduct = null;
    private JComboBox jRegionComboBoxProduct = null;
    private JComboBox jTypeComboBoxProduct = null;
    private JTextField jNameFieldProduct = null;
    private JTextField jNumberOfPackagesFieldProduct = null;
    private JTextField jWeightOfPackingFieldProduct = null;
    private JCheckBox jTasteFirst = null;
    private JCheckBox jTasteSecond = null;
    private JCheckBox jTasteThird = null;
    private JCheckBox jDeliveryFirst = null;
    private JCheckBox jDeliverySecond = null;
    private JCheckBox jDeliveryThird = null;
    private JTextField jResult = null;
    private JLabel jLabelType = null;
    private JLabel jLabelName = null;
    private JLabel jLabelRegion = null;
    private JLabel jLabelNumberOfPackages = null;
    private JLabel jLabelWeightOfPacking = null;
    private JLabel jLabelTaste = null;
    private JLabel jLabelDelivery = null;
    private JLabel jLabelResult = null;

    public static boolean checkString(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private JLabel getJLabelRegion() {

        if (jLabelRegion == null) {
            jLabelRegion = new JLabel("Выберите свой регион:");
            jLabelRegion.setBounds(new Rectangle(10, 10, 300, 10));
        }
        return jLabelRegion;
    }

    private JComboBox getJComboBoxRegion() {

        if (jRegionComboBoxProduct == null) {
            String[] items = {
                    "Минск",
                    "Гродно",
                    "Брест",
                    "Гомель",
                    "Витебск",
                    "Могилев",
                    "Другой"
            };
            jRegionComboBoxProduct = new JComboBox(items);
            jRegionComboBoxProduct.setBounds(new Rectangle(10, 25, 250, 20));
        }
        return jRegionComboBoxProduct;
    }

    private JLabel getJLabelType() {

        if (jLabelType == null) {
            jLabelType = new JLabel("Выберите тип спортивного питания:");
            jLabelType.setBounds(new Rectangle(10, 50, 300, 10));
        }
        return jLabelType;
    }

    private JComboBox getJComboBoxType() {

        if (jTypeComboBoxProduct == null) {
            String[] items = {
                    "Протеины",
                    "Креатины",
                    "Аминокислоты",
                    "BCAA",
                    "Жиросжигатели",
                    "Гейнеры",
                    "Энергетики",
                    "ВитаминныеКомплексы"
            };
            jTypeComboBoxProduct = new JComboBox(items);
            jTypeComboBoxProduct.setBounds(new Rectangle(10, 65, 250, 20));
        }
        return jTypeComboBoxProduct;
    }

    private JLabel getJLabelName() {

        if (jLabelName == null) {
            jLabelName = new JLabel("Введите название:");
            jLabelName.setBounds(new Rectangle(10, 90, 300, 10));
        }
        return jLabelName;
    }

    private JTextField getJTextFieldName() {
        if (jNameFieldProduct == null) {
            jNameFieldProduct = new JTextField();
            jNameFieldProduct.setBounds(new Rectangle(10, 105, 250, 20));
        }
        return jNameFieldProduct;
    }

    private JLabel getjLabelNumberOfPackages() {

        if (jLabelNumberOfPackages == null) {
            jLabelNumberOfPackages = new JLabel("Введите количество упаковок(штук):");
            jLabelNumberOfPackages.setBounds(new Rectangle(10, 130, 300, 10));
        }
        return jLabelNumberOfPackages;
    }

    private JTextField getJTextFieldNumberOfPackages() {

        if (jNumberOfPackagesFieldProduct == null) {
            jNumberOfPackagesFieldProduct = new JTextField();
            jNumberOfPackagesFieldProduct.setBounds(new Rectangle(10, 145, 250, 20));
        }
        return jNumberOfPackagesFieldProduct;
    }

    private JLabel getjLabelWeightOfPacking() {

        if (jLabelWeightOfPacking == null) {
            jLabelWeightOfPacking = new JLabel("Введите вес упаковки(в граммах):");
            jLabelWeightOfPacking.setBounds(new Rectangle(10, 170, 300, 10));
        }
        return jLabelWeightOfPacking;
    }

    private JTextField getJTextFieldWeightOfPacking() {
        if (jWeightOfPackingFieldProduct == null) {
            jWeightOfPackingFieldProduct = new JTextField();
            jWeightOfPackingFieldProduct.setBounds(new Rectangle(10, 185, 250, 20));
        }
        return jWeightOfPackingFieldProduct;
    }

    private JLabel getjLabelTaste() {

        if (jLabelTaste == null) {
            jLabelTaste = new JLabel("Выберите вкус:");
            jLabelTaste.setBounds(new Rectangle(10, 210, 300, 10));
        }
        return jLabelTaste;
    }

    private JCheckBox getjTasteFirst() {
        if (jTasteFirst == null) {
            jTasteFirst = new JCheckBox("Клубника");
            jTasteFirst.setSelected(true);
            jTasteFirst.setBounds(new Rectangle(10, 225, 90, 30));
        }
        return jTasteFirst;
    }

    private JCheckBox getjTasteSecond() {
        if (jTasteSecond == null) {
            jTasteSecond = new JCheckBox("Шоколад");
            jTasteSecond.setBounds(new Rectangle(105, 225, 90, 30));
        }
        return jTasteSecond;
    }

    private JCheckBox getjTasteThird() {
        if (jTasteThird == null) {
            jTasteThird = new JCheckBox("Ваниль");
            jTasteThird.setBounds(new Rectangle(200, 225, 70, 30));
        }
        return jTasteThird;
    }

    private JLabel getjLabelDelivery() {

        if (jLabelDelivery == null) {
            jLabelDelivery = new JLabel("Условия доставки:");
            jLabelDelivery.setBounds(new Rectangle(10, 260, 300, 10));
        }
        return jLabelDelivery;
    }

    private JCheckBox getjDeliveryFirst() {
        if (jDeliveryFirst == null) {
            jDeliveryFirst = new JCheckBox("Без доставки");
            jDeliveryFirst.setSelected(true);
            jDeliveryFirst.setBounds(new Rectangle(10, 275, 120, 30));
        }
        return jDeliveryFirst;
    }

    private JCheckBox getjDeliverySecond() {
        if (jDeliverySecond == null) {
            jDeliverySecond = new JCheckBox("Обычная доставка");
            jDeliverySecond.setBounds(new Rectangle(135, 275, 150, 30));
        }
        return jDeliverySecond;
    }

    private JCheckBox getjDeliveryThird() {
        if (jDeliveryThird == null) {
            jDeliveryThird = new JCheckBox("Срочная доставка");
            jDeliveryThird.setBounds(new Rectangle(290, 275, 150, 30));
        }
        return jDeliveryThird;
    }

    private JLabel getjLabelResult() {

        if (jLabelResult == null) {
            jLabelResult = new JLabel("Стоимость заказа составила(бел. руб.):");
            jLabelResult.setBounds(new Rectangle(10, 330, 400, 10));
        }
        return jLabelResult;
    }

    private JTextField getJTextFieldResult() {
        if (jResult == null) {
            jResult = new JTextField();
            jResult.setBounds(new Rectangle(10, 345, 250, 20));
        }
        return jResult;
    }

    private JButton getJButtonClearProduct() {
        if (jButtonClearProduct == null) {
            jButtonClearProduct = new JButton();
            jButtonClearProduct.setBounds(new Rectangle(170, 410, 150, 30));
            jButtonClearProduct.setText("Очистить");
            jButtonClearProduct.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            jNameFieldProduct.setText("");
                            jNumberOfPackagesFieldProduct.setText("");
                            jWeightOfPackingFieldProduct.setText("");
                            jResult.setText("");
                        }
                    });
                }
            });
        }
        return jButtonClearProduct;
    }

    private JButton getJButtonBackProduct() {
        if (jButtonBackProduct == null) {
            jButtonBackProduct = new JButton();
            jButtonBackProduct.setBounds(new Rectangle(330, 410, 150, 30));
            jButtonBackProduct.setText("Назад");
            jButtonBackProduct.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            openMenuUser();
                        }
                    });
                }
            });
        }
        return jButtonBackProduct;
    }

    private JButton getJButtonCalculateProduct() {
        if (jButtonCalculateProduct == null) {
            jButtonCalculateProduct = new JButton();
            jButtonCalculateProduct.setBounds(new Rectangle(10, 410, 150, 30));
            jButtonCalculateProduct.setText("Рассчитать");
            jButtonCalculateProduct.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            int flag = 0;
                            int countCheckOne = 0;
                            int countCheckTwo = 0;
                            try {
                                System.out.println("соединение с сервером...");
                                Socket clientSocket = new Socket("127.0.0.1", 3308);
                                System.out.println("соединение установлено...");
                                ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                                String id = "9";
                                String region = (String) jRegionComboBoxProduct.getSelectedItem();
                                String type = (String) jTypeComboBoxProduct.getSelectedItem();
                                String name = jNameFieldProduct.getText();
                                String numberOfPackages = jNumberOfPackagesFieldProduct.getText();
                                String weightOfPacking = jWeightOfPackingFieldProduct.getText();
                                String taste = "";
                                String delivery = "";
                                if (jTasteFirst.getModel().isSelected()) {
                                    taste += "Клубника";
                                    countCheckOne++;
                                }
                                if (jTasteSecond.getModel().isSelected()) {
                                    taste += "Шоколад";
                                    countCheckOne++;
                                }
                                if (jTasteThird.getModel().isSelected()) {
                                    taste += "Ваниль";
                                    countCheckOne++;
                                }
                                if (countCheckOne > 1 || countCheckOne < 1) {
                                    JOptionPane.showMessageDialog(null, "Должен быть выбран только один пункт");
                                }
                                if (jDeliveryFirst.getModel().isSelected()) {
                                    delivery += "БезДоставки";
                                    countCheckTwo++;
                                }
                                if (jDeliverySecond.getModel().isSelected()) {
                                    delivery += "ОбычнаяДоставка";
                                    countCheckTwo++;
                                }
                                if (jDeliveryThird.getModel().isSelected()) {
                                    delivery += "СрочнаяДоставка";
                                    countCheckTwo++;
                                }
                                if (countCheckTwo > 1 || countCheckTwo < 1) {
                                    JOptionPane.showMessageDialog(null, "Должен быть выбран только один пункт");
                                } else if (region.length() == 0 || type.length() == 0 || name.length() == 0 || numberOfPackages.length() == 0 || weightOfPacking.length() == 0 || taste.length() == 0 || delivery.length() == 0) {
                                    JOptionPane.showMessageDialog(null, "Ошибка!!! Все поля должны быть заполнены!");
                                } else if (checkString(numberOfPackages) == false || checkString(weightOfPacking) == false) {
                                    JOptionPane.showMessageDialog(null, "Ошибка!!! Введенное значение должно быть числом!");
                                } else {
                                    String clientMessage = id + " " + region + " " + type + " " + name + " " + numberOfPackages +
                                            " " + weightOfPacking + " " + taste + " " + delivery;
                                    output.writeObject(clientMessage);
                                    Thread.sleep(10000);
                                    String autoMessage = "" + input.readObject();
                                    String[] massMessage = autoMessage.split(" ");
                                    String message = massMessage[1];
                                    if (message.equals("1")) {
                                        double doubleCost = Double.parseDouble(massMessage[0]);
                                        int intCost = (int) doubleCost;
                                        String cost = Integer.toString(intCost);
                                        jResult.setText(cost);
                                    } else {
                                        String mess = massMessage[0];
                                        JOptionPane.showMessageDialog(null, mess);
                                    }
                                }
                                output.close();
                                input.close();
                                clientSocket.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        return jButtonCalculateProduct;
    }

    public void calculateProductUser() {

        initWindowCalculateProduct();

    }


    public static void initWindowCalculateProduct() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FrameCalculateCost thisClass = new FrameCalculateCost();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setLocationRelativeTo(null);
                thisClass.setVisible(true);

            }
        });
    }

    public FrameCalculateCost() {
        super();
        initializeProduct();
    }

    private void initializeProduct() {
        this.setSize(500, 500);
        this.setResizable(false);
        this.setContentPane(getJContentPaneCalculateProduct());
        this.setTitle("Расчет стоимости заказа");
    }

    private JPanel getJContentPaneCalculateProduct() {
        if (jContentPaneCalculateProduct == null) {
            jContentPaneCalculateProduct = new JPanel();
            jContentPaneCalculateProduct.setLayout(null);
            jContentPaneCalculateProduct.add(getJButtonCalculateProduct(), null);
            jContentPaneCalculateProduct.add(getJButtonClearProduct(), null);
            jContentPaneCalculateProduct.add(getJButtonBackProduct(), null);
            jContentPaneCalculateProduct.add(getJComboBoxRegion(), null);
            jContentPaneCalculateProduct.add(getJComboBoxType(), null);
            jContentPaneCalculateProduct.add(getJTextFieldName(), null);
            jContentPaneCalculateProduct.add(getJTextFieldNumberOfPackages(), null);
            jContentPaneCalculateProduct.add(getJTextFieldWeightOfPacking(), null);
            jContentPaneCalculateProduct.add(getjTasteFirst(), null);
            jContentPaneCalculateProduct.add(getjTasteSecond(), null);
            jContentPaneCalculateProduct.add(getjTasteThird(), null);
            jContentPaneCalculateProduct.add(getjDeliveryFirst(), null);
            jContentPaneCalculateProduct.add(getjDeliverySecond(), null);
            jContentPaneCalculateProduct.add(getjDeliveryThird(), null);
            jContentPaneCalculateProduct.add(getJTextFieldResult(), null);
            jContentPaneCalculateProduct.add(getJLabelRegion(), null);
            jContentPaneCalculateProduct.add(getJLabelType(), null);
            jContentPaneCalculateProduct.add(getJLabelName(), null);
            jContentPaneCalculateProduct.add(getjLabelNumberOfPackages(), null);
            jContentPaneCalculateProduct.add(getjLabelWeightOfPacking(), null);
            jContentPaneCalculateProduct.add(getjLabelTaste(), null);
            jContentPaneCalculateProduct.add(getjLabelDelivery(), null);
            jContentPaneCalculateProduct.add(getjLabelResult(), null);
        }
        return jContentPaneCalculateProduct;
    }

    public void openMenuUser() {

        this.dispose();
        initWindowUser();
    }
}

