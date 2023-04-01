package org.example.page;

import org.example.Client;
import org.example.frame.business.FrameCalculateCost;
import org.example.frame.business.FrameDisplayManufacturerStatistic;
import org.example.frame.general.FramePrintProductUser;
import org.example.frame.search.FrameSearchProductUser;

import javax.swing.*;
import java.awt.*;

public class User extends Client {

    private JPanel jContentPaneUser = null;
    private JButton jButtonBackUser = null;
    private JButton jButtonPrintProductUser = null;
    private JButton jButtonSearchProductUser = null;
    private JButton jButtonCalculateProductUser = null;
    private JButton jButtonDisplayManufacturerStatistics = null;

    private JButton getJButtonPrintProductUser() {
        if (jButtonPrintProductUser == null) {
            jButtonPrintProductUser = new JButton();
            jButtonPrintProductUser.setBounds(new Rectangle(100, 10, 200, 40));
            jButtonPrintProductUser.setText("Просмотреть товар");
            jButtonPrintProductUser.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            User.this.dispose();
                            FramePrintProductUser framePrintProductUser = new FramePrintProductUser();
                            framePrintProductUser.printProductUser();
                        }
                    });
                }
            });
        }
        return jButtonPrintProductUser;
    }

    private JButton getJButtonSearchProductUser() {
        if (jButtonSearchProductUser == null) {
            jButtonSearchProductUser = new JButton();
            jButtonSearchProductUser.setBounds(new Rectangle(100, 60, 200, 40));
            jButtonSearchProductUser.setText("Поиск");
            jButtonSearchProductUser.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            User.this.dispose();
                            FrameSearchProductUser frameSearchProduct = new FrameSearchProductUser();
                            frameSearchProduct.searchProductUser();
                        }
                    });
                }
            });
        }
        return jButtonSearchProductUser;
    }

    private JButton getJButtonBackUser() {
        if (jButtonBackUser == null) {
            jButtonBackUser = new JButton();
            jButtonBackUser.setBounds(new Rectangle(100, 210, 200, 40));
            jButtonBackUser.setText("Назад");
            jButtonBackUser.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            openNewWindow();
                        }
                    });
                }
            });
        }
        return jButtonBackUser;
    }

    private JButton getJButtonCalculateProductUser() {
        if (jButtonCalculateProductUser == null) {
            jButtonCalculateProductUser = new JButton();
            jButtonCalculateProductUser.setBounds(new Rectangle(100, 110, 200, 40));
            jButtonCalculateProductUser.setText("Заказать");
            jButtonCalculateProductUser.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            User.this.dispose();
                            FrameCalculateCost frameCalculateCost = new FrameCalculateCost();
                            frameCalculateCost.calculateProductUser();
                        }
                    });
                }
            });
        }
        return jButtonCalculateProductUser;
    }

    private JButton getJButtonDisplayManufacturerStatistics() {
        if (jButtonDisplayManufacturerStatistics == null) {
            jButtonDisplayManufacturerStatistics = new JButton();
            jButtonDisplayManufacturerStatistics.setBounds(new Rectangle(100, 160, 200, 40));
            jButtonDisplayManufacturerStatistics.setText("Статистика производителей");
            jButtonDisplayManufacturerStatistics.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            User.this.dispose();
                            FrameDisplayManufacturerStatistic frameDisplayManufacturerStatistic = new FrameDisplayManufacturerStatistic();
                            frameDisplayManufacturerStatistic.displayManufacturerStatistics();
                        }
                    });
                }
            });
        }
        return jButtonDisplayManufacturerStatistics;
    }


    public static void initWindowUser() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                User thisClass = new User();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setLocationRelativeTo(null);
                thisClass.setVisible(true);
            }
        });
    }

    public User() {
        super();
        initialize();
    }

    private void initialize() {
        this.setSize(400, 400);
        this.setResizable(false);
        this.setContentPane(getJContentPane());
        this.setTitle("Меню пользователя");
    }

    private JPanel getJContentPane() {
        if (jContentPaneUser == null) {
            jContentPaneUser = new JPanel();
            jContentPaneUser.setLayout(null);
            jContentPaneUser.add(getJButtonBackUser(), null);
            jContentPaneUser.add(getJButtonPrintProductUser(), null);
            jContentPaneUser.add(getJButtonSearchProductUser(), null);
            jContentPaneUser.add(getJButtonCalculateProductUser(), null);
            jContentPaneUser.add(getJButtonDisplayManufacturerStatistics(), null);
        }
        return jContentPaneUser;
    }

    public void openMenuForUser() {

        initWindowUser();
    }

    public void openNewWindow() {

        this.dispose();
        initWindow();
    }

}
