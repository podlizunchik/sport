package org.example;

import org.example.page.Authorization;
import org.example.page.Registration;

import javax.swing.*;
import java.awt.*;

public class Client extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JButton jButtonReg = null;
    private JButton jButtonAut = null;
    private static final Registration reg = new Registration();
    private static final Authorization aut = new Authorization();

    public static void main(String[] args) {
        initWindow();
    }

    public Client() {
        super();
        initialize();
    }

    public static void initWindow() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Client thisClass = new Client();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setLocationRelativeTo(null);
                thisClass.setVisible(true);
            }
        });
    }

    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJButtonReg(), null);
            jContentPane.add(getJButtonAut(), null);
        }
        return jContentPane;
    }

    private void initialize() {
        this.setSize(500, 300);
        this.setResizable(false);
        this.setContentPane(getJContentPane());
        this.setTitle("Главное меню");
    }

    private JButton getJButtonReg() {
        if (jButtonReg == null) {
            jButtonReg = new JButton();
            jButtonReg.setBounds(new Rectangle(90, 100, 150, 60));
            jButtonReg.setText("Регистрация");
            jButtonReg.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            Client.this.dispose();
                            reg.OpenWindowReg();
                        }
                    });
                }
            });
        }
        return jButtonReg;
    }

    private JButton getJButtonAut() {
        if (jButtonAut == null) {
            jButtonAut = new JButton();
            jButtonAut.setBounds(new Rectangle(250, 100, 150, 60));
            jButtonAut.setText("Авторизация");
            jButtonAut.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            Client.this.dispose();
                            aut.openWindowAut();
                        }
                    });
                }
            });
        }
        return jButtonAut;
    }
}
