package org.example.frame.general;

import org.example.page.Admin;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FrameDeleteProduct extends Admin {

    private JPanel jContentPaneDeleteProduct = null;
    private JButton jButtonDeleteProduct = null;
    private JButton jButtonClearProduct = null;
    private JButton jButtonBackProduct = null;
    private JTextField jIDFieldProduct = null;
    private JLabel jLabelID = null;

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
            jLabelID = new JLabel("Введите id товара, который хотите удалить:");
            jLabelID.setBounds(new Rectangle(50, 10, 400, 10));
        }
        return jLabelID;
    }

    private JTextField getJTextFieldID() {

        if (jIDFieldProduct == null) {
            jIDFieldProduct = new JTextField();
            jIDFieldProduct.setBounds(new Rectangle(50, 30, 50, 20));
        }
        return jIDFieldProduct;
    }

    private JButton getJButtonClearProduct() {
        if (jButtonClearProduct == null) {
            jButtonClearProduct = new JButton();
            jButtonClearProduct.setBounds(new Rectangle(130, 100, 100, 30));
            jButtonClearProduct.setText("Очистить");
            jButtonClearProduct.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            jIDFieldProduct.setText("");
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
            jButtonBackProduct.setBounds(new Rectangle(250, 100, 100, 30));
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

    private JButton getJButtonDeleteProduct() {
        if (jButtonDeleteProduct == null) {
            jButtonDeleteProduct = new JButton();
            jButtonDeleteProduct.setBounds(new Rectangle(10, 100, 100, 30));
            jButtonDeleteProduct.setText("Удалить");
            jButtonDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            int flag = 0;
                            try {

                                System.out.println("соединение с сервером...");
                                Socket clientSocket = new Socket("127.0.0.1", 3308);
                                System.out.println("соединение установлено...");
                                ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                                String id = "4";
                                String number = jIDFieldProduct.getText();
                                if (number.length() == 0) {
                                    JOptionPane.showMessageDialog(null, "Ошибка!!! Все поля должны быть заполнены!");
                                } else if (checkString(number) == false) {
                                    JOptionPane.showMessageDialog(null, "Ошибка!!! Введенное значение должно быть числом!");
                                } else {
                                    String clientMessage = id + " " + number;
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
                                FrameDeleteProduct.this.dispose();
                                deleteProduct();
                            }
                        }
                    });
                }
            });
        }
        return jButtonDeleteProduct;
    }

    public void deleteProduct() {

        initWindowDeleteProduct();

    }


    public static void initWindowDeleteProduct() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FrameDeleteProduct thisClass = new FrameDeleteProduct();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setLocationRelativeTo(null);
                thisClass.setVisible(true);
            }
        });
    }

    public FrameDeleteProduct() {
        super();
        initializeProduct();
    }

    private void initializeProduct() {
        this.setSize(400, 200);
        this.setResizable(false);
        this.setContentPane(getjContentPaneDeleteProduct());
        this.setTitle("Удаление товара");
    }

    private JPanel getjContentPaneDeleteProduct() {
        if (jContentPaneDeleteProduct == null) {
            jContentPaneDeleteProduct = new JPanel();
            jContentPaneDeleteProduct.setLayout(null);
            jContentPaneDeleteProduct.add(getJButtonDeleteProduct(), null);
            jContentPaneDeleteProduct.add(getJButtonClearProduct(), null);
            jContentPaneDeleteProduct.add(getJButtonBackProduct(), null);
            jContentPaneDeleteProduct.add(getJLabelID(), null);
            jContentPaneDeleteProduct.add(getJTextFieldID(), null);
        }
        return jContentPaneDeleteProduct;
    }

    public void openMenuAdmin() {

        this.dispose();
        initWindowAdmin();
    }

}
