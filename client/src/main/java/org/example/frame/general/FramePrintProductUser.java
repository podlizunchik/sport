package org.example.frame.general;

import org.example.page.User;
import org.example.table.TableModelProduct;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FramePrintProductUser extends User {

    private JPanel jContentPanePrintProductUser = null;
    private JButton jButtonPrintProductUser = null;
    private JButton jButtonBackProductUser = null;

    private JButton getJButtonPrintProductUser() {
        if (jButtonPrintProductUser == null) {
            jButtonPrintProductUser = new JButton();
            jButtonPrintProductUser.setBounds(new Rectangle(65, 10, 150, 50));
            jButtonPrintProductUser.setText("Просмотр");
            jButtonPrintProductUser.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            try {
                                System.out.println("соединение с сервером...");
                                Socket clientSocket = new Socket("127.0.0.1", 3308);
                                System.out.println("соединение установлено...");
                                ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                                String id = "6";
                                String clientMessage = id;
                                output.writeObject(clientMessage);
                                String autoMessage = "" + input.readObject();
                                TableModelProduct tmp = new TableModelProduct();
                                tmp.printProduct(autoMessage);
                                FramePrintProductUser.this.setState(JFrame.ICONIFIED);
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
        return jButtonPrintProductUser;
    }

    private JButton getJButtonBackProductUser() {
        if (jButtonBackProductUser == null) {
            jButtonBackProductUser = new JButton();
            jButtonBackProductUser.setBounds(new Rectangle(65, 70, 150, 50));
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

    public void printProductUser() {

        initWindowPrintProductUser();

    }


    public static void initWindowPrintProductUser() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FramePrintProductUser thisClass = new FramePrintProductUser();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setLocationRelativeTo(null);
                thisClass.setVisible(true);
            }
        });
    }

    public FramePrintProductUser() {
        super();
        initializeProductUser();
    }

    private void initializeProductUser() {
        this.setSize(300, 200);
        this.setResizable(false);
        this.setContentPane(getjContentPanePrintProductUser());
        this.setTitle("Просмотр товаров");
    }

    private JPanel getjContentPanePrintProductUser() {
        if (jContentPanePrintProductUser == null) {
            jContentPanePrintProductUser = new JPanel();
            jContentPanePrintProductUser.setLayout(null);
            jContentPanePrintProductUser.add(getJButtonPrintProductUser(), null);
            jContentPanePrintProductUser.add(getJButtonBackProductUser(), null);
        }
        return jContentPanePrintProductUser;
    }

    public void openMenuUser() {

        this.dispose();
        openMenuForUser();
    }

}

