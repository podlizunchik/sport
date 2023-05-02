package org.example.page;

import org.example.Client;
import org.example.frame.business.FrameDisplayOfStatistics;
import org.example.frame.business.FrameOrderProcessing;
import org.example.frame.general.*;
import org.example.frame.save.FrameSaveProduct;
import org.example.frame.search.FrameSearchProductAdmin;

import javax.swing.*;
import java.awt.*;

public class Admin extends Client {

    private JPanel jContentPaneAdmin = null;
    private JButton jButtonBackAdmin = null;
    private JButton jButtonAddProduct = null;
    private JButton jButtonDeleteProduct = null;
    private JButton jButtonChangeProduct = null;
    private JButton jButtonPrintProductAdmin = null;
    private JButton jButtonSearchProductAdmin = null;
    private JButton jButtonSaveProduct = null;
    private JButton jButtonPrintOrder = null;
    private JButton jButtonOrderProcessing = null;
    private JButton jButtonDisplayStatistics = null;

    private JButton getJButtonBackAdmin() {
        if (jButtonBackAdmin == null) {
            jButtonBackAdmin = new JButton();
            jButtonBackAdmin.setBounds(new Rectangle(100, 460, 200, 40));
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

    private JButton getJButtonSearchProductAdmin() {
        if (jButtonSearchProductAdmin == null) {
            jButtonSearchProductAdmin = new JButton();
            jButtonSearchProductAdmin.setBounds(new Rectangle(100, 210, 200, 40));
            jButtonSearchProductAdmin.setText("Поиск");
            jButtonSearchProductAdmin.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            Admin.this.dispose();
                            FrameSearchProductAdmin frameSearchProductAdmin = new FrameSearchProductAdmin();
                            frameSearchProductAdmin.searchProductAdmin();
                        }
                    });
                }
            });
        }
        return jButtonSearchProductAdmin;
    }

    private JButton getJButtonPrintOrder() {
        if (jButtonPrintOrder == null) {
            jButtonPrintOrder = new JButton();
            jButtonPrintOrder.setBounds(new Rectangle(100, 310, 200, 40));
            jButtonPrintOrder.setText("Просмотреть заказы");
            jButtonPrintOrder.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            Admin.this.dispose();
                            FramePrintOrderAdmin framePrintOrderAdmin = new FramePrintOrderAdmin();
                            framePrintOrderAdmin.printOrderAdmin();
                        }
                    });
                }
            });
        }
        return jButtonPrintOrder;
    }

    private JButton getJButtonSaveProduct() {
        if (jButtonSaveProduct == null) {
            jButtonSaveProduct = new JButton();
            jButtonSaveProduct.setBounds(new Rectangle(100, 260, 200, 40));
            jButtonSaveProduct.setText("Сохранение");
            jButtonSaveProduct.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            Admin.this.dispose();
                            FrameSaveProduct frameSaveProduct = new FrameSaveProduct();
                            frameSaveProduct.saveProduct();
                        }
                    });
                }
            });
        }
        return jButtonSaveProduct;
    }

    private JButton getJButtonOrderProcessing() {
        if (jButtonOrderProcessing == null) {
            jButtonOrderProcessing = new JButton();
            jButtonOrderProcessing.setBounds(new Rectangle(100, 360, 200, 40));
            jButtonOrderProcessing.setText("Обработка заказа");
            jButtonOrderProcessing.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            Admin.this.dispose();
                            FrameOrderProcessing frameOrderProcessing = new FrameOrderProcessing();
                            frameOrderProcessing.processOrderAdmin();
                        }
                    });
                }
            });
        }
        return jButtonOrderProcessing;
    }

    private JButton getJButtonDisplayStatistics() {
        if (jButtonDisplayStatistics == null) {
            jButtonDisplayStatistics = new JButton();
            jButtonDisplayStatistics.setBounds(new Rectangle(100, 410, 200, 40));
            jButtonDisplayStatistics.setText("Статистика заказов");
            jButtonDisplayStatistics.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            Admin.this.dispose();
                            FrameDisplayOfStatistics frameDisplayOfStatistics = new FrameDisplayOfStatistics();
                            frameDisplayOfStatistics.displayStatistics();
                        }
                    });
                }
            });
        }
        return jButtonDisplayStatistics;
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
        this.setSize(400, 550);
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
            jContentPaneAdmin.add(getJButtonSearchProductAdmin(), null);
            jContentPaneAdmin.add(getJButtonSaveProduct(), null);
            jContentPaneAdmin.add(getJButtonPrintOrder(), null);
            jContentPaneAdmin.add(getJButtonOrderProcessing(), null);
            jContentPaneAdmin.add(getJButtonDisplayStatistics(), null);
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
