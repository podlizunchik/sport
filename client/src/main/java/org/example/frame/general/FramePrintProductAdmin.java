package org.example.frame.general;

import org.example.page.Admin;
import org.example.table.TableModelProduct;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FramePrintProductAdmin extends Admin {

    private JPanel jContentPanePrintProductAdmin = null;
    private JButton jButtonPrintProductAdmin = null;
    private JButton jButtonBackProductAdmin = null;

    private JButton getJButtonPrintProductAdmin() {
        if (jButtonPrintProductAdmin == null) {
            jButtonPrintProductAdmin = new JButton();
            jButtonPrintProductAdmin.setBounds(new Rectangle(65, 10, 150, 50));
            jButtonPrintProductAdmin.setText("Просмотр");
            jButtonPrintProductAdmin.addActionListener(new java.awt.event.ActionListener() {
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
                                FramePrintProductAdmin.this.setState(JFrame.ICONIFIED);
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
        return jButtonPrintProductAdmin;
    }

    private JButton getJButtonBackProductAdmin() {
        if (jButtonBackProductAdmin == null) {
            jButtonBackProductAdmin = new JButton();
            jButtonBackProductAdmin.setBounds(new Rectangle(65, 70, 150, 50));
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

    public void printProductAdmin() {

        initWindowPrintProductAdmin();

    }


    public static void initWindowPrintProductAdmin() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FramePrintProductAdmin thisClass = new FramePrintProductAdmin();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setLocationRelativeTo(null);
                thisClass.setVisible(true);
            }
        });
    }

    public FramePrintProductAdmin() {
        super();
        initializeProductAdmin();
    }

    private void initializeProductAdmin() {
        this.setSize(300, 200);
        this.setResizable(false);
        this.setContentPane(getjContentPanePrintProductAdmin());
        this.setTitle("Просмотр товара");
    }

    private JPanel getjContentPanePrintProductAdmin() {
        if (jContentPanePrintProductAdmin == null) {
            jContentPanePrintProductAdmin = new JPanel();
            jContentPanePrintProductAdmin.setLayout(null);
            jContentPanePrintProductAdmin.add(getJButtonPrintProductAdmin(), null);
            jContentPanePrintProductAdmin.add(getJButtonBackProductAdmin(), null);
        }
        return jContentPanePrintProductAdmin;
    }

    public void openMenuAdmin() {

        this.dispose();
        initWindowAdmin();
    }

}
