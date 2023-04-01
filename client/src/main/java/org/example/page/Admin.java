package org.example.page;

import org.example.Client;
import org.example.frame.general.FrameAddProduct;
import org.example.frame.general.FrameChangeProduct;
import org.example.frame.general.FrameDeleteProduct;
import org.example.frame.general.FramePrintProductAdmin;

import javax.swing.*;
import java.awt.*;

public class Admin extends Client {

    private JPanel jContentPaneAdmin = null;
    private JButton jButtonBackAdmin = null;
    private JButton jButtonAddProduct = null;
    private JButton jButtonDeleteProduct = null;
    private JButton jButtonChangeProduct = null;
    private JButton jButtonPrintProductAdmin = null;

    private JButton getJButtonBackAdmin() {
        if (jButtonBackAdmin == null) {
            jButtonBackAdmin = new JButton();
            jButtonBackAdmin.setBounds(new Rectangle(100, 260, 200, 40));
            jButtonBackAdmin.setText("Назад");
            jButtonBackAdmin.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            openNewWindow();
                        }
                    });
                }
            });
        }
        return jButtonBackAdmin;
    }

    private JButton getJButtonAddProduct() {
        if (jButtonAddProduct == null) {
            jButtonAddProduct = new JButton();
            jButtonAddProduct.setBounds(new Rectangle(100, 10, 200, 40));
            jButtonAddProduct.setText("Добавить товар");
            jButtonAddProduct.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            Admin.this.dispose();
                            FrameAddProduct frameAddProduct = new FrameAddProduct();
                            frameAddProduct.insertProduct();
                        }
                    });
                }
            });
        }
        return jButtonAddProduct;
    }

    private JButton getJButtonDeleteProduct() {
        if (jButtonDeleteProduct == null) {
            jButtonDeleteProduct = new JButton();
            jButtonDeleteProduct.setBounds(new Rectangle(100, 60, 200, 40));
            jButtonDeleteProduct.setText("Удалить товар");
            jButtonDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            Admin.this.dispose();
                            FrameDeleteProduct frameDeleteProduct = new FrameDeleteProduct();
                            frameDeleteProduct.deleteProduct();
                        }
                    });
                }
            });
        }
        return jButtonDeleteProduct;
    }

    private JButton getJButtonChangeProduct() {
        if (jButtonChangeProduct == null) {
            jButtonChangeProduct = new JButton();
            jButtonChangeProduct.setBounds(new Rectangle(100, 110, 200, 40));
            jButtonChangeProduct.setText("Изменить товар");
            jButtonChangeProduct.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            Admin.this.dispose();
                            FrameChangeProduct frameChangeProduct = new FrameChangeProduct();
                            frameChangeProduct.changeProduct();
                        }
                    });
                }
            });
        }
        return jButtonChangeProduct;
    }

    private JButton getJButtonPrintProductAdmin() {
        if (jButtonPrintProductAdmin == null) {
            jButtonPrintProductAdmin = new JButton();
            jButtonPrintProductAdmin.setBounds(new Rectangle(100, 160, 200, 40));
            jButtonPrintProductAdmin.setText("Просмотреть товар");
            jButtonPrintProductAdmin.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            Admin.this.dispose();
                            FramePrintProductAdmin framePrintProductAdmin = new FramePrintProductAdmin();
                            framePrintProductAdmin.printProductAdmin();
                        }
                    });
                }
            });
        }
        return jButtonPrintProductAdmin;
    }

    public static void initWindowAdmin() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Admin thisClass = new Admin();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setLocationRelativeTo(null);
                thisClass.setVisible(true);
            }
        });
    }

    public Admin() {
        super();
        initialize();
    }

    private void initialize() {
        this.setSize(400, 350);
        this.setResizable(false);
        this.setContentPane(getJContentPane());
        this.setTitle("Меню администратора");
    }

    private JPanel getJContentPane() {
        if (jContentPaneAdmin == null) {
            jContentPaneAdmin = new JPanel();
            jContentPaneAdmin.setLayout(null);
            jContentPaneAdmin.add(getJButtonBackAdmin(), null);
            jContentPaneAdmin.add(getJButtonAddProduct(), null);
            jContentPaneAdmin.add(getJButtonDeleteProduct(), null);
            jContentPaneAdmin.add(getJButtonChangeProduct(), null);
            jContentPaneAdmin.add(getJButtonPrintProductAdmin(), null);
        }
        return jContentPaneAdmin;
    }

    public void openMenuForAdmin() {

        initWindowAdmin();
    }

    public void openNewWindow() {

        this.dispose();
        initWindow();
    }

}
