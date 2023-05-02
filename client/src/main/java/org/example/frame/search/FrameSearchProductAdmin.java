package org.example.frame.search;

import org.example.page.Admin;
import org.example.table.TableModelProduct;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FrameSearchProductAdmin extends Admin {

    private JPanel jContentPaneSearchProductAdmin = null;
    private JButton jButtonSearchProductAdmin = null;
    private JButton jButtonBackProductAdmin = null;
    private JLabel jLabelData = null;
    private JTextField jDataFieldProduct = null;
    private JLabel jLabelID = null;
    private JTextField jIDFieldProduct = null;
    private JLabel jLabelFirstInfo = null;
    private JLabel jLabelSecondInfo = null;
    private JLabel jLabelThirdInfo = null;
    private JLabel jLabelFourInfo = null;
    private JLabel jLabelFiveInfo = null;
    private JLabel jLabelSixInfo = null;

    private JLabel getJLabelFirstInfo() {

        if (jLabelFirstInfo == null) {
            jLabelFirstInfo = new JLabel("1.Поиск по типу");
            jLabelFirstInfo.setBounds(new Rectangle(50, 10, 300, 10));
        }
        return jLabelFirstInfo;
    }

    private JLabel getJLabelSecondInfo() {

        if (jLabelSecondInfo == null) {
            jLabelSecondInfo = new JLabel("2.Поиск по названию");
            jLabelSecondInfo.setBounds(new Rectangle(50, 30, 300, 10));
        }
        return jLabelSecondInfo;
    }

    private JLabel getJLabelThirdInfo() {

        if (jLabelThirdInfo == null) {
            jLabelThirdInfo = new JLabel("3.Поиск по цене");
            jLabelThirdInfo.setBounds(new Rectangle(50, 50, 300, 10));
        }
        return jLabelThirdInfo;
    }

    private JLabel getJLabelFourInfo() {

        if (jLabelFourInfo == null) {
            jLabelFourInfo = new JLabel("4.Поиск по количеству упаковок");
            jLabelFourInfo.setBounds(new Rectangle(50, 70, 300, 10));
        }
        return jLabelFourInfo;
    }

    private JLabel getJLabelFiveInfo() {

        if (jLabelFiveInfo == null) {
            jLabelFiveInfo = new JLabel("5.Поиск по весу упаковки");
            jLabelFiveInfo.setBounds(new Rectangle(50, 90, 300, 10));
        }
        return jLabelFiveInfo;
    }

    private JLabel getJLabelSixInfo() {

        if (jLabelSixInfo == null) {
            jLabelSixInfo = new JLabel("6.Поиск по производителю");
            jLabelSixInfo.setBounds(new Rectangle(50, 110, 300, 10));
        }
        return jLabelSixInfo;
    }

    private JLabel getJLabelData() {

        if (jLabelData == null) {
            jLabelData = new JLabel("Введите данные:");
            jLabelData.setBounds(new Rectangle(50, 200, 300, 10));
        }
        return jLabelData;
    }

    private JTextField getJTextFieldData() {
        if (jDataFieldProduct == null) {
            jDataFieldProduct = new JTextField();
            jDataFieldProduct.setBounds(new Rectangle(50, 220, 250, 20));
        }
        return jDataFieldProduct;
    }

    private JLabel getJLabelID() {

        if (jLabelID == null) {
            jLabelID = new JLabel("Введите id поиска:");
            jLabelID.setBounds(new Rectangle(50, 160, 300, 10));
        }
        return jLabelID;
    }

    private JTextField getJTextFieldID() {
        if (jIDFieldProduct == null) {
            jIDFieldProduct = new JTextField();
            jIDFieldProduct.setBounds(new Rectangle(50, 175, 250, 20));
        }
        return jIDFieldProduct;
    }

    private JButton getJButtonSearchProductAdmin() {
        if (jButtonSearchProductAdmin == null) {
            jButtonSearchProductAdmin = new JButton();
            jButtonSearchProductAdmin.setBounds(new Rectangle(30, 300, 150, 50));
            jButtonSearchProductAdmin.setText("Поиск");
            jButtonSearchProductAdmin.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            try {
                                System.out.println("соединение с сервером...");
                                Socket clientSocket = new Socket("127.0.0.1", 3308);
                                System.out.println("соединение установлено...");
                                ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                                String id = "7";
                                String searchID = jIDFieldProduct.getText();
                                String searchData = jDataFieldProduct.getText();
                                if (searchID.length() == 0 || searchData.length() == 0) {
                                    JOptionPane.showMessageDialog(null, "Ошибка!!! Все поля должны быть заполнены!");
                                } else if (searchID.equals("1") || searchID.equals("2") || searchID.equals("3") || searchID.equals("4")
                                        || searchID.equals("5") || searchID.equals("6")) {
                                    String clientMessage = id + " " + searchID + " " + searchData;
                                    output.writeObject(clientMessage);
                                    FrameSearchProductAdmin.this.setState(JFrame.ICONIFIED);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Ошибка!!! id должен принадлежать интервалу от 1 до 6 включительно!");
                                }
                                String autoMessage = "" + input.readObject();
                                TableModelProduct tmp = new TableModelProduct();
                                tmp.searchProduct(autoMessage);
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
        return jButtonSearchProductAdmin;
    }

    private JButton getJButtonBackProductAdmin() {
        if (jButtonBackProductAdmin == null) {
            jButtonBackProductAdmin = new JButton();
            jButtonBackProductAdmin.setBounds(new Rectangle(200, 300, 150, 50));
            jButtonBackProductAdmin.setText("Назад");
            jButtonBackProductAdmin.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            openMenuAdmin();
                        }
                    });
                }
            });
        }
        return jButtonBackProductAdmin;
    }

    public void searchProductAdmin() {

        initWindowSearchProductAdmin();

    }


    public static void initWindowSearchProductAdmin() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FrameSearchProductAdmin thisClass = new FrameSearchProductAdmin();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setLocationRelativeTo(null);
                thisClass.setVisible(true);
            }
        });
    }

    public FrameSearchProductAdmin() {
        super();
        initializeProductAdmin();
    }

    private void initializeProductAdmin() {
        this.setSize(400, 400);
        this.setResizable(false);
        this.setContentPane(getJContentPaneSearchProductAdmin());
        this.setTitle("Поиск товара");
    }

    private JPanel getJContentPaneSearchProductAdmin() {
        if (jContentPaneSearchProductAdmin == null) {
            jContentPaneSearchProductAdmin = new JPanel();
            jContentPaneSearchProductAdmin.setLayout(null);
            jContentPaneSearchProductAdmin.add(getJButtonSearchProductAdmin(), null);
            jContentPaneSearchProductAdmin.add(getJButtonBackProductAdmin(), null);
            jContentPaneSearchProductAdmin.add(getJLabelData(), null);
            jContentPaneSearchProductAdmin.add(getJTextFieldData(), null);
            jContentPaneSearchProductAdmin.add(getJLabelID(), null);
            jContentPaneSearchProductAdmin.add(getJTextFieldID(), null);
            jContentPaneSearchProductAdmin.add(getJLabelFirstInfo(), null);
            jContentPaneSearchProductAdmin.add(getJLabelSecondInfo(), null);
            jContentPaneSearchProductAdmin.add(getJLabelThirdInfo(), null);
            jContentPaneSearchProductAdmin.add(getJLabelFourInfo(), null);
            jContentPaneSearchProductAdmin.add(getJLabelFiveInfo(), null);
            jContentPaneSearchProductAdmin.add(getJLabelSixInfo(), null);
        }
        return jContentPaneSearchProductAdmin;
    }

    public void openMenuAdmin() {

        this.dispose();
        initWindowAdmin();
    }

}
