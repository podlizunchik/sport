package org.example.frame.business;

import org.example.page.Admin;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FrameOrderProcessing extends Admin {

    private JPanel jContentPaneOrderProcessing = null;
    private JButton jButtonOrderProcessing = null;
    private JButton jButtonBackOrderProcessing = null;
    private JButton jButtonClearOrderProcessing = null;
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
            jLabelID = new JLabel("Введите id заказа, который хотите обработать:");
            jLabelID.setBounds(new Rectangle(30, 10, 340, 10));
        }
        return jLabelID;
    }

    private JTextField getJTextFieldID() {

        if (jIDFieldProduct == null) {
            jIDFieldProduct = new JTextField();
            jIDFieldProduct.setBounds(new Rectangle(30, 25, 80, 20));
        }
        return jIDFieldProduct;
    }


    private JButton getJButtonOrderProcessing() {
        if (jButtonOrderProcessing == null) {
            jButtonOrderProcessing = new JButton();
            jButtonOrderProcessing.setBounds(new Rectangle(30, 90, 100, 50));
            jButtonOrderProcessing.setText("Обработать");
            jButtonOrderProcessing.addActionListener(new java.awt.event.ActionListener() {
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
                                String id = "11";
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
                                FrameOrderProcessing.this.setState(JFrame.ICONIFIED);
                                output.close();
                                input.close();
                                clientSocket.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (flag == 1) {
                                openMenuAdmin();
                            }
                        }
                    });
                }
            });
        }
        return jButtonOrderProcessing;
    }

    private JButton getJButtonBackOrderProcessing() {
        if (jButtonBackOrderProcessing == null) {
            jButtonBackOrderProcessing = new JButton();
            jButtonBackOrderProcessing.setBounds(new Rectangle(250, 90, 100, 50));
            jButtonBackOrderProcessing.setText("Назад");
            jButtonBackOrderProcessing.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            openMenuAdmin();
                        }
                    });
                }
            });
        }
        return jButtonBackOrderProcessing;
    }

    private JButton getJButtonClearOrderProcessing() {
        if (jButtonClearOrderProcessing == null) {
            jButtonClearOrderProcessing = new JButton();
            jButtonClearOrderProcessing.setBounds(new Rectangle(140, 90, 100, 50));
            jButtonClearOrderProcessing.setText("Очистить");
            jButtonClearOrderProcessing.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            jIDFieldProduct.setText("");
                        }
                    });
                }
            });
        }
        return jButtonClearOrderProcessing;
    }

    public void processOrderAdmin() {

        initWindowOrderProcessing();

    }


    public static void initWindowOrderProcessing() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FrameOrderProcessing thisClass = new FrameOrderProcessing();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setLocationRelativeTo(null);
                thisClass.setVisible(true);
            }
        });
    }

    public FrameOrderProcessing() {
        super();
        initializeOrderProcessing();
    }

    private void initializeOrderProcessing() {
        this.setSize(400, 200);
        this.setResizable(false);
        this.setContentPane(getjContentPaneOrderProcessing());
        this.setTitle("Обработка заказа");
    }

    private JPanel getjContentPaneOrderProcessing() {
        if (jContentPaneOrderProcessing == null) {
            jContentPaneOrderProcessing = new JPanel();
            jContentPaneOrderProcessing.setLayout(null);
            jContentPaneOrderProcessing.add(getJButtonOrderProcessing(), null);
            jContentPaneOrderProcessing.add(getJButtonBackOrderProcessing(), null);
            jContentPaneOrderProcessing.add(getJButtonClearOrderProcessing(), null);
            jContentPaneOrderProcessing.add(getJLabelID(), null);
            jContentPaneOrderProcessing.add(getJTextFieldID(), null);
        }
        return jContentPaneOrderProcessing;
    }

    public void openMenuAdmin() {

        this.dispose();
        initWindowAdmin();
    }

}

