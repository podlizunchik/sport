package org.example.frame.general;

import org.example.page.Admin;
import org.example.table.TableModelOrder;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FramePrintOrderAdmin extends Admin {

    private JPanel jContentPanePrintOrderAdmin = null;
    private JButton jButtonPrintOrderAdmin = null;
    private JButton jButtonBackOrderAdmin = null;

    private JButton getJButtonPrintOrderAdmin() {
        if (jButtonPrintOrderAdmin == null) {
            jButtonPrintOrderAdmin = new JButton();
            jButtonPrintOrderAdmin.setBounds(new Rectangle(65, 10, 150, 50));
            jButtonPrintOrderAdmin.setText("Просмотр");
            jButtonPrintOrderAdmin.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            try {
                                System.out.println("соединение с сервером...");
                                Socket clientSocket = new Socket("127.0.0.1", 3308);
                                System.out.println("соединение установлено...");
                                ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                                String id = "10";
                                String clientMessage = id;
                                output.writeObject(clientMessage);
                                String autoMessage = "" + input.readObject();
                                TableModelOrder tmp = new TableModelOrder();
                                tmp.printOrder(autoMessage);
                                FramePrintOrderAdmin.this.setState(JFrame.ICONIFIED);
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
        return jButtonPrintOrderAdmin;
    }

    private JButton getJButtonBackOrderAdmin() {
        if (jButtonBackOrderAdmin == null) {
            jButtonBackOrderAdmin = new JButton();
            jButtonBackOrderAdmin.setBounds(new Rectangle(65, 70, 150, 50));
            jButtonBackOrderAdmin.setText("Назад");
            jButtonBackOrderAdmin.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            openMenuAdmin();
                        }
                    });
                }
            });
        }
        return jButtonBackOrderAdmin;
    }

    public void printOrderAdmin() {

        initWindowPrintOrderAdmin();

    }


    public static void initWindowPrintOrderAdmin() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FramePrintOrderAdmin thisClass = new FramePrintOrderAdmin();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setLocationRelativeTo(null);
                thisClass.setVisible(true);
            }
        });
    }

    public FramePrintOrderAdmin() {
        super();
        initializeOrderAdmin();
    }

    private void initializeOrderAdmin() {
        this.setSize(300, 200);
        this.setResizable(false);
        this.setContentPane(getjContentPanePrintOrderAdmin());
        this.setTitle("Просмотр заказов");
    }

    private JPanel getjContentPanePrintOrderAdmin() {
        if (jContentPanePrintOrderAdmin == null) {
            jContentPanePrintOrderAdmin = new JPanel();
            jContentPanePrintOrderAdmin.setLayout(null);
            jContentPanePrintOrderAdmin.add(getJButtonPrintOrderAdmin(), null);
            jContentPanePrintOrderAdmin.add(getJButtonBackOrderAdmin(), null);
        }
        return jContentPanePrintOrderAdmin;
    }

    public void openMenuAdmin() {

        this.dispose();
        initWindowAdmin();
    }

}
