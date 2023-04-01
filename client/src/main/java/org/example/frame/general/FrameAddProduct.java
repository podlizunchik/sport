package org.example.frame.general;

import org.example.page.Admin;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FrameAddProduct extends Admin {

    private JPanel jContentPaneAddProduct = null;
    private JButton jButtonAddProduct = null;
    private JButton jButtonClearProduct = null;
    private JButton jButtonBackProduct = null;
    private JComboBox jTypeComboBoxProduct = null;
    private JTextField jNameFieldProduct = null;
    private JTextField jPriceFieldProduct = null;
    private JTextField jNumberOfPackagesFieldProduct = null;
    private JTextField jWeightOfPackingFieldProduct = null;
    private JTextField jManufacturerFieldProduct = null;
    private JCheckBox jAccountingDiscountsFirst = null;
    private JCheckBox jAccountingDiscountsSecond = null;
    private JCheckBox jAccountingDiscountsThird = null;
    private JLabel jLabelType = null;
    private JLabel jLabelName = null;
    private JLabel jLabelPrice = null;
    private JLabel jLabelNumberOfPackages = null;
    private JLabel jLabelWeightOfPacking = null;
    private JLabel jLabelManufacturer = null;
    private JLabel jLabelAccountingDiscounts = null;

    public static boolean checkString(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private JLabel getJLabelType() {

        if (jLabelType == null) {
            jLabelType = new JLabel("Выберите тип спортивного питания:");
            jLabelType.setBounds(new Rectangle(10, 10, 300, 10));
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
            jTypeComboBoxProduct.setBounds(new Rectangle(10, 25, 250, 20));
        }
        return jTypeComboBoxProduct;
    }

    private JLabel getJLabelName() {

        if (jLabelName == null) {
            jLabelName = new JLabel("Введите название:");
            jLabelName.setBounds(new Rectangle(10, 50, 300, 10));
        }
        return jLabelName;
    }

    private JTextField getJTextFieldName() {
        if (jNameFieldProduct == null) {
            jNameFieldProduct = new JTextField();
            jNameFieldProduct.setBounds(new Rectangle(10, 65, 250, 20));
        }
        return jNameFieldProduct;
    }

    private JLabel getjLabelPrice() {

        if (jLabelPrice == null) {
            jLabelPrice = new JLabel("Введите цену(в бел. руб.):");
            jLabelPrice.setBounds(new Rectangle(10, 90, 300, 10));
        }
        return jLabelPrice;
    }

    private JTextField getJTextFieldPrice() {
        if (jPriceFieldProduct == null) {
            jPriceFieldProduct = new JTextField();
            jPriceFieldProduct.setBounds(new Rectangle(10, 105, 250, 20));
        }
        return jPriceFieldProduct;
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

    private JLabel getjLabelManufacturer() {

        if (jLabelManufacturer == null) {
            jLabelManufacturer = new JLabel("Введите производителя(страну):");
            jLabelManufacturer.setBounds(new Rectangle(10, 210, 300, 10));
        }
        return jLabelManufacturer;
    }

    private JTextField getJTextFieldManafacturer() {
        if (jManufacturerFieldProduct == null) {
            jManufacturerFieldProduct = new JTextField();
            jManufacturerFieldProduct.setBounds(new Rectangle(10, 225, 250, 20));
        }
        return jManufacturerFieldProduct;
    }

    private JLabel getjLabelAccountingDiscounts() {

        if (jLabelAccountingDiscounts == null) {
            jLabelAccountingDiscounts = new JLabel("Скидка при покупке оптом(%):");
            jLabelAccountingDiscounts.setBounds(new Rectangle(10, 250, 300, 10));
        }
        return jLabelAccountingDiscounts;
    }

    private JCheckBox getAccountingDiscountsFirst() {
        if (jAccountingDiscountsFirst == null) {
            jAccountingDiscountsFirst = new JCheckBox("Нету");
            jAccountingDiscountsFirst.setSelected(true);
            jAccountingDiscountsFirst.setBounds(new Rectangle(10, 270, 55, 30));
        }
        return jAccountingDiscountsFirst;
    }

    private JCheckBox getAccountingDiscountsSecond() {
        if (jAccountingDiscountsSecond == null) {
            jAccountingDiscountsSecond = new JCheckBox("5%");
            jAccountingDiscountsSecond.setBounds(new Rectangle(65, 270, 50, 30));
        }
        return jAccountingDiscountsSecond;
    }

    private JCheckBox getAccountingDiscountsThird() {
        if (jAccountingDiscountsThird == null) {
            jAccountingDiscountsThird = new JCheckBox("10%");
            jAccountingDiscountsThird.setBounds(new Rectangle(115, 270, 50, 30));
        }
        return jAccountingDiscountsThird;
    }

    private JButton getJButtonClearProduct() {
        if (jButtonClearProduct == null) {
            jButtonClearProduct = new JButton();
            jButtonClearProduct.setBounds(new Rectangle(170, 300, 150, 30));
            jButtonClearProduct.setText("Очистить");
            jButtonClearProduct.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            jNameFieldProduct.setText("");
                            jPriceFieldProduct.setText("");
                            jNumberOfPackagesFieldProduct.setText("");
                            jWeightOfPackingFieldProduct.setText("");
                            jManufacturerFieldProduct.setText("");
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
            jButtonBackProduct.setBounds(new Rectangle(330, 300, 150, 30));
            jButtonBackProduct.setText("Назад");
            jButtonBackProduct.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            openMenuAdmin();
                        }
                    });
                }
            });
        }
        return jButtonBackProduct;
    }

    private JButton getJButtonAddProduct() {
        if (jButtonAddProduct == null) {
            jButtonAddProduct = new JButton();
            jButtonAddProduct.setBounds(new Rectangle(10, 300, 150, 30));
            jButtonAddProduct.setText("Добавить");
            jButtonAddProduct.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            int flag = 0;
                            int countCheck = 0;
                            try {

                                System.out.println("соединение с сервером...");
                                Socket clientSocket = new Socket("127.0.0.1", 3308);
                                System.out.println("соединение установлено...");
                                ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                                String id = "3";
                                String type = (String) jTypeComboBoxProduct.getSelectedItem();
                                String name = jNameFieldProduct.getText();
                                String price = jPriceFieldProduct.getText();
                                String numberOfPackages = jNumberOfPackagesFieldProduct.getText();
                                String weightOfPacking = jWeightOfPackingFieldProduct.getText();
                                String manufacturer = jManufacturerFieldProduct.getText();
                                String accountingDiscounts = "";
                                if (jAccountingDiscountsFirst.getModel().isSelected()) {
                                    accountingDiscounts += "0";
                                    countCheck++;
                                }
                                if (jAccountingDiscountsSecond.getModel().isSelected()) {
                                    accountingDiscounts += "5";
                                    countCheck++;
                                }
                                if (jAccountingDiscountsThird.getModel().isSelected()) {
                                    accountingDiscounts += "10";
                                    countCheck++;
                                }
                                if (countCheck > 1 || countCheck < 1) {
                                    JOptionPane.showMessageDialog(null, "Должен быть выбран только один пункт");
                                } else if (type.length() == 0 || name.length() == 0 || price.length() == 0 || numberOfPackages.length() == 0 || weightOfPacking.length() == 0 || manufacturer.length() == 0) {
                                    JOptionPane.showMessageDialog(null, "Ошибка!!! Все поля должны быть заполнены!");
                                } else if (checkString(price) == false || checkString(numberOfPackages) == false || checkString(weightOfPacking) == false) {
                                    JOptionPane.showMessageDialog(null, "Ошибка!!! Введенное значение должно быть типа int!");
                                } else {
                                    String clientMessage = id + " " + type + " " + name + " " + price + " " + numberOfPackages +
                                            " " + weightOfPacking + " " + manufacturer + " " + accountingDiscounts;
                                    output.writeObject(clientMessage);
                                    String autoMessage = "" + input.readObject();
                                    String[] massMessage = autoMessage.split("  ");
                                    flag = Integer.parseInt(massMessage[1]);
                                    String message = massMessage[0];
                                    JOptionPane.showMessageDialog(null, message);
                                }
                                output.close();
                                input.close();
                                clientSocket.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (flag == 1) {
                                openMenuAdmin();
                            } else {
                                FrameAddProduct.this.dispose();
                                insertProduct();
                            }
                        }
                    });
                }
            });
        }
        return jButtonAddProduct;
    }

    public void insertProduct() {

        initWindowAddProduct();

    }


    public static void initWindowAddProduct() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FrameAddProduct thisClass = new FrameAddProduct();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setLocationRelativeTo(null);
                thisClass.setVisible(true);

            }
        });
    }

    public FrameAddProduct() {
        super();
        initializeProduct();
    }

    private void initializeProduct() {
        this.setSize(500, 400);
        this.setResizable(false);
        this.setContentPane(getJContentPaneAddProduct());
        this.setTitle("Добавление товара");
    }

    private JPanel getJContentPaneAddProduct() {
        if (jContentPaneAddProduct == null) {
            jContentPaneAddProduct = new JPanel();
            jContentPaneAddProduct.setLayout(null);
            jContentPaneAddProduct.add(getJButtonAddProduct(), null);
            jContentPaneAddProduct.add(getJButtonClearProduct(), null);
            jContentPaneAddProduct.add(getJButtonBackProduct(), null);
            jContentPaneAddProduct.add(getJComboBoxType(), null);
            jContentPaneAddProduct.add(getJTextFieldName(), null);
            jContentPaneAddProduct.add(getJTextFieldPrice(), null);
            jContentPaneAddProduct.add(getJTextFieldNumberOfPackages(), null);
            jContentPaneAddProduct.add(getJTextFieldWeightOfPacking(), null);
            jContentPaneAddProduct.add(getJTextFieldManafacturer(), null);
            jContentPaneAddProduct.add(getAccountingDiscountsFirst(), null);
            jContentPaneAddProduct.add(getAccountingDiscountsSecond(), null);
            jContentPaneAddProduct.add(getAccountingDiscountsThird(), null);
            jContentPaneAddProduct.add(getJLabelType(), null);
            jContentPaneAddProduct.add(getJLabelName(), null);
            jContentPaneAddProduct.add(getjLabelPrice(), null);
            jContentPaneAddProduct.add(getjLabelNumberOfPackages(), null);
            jContentPaneAddProduct.add(getjLabelWeightOfPacking(), null);
            jContentPaneAddProduct.add(getjLabelManufacturer(), null);
            jContentPaneAddProduct.add(getjLabelAccountingDiscounts(), null);
        }
        return jContentPaneAddProduct;
    }

    public void openMenuAdmin() {

        this.dispose();
        initWindowAdmin();
    }
}

