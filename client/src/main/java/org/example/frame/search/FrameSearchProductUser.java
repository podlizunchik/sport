package org.example.frame.search;

import org.example.page.User;
import org.example.table.TableModelProduct;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FrameSearchProductUser extends User {

    private JPanel jContentPaneSearchProductUser = null;
    private JButton jButtonSearchProductUser = null;
    private JButton jButtonBackProductUser = null;
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

    private JButton getJButtonSearchProductUser() {
        if (jButtonSearchProductUser == null) {
            jButtonSearchProductUser = new JButton();
            jButtonSearchProductUser.setBounds(new Rectangle(30, 300, 150, 50));
            jButtonSearchProductUser.setText("Поиск");
            jButtonSearchProductUser.addActionListener(new java.awt.event.ActionListener() {
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
                                    FrameSearchProductUser.this.setState(JFrame.ICONIFIED);
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
        return jButtonSearchProductUser;
    }

    private JButton getJButtonBackProductUser() {
        if (jButtonBackProductUser == null) {
            jButtonBackProductUser = new JButton();
            jButtonBackProductUser.setBounds(new Rectangle(200, 300, 150, 50));
            jButtonBackProductUser.setText("Назад");
            jButtonBackProductUser.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            openMenuUser();
                        }
                    });
                }
            });
        }
        return jButtonBackProductUser;
    }

    public void searchProductUser() {

        initWindowSearchProductUser();

    }


    public static void initWindowSearchProductUser() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FrameSearchProductUser thisClass = new FrameSearchProductUser();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setLocationRelativeTo(null);
                thisClass.setVisible(true);
            }
        });
    }

    public FrameSearchProductUser() {
        super();
        initializeProductUser();
    }

    private void initializeProductUser() {
        this.setSize(400, 400);
        this.setResizable(false);
        this.setContentPane(getJContentPaneSearchProductUser());
        this.setTitle("Поиск товара");
    }

    private JPanel getJContentPaneSearchProductUser() {
        if (jContentPaneSearchProductUser == null) {
            jContentPaneSearchProductUser = new JPanel();
            jContentPaneSearchProductUser.setLayout(null);
            jContentPaneSearchProductUser.add(getJButtonSearchProductUser(), null);
            jContentPaneSearchProductUser.add(getJButtonBackProductUser(), null);
            jContentPaneSearchProductUser.add(getJLabelData(), null);
            jContentPaneSearchProductUser.add(getJTextFieldData(), null);
            jContentPaneSearchProductUser.add(getJLabelID(), null);
            jContentPaneSearchProductUser.add(getJTextFieldID(), null);
            jContentPaneSearchProductUser.add(getJLabelFirstInfo(), null);
            jContentPaneSearchProductUser.add(getJLabelSecondInfo(), null);
            jContentPaneSearchProductUser.add(getJLabelThirdInfo(), null);
            jContentPaneSearchProductUser.add(getJLabelFourInfo(), null);
            jContentPaneSearchProductUser.add(getJLabelFiveInfo(), null);
            jContentPaneSearchProductUser.add(getJLabelSixInfo(), null);
        }
        return jContentPaneSearchProductUser;
    }

    public void openMenuUser() {

        this.dispose();
        initWindowUser();
    }

}
