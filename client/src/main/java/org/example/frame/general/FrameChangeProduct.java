package org.example.frame.general;

import org.example.page.Admin;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class FrameChangeProduct extends Admin {
    private JPanel jContentPaneChangeProduct = null;
    private JButton jButtonChangeProduct = null;
    private JButton jButtonClearProduct = null;
    private JButton jButtonBackProduct = null;
    private JTextField jIDFieldProduct = null;
    private JLabel jLabelID = null;
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

    private JLabel getJLabelID() {

        if (jLabelID == null) {
            jLabelID = new JLabel("Введите id товара,данные о котором хотите изменить:");
            jLabelID.setBounds(new Rectangle(10, 10, 400, 10));
        }
        return jLabelID;
    }

    private JTextField getJTextFieldID() {

        if (jIDFieldProduct == null) {
            jIDFieldProduct = new JTextField();
            jIDFieldProduct.setBounds(new Rectangle(10, 25, 50, 20));
        }
        return jIDFieldProduct;
    }

    private JLabel getJLabelType() {

        if (jLabelType == null) {
            jLabelType = new JLabel("Введите новый тип спортивного питания:");
            jLabelType.setBounds(new Rectangle(10, 50, 400, 10));
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
            jLabelName = new JLabel("Введите новое название:");
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

    private JLabel getjLabelPrice() {

        if (jLabelPrice == null) {
            jLabelPrice = new JLabel("Введите новую цену(в бел. руб.):");
            jLabelPrice.setBounds(new Rectangle(10, 130, 400, 10));
        }
        return jLabelPrice;
    }

    private JTextField getJTextFieldPrice() {
        if (jPriceFieldProduct == null) {
            jPriceFieldProduct = new JTextField();
            jPriceFieldProduct.setBounds(new Rectangle(10, 145, 250, 20));
        }
        return jPriceFieldProduct;
    }

    private JLabel getjLabelNumberOfPackages() {

        if (jLabelNumberOfPackages == null) {
            jLabelNumberOfPackages = new JLabel("Введите новое количество упаковок(штук):");
            jLabelNumberOfPackages.setBounds(new Rectangle(10, 180, 400, 10));
        }
        return jLabelNumberOfPackages;
    }

    private JTextField getJTextFieldNumberOfPackages() {

        if (jNumberOfPackagesFieldProduct == null) {
            jNumberOfPackagesFieldProduct = new JTextField();
            jNumberOfPackagesFieldProduct.setBounds(new Rectangle(10, 195, 250, 20));
        }
        return jNumberOfPackagesFieldProduct;
    }

    private JLabel getjLabelWeightOfPacking() {

        if (jLabelWeightOfPacking == null) {
            jLabelWeightOfPacking = new JLabel("Введите новый вес упаковки(в граммах):");
            jLabelWeightOfPacking.setBounds(new Rectangle(10, 220, 300, 10));
        }
        return jLabelWeightOfPacking;
    }

    private JTextField getJTextFieldWeightOfPacking() {
        if (jWeightOfPackingFieldProduct == null) {
            jWeightOfPackingFieldProduct = new JTextField();
            jWeightOfPackingFieldProduct.setBounds(new Rectangle(10, 235, 250, 20));
        }
        return jWeightOfPackingFieldProduct;
    }

    private JLabel getjLabelManufacturer() {

        if (jLabelManufacturer == null) {
            jLabelManufacturer = new JLabel("Введите нового производителя(страну):");
            jLabelManufacturer.setBounds(new Rectangle(10, 260, 400, 10));
        }
        return jLabelManufacturer;
    }

    private JTextField getJTextFieldManufacturer() {
        if (jManufacturerFieldProduct == null) {
            jManufacturerFieldProduct = new JTextField();
            jManufacturerFieldProduct.setBounds(new Rectangle(10, 275, 250, 20));
        }
        return jManufacturerFieldProduct;
    }

    private JLabel getLabelAccountingDiscounts() {

        if (jLabelAccountingDiscounts == null) {
            jLabelAccountingDiscounts = new JLabel("Скидка при покупке оптом(%):");
            jLabelAccountingDiscounts.setBounds(new Rectangle(10, 300, 300, 10));
        }
        return jLabelAccountingDiscounts;
    }

    private JCheckBox getAccountingDiscountsFirst() {
        if (jAccountingDiscountsFirst == null) {
            jAccountingDiscountsFirst = new JCheckBox("Нету");
            jAccountingDiscountsFirst.setSelected(true);
            jAccountingDiscountsFirst.setBounds(new Rectangle(10, 315, 55, 30));
        }
        return jAccountingDiscountsFirst;
    }

    private JCheckBox getAccountingDiscountsSecond() {
        if (jAccountingDiscountsSecond == null) {
            jAccountingDiscountsSecond = new JCheckBox("5%");
            jAccountingDiscountsSecond.setBounds(new Rectangle(65, 315, 50, 30));
        }
        return jAccountingDiscountsSecond;
    }

    private JCheckBox getAccountingDiscountsThird() {
        if (jAccountingDiscountsThird == null) {
            jAccountingDiscountsThird = new JCheckBox("10%");
            jAccountingDiscountsThird.setBounds(new Rectangle(115, 315, 50, 30));
        }
        return jAccountingDiscountsThird;
    }

    private JButton getJButtonClearProduct() {
        if (jButtonClearProduct == null) {
            jButtonClearProduct = new JButton();
            jButtonClearProduct.setBounds(new Rectangle(130, 360, 100, 30));
            jButtonClearProduct.setText("Очистить");
            jButtonClearProduct.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            jIDFieldProduct.setText("");
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
            jButtonBackProduct.setBounds(new Rectangle(250, 360, 100, 30));
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

    private JButton getJButtonChangeProduct() {
        if (jButtonChangeProduct == null) {
            jButtonChangeProduct = new JButton();
            jButtonChangeProduct.setBounds(new Rectangle(10, 360, 100, 30));
            jButtonChangeProduct.setText("Изменить");
            jButtonChangeProduct.addActionListener(new java.awt.event.ActionListener() {
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
                                String id = "5";
                                String newNumber = jIDFieldProduct.getText();
                                String newType = (String) jTypeComboBoxProduct.getSelectedItem();
                                String newName = jNameFieldProduct.getText();
                                String newPrice = jPriceFieldProduct.getText();
                                String newNumberOfPackages = jNumberOfPackagesFieldProduct.getText();
                                String newWeightOfPacking = jWeightOfPackingFieldProduct.getText();
                                String newManufacturer = jManufacturerFieldProduct.getText();
                                String newAccountingDiscounts = "";
                                if (jAccountingDiscountsFirst.getModel().isSelected()) {
                                    newAccountingDiscounts += "0";
                                    countCheck++;
                                }
                                if (jAccountingDiscountsSecond.getModel().isSelected()) {
                                    newAccountingDiscounts += "5";
                                    countCheck++;
                                }
                                if (jAccountingDiscountsThird.getModel().isSelected()) {
                                    newAccountingDiscounts += "10";
                                    countCheck++;
                                }
                                if (countCheck > 1 && countCheck < 1) {
                                    JOptionPane.showMessageDialog(null, "Должен быть выбран только один пункт");
                                } else if (newNumber.length() == 0 || newType.length() == 0 || newName.length() == 0 || newPrice.length() == 0 || newNumberOfPackages.length() == 0 || newWeightOfPacking.length() == 0 || newManufacturer.length() == 0) {
                                    JOptionPane.showMessageDialog(null, "Ошибка!!! Все поля должны быть заполнены!");
                                } else if (checkString(newNumber) == false || checkString(newPrice) == false || checkString(newNumberOfPackages) == false || checkString(newWeightOfPacking) == false) {
                                    JOptionPane.showMessageDialog(null, "Ошибка!!! Введенное значение должно быть числом!");
                                } else {
                                    String clientMessage = id + " " + newNumber + " " + newType + " " + newName + " " + newPrice + " " + newNumberOfPackages +
                                            " " + newWeightOfPacking + " " + newManufacturer + " " + newAccountingDiscounts;
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
                                FrameChangeProduct.this.dispose();
                                changeProduct();
                            }

                        }
                    });
                }
            });
        }
        return jButtonChangeProduct;
    }

    public void changeProduct() {

        initWindowChangeProduct();

    }


    public static void initWindowChangeProduct() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FrameChangeProduct thisClass = new FrameChangeProduct();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setLocationRelativeTo(null);
                thisClass.setVisible(true);
            }
        });
    }

    public FrameChangeProduct() {
        super();
        initializeProduct();
    }

    private void initializeProduct() {
        this.setSize(400, 500);
        this.setResizable(false);
        this.setContentPane(getjContentPaneChangeProduct());
        this.setTitle("Измененние данных о товаре");
    }

    private JPanel getjContentPaneChangeProduct() {
        if (jContentPaneChangeProduct == null) {
            jContentPaneChangeProduct = new JPanel();
            jContentPaneChangeProduct.setLayout(null);
            jContentPaneChangeProduct.add(getJButtonChangeProduct(), null);
            jContentPaneChangeProduct.add(getJButtonClearProduct(), null);
            jContentPaneChangeProduct.add(getJButtonBackProduct(), null);
            jContentPaneChangeProduct.add(getJLabelID(), null);
            jContentPaneChangeProduct.add(getJTextFieldID(), null);
            jContentPaneChangeProduct.add(getJComboBoxType(), null);
            jContentPaneChangeProduct.add(getJTextFieldName(), null);
            jContentPaneChangeProduct.add(getJTextFieldPrice(), null);
            jContentPaneChangeProduct.add(getJTextFieldNumberOfPackages(), null);
            jContentPaneChangeProduct.add(getJTextFieldWeightOfPacking(), null);
            jContentPaneChangeProduct.add(getJTextFieldManufacturer(), null);
            jContentPaneChangeProduct.add(getAccountingDiscountsFirst(), null);
            jContentPaneChangeProduct.add(getAccountingDiscountsSecond(), null);
            jContentPaneChangeProduct.add(getAccountingDiscountsThird(), null);
            jContentPaneChangeProduct.add(getJLabelType(), null);
            jContentPaneChangeProduct.add(getJLabelName(), null);
            jContentPaneChangeProduct.add(getjLabelPrice(), null);
            jContentPaneChangeProduct.add(getjLabelNumberOfPackages(), null);
            jContentPaneChangeProduct.add(getjLabelWeightOfPacking(), null);
            jContentPaneChangeProduct.add(getjLabelManufacturer(), null);
            jContentPaneChangeProduct.add(getLabelAccountingDiscounts(), null);
        }
        return jContentPaneChangeProduct;
    }

    public void openMenuAdmin() {

        this.dispose();
        initWindowAdmin();
    }

}
