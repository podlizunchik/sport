package org.example.frame.save;

import org.example.page.Admin;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FrameSaveProduct extends Admin {

    private JPanel jContentPaneSaveProduct = null;
    private JButton jButtonSaveProduct = null;
    private JButton jButtonBackProduct = null;

    private JButton getJButtonSaveProduct() {

        if (jButtonSaveProduct == null) {
            jButtonSaveProduct = new JButton();
            jButtonSaveProduct.setBounds(new Rectangle(65, 10, 150, 50));
            jButtonSaveProduct.setText("Сохранить");
            jButtonSaveProduct.addActionListener(new java.awt.event.ActionListener() {
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
                                String id = "8";
                                String clientMessage = id;
                                output.writeObject(clientMessage);
                                String autoMessage = "" + input.readObject();
                                String[] massMessage = autoMessage.split("  ");
                                flag = Integer.parseInt(massMessage[1]);
                                String message = massMessage[0];
                                JOptionPane.showMessageDialog(null, message);
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
        return jButtonSaveProduct;
    }

    private JButton getJButtonBackProduct() {
        if (jButtonBackProduct == null) {
            jButtonBackProduct = new JButton();
            jButtonBackProduct.setBounds(new Rectangle(65, 70, 150, 50));
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

    public void saveProduct() {

        initWindowSaveProduct();

    }


    public static void initWindowSaveProduct() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FrameSaveProduct thisClass = new FrameSaveProduct();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setLocationRelativeTo(null);
                thisClass.setVisible(true);
            }
        });
    }

    public FrameSaveProduct() {
        super();
        initializeProduct();
    }

    private void initializeProduct() {
        this.setSize(300, 200);
        this.setResizable(false);
        this.setContentPane(getjContentPaneSaveProduct());
        this.setTitle("Сохранение данных");
    }

    private JPanel getjContentPaneSaveProduct() {
        if (jContentPaneSaveProduct == null) {
            jContentPaneSaveProduct = new JPanel();
            jContentPaneSaveProduct.setLayout(null);
            jContentPaneSaveProduct.add(getJButtonSaveProduct(), null);
            jContentPaneSaveProduct.add(getJButtonBackProduct(), null);
        }
        return jContentPaneSaveProduct;
    }

    public void openMenuAdmin() {

        this.dispose();
        initWindowAdmin();
    }

}
