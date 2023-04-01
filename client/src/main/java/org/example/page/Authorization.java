package org.example.page;

import org.example.Client;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Authorization extends Client {
    private JPanel jContentPaneAut = null;
    private JButton jButtonAut = null;
    private JButton jButtonClearAut = null;
    private JButton jButtonBackAut = null;
    private JTextField jLoginFieldAut = null;
    private JPasswordField jPassFieldAut = null;
    private JLabel jLabelLogin = null;
    private JLabel jLabelPass = null;

    private JLabel getJLabelLogin() {
        if (jLabelLogin == null) {
            jLabelLogin = new JLabel("Введите логин:");
            jLabelLogin.setBounds(new Rectangle(10, 10, 100, 10));
        }
        return jLabelLogin;
    }

    private JLabel getJLabelPass() {
        if (jLabelPass == null) {
            jLabelPass = new JLabel("Введите пароль:");
            jLabelPass.setBounds(new Rectangle(10, 50, 100, 10));
        }
        return jLabelPass;
    }

    private JTextField getJTextFieldLogin() {
        if (jLoginFieldAut == null) {
            jLoginFieldAut = new JTextField();
            jLoginFieldAut.setBounds(new Rectangle(10, 25, 250, 20));
        }
        return jLoginFieldAut;
    }

    private JPasswordField getJTextFieldPass() {
        if (jPassFieldAut == null) {
            jPassFieldAut = new JPasswordField();
            jPassFieldAut.setBounds(new Rectangle(10, 65, 250, 20));
        }
        return jPassFieldAut;
    }

    private JButton getJButtonClearAut() {
        if (jButtonClearAut == null) {
            jButtonClearAut = new JButton();
            jButtonClearAut.setBounds(new Rectangle(170, 150, 150, 30));
            jButtonClearAut.setText("Очистить");
            jButtonClearAut.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            jLoginFieldAut.setText("");
                            jPassFieldAut.setText("");
                        }
                    });
                }
            });
        }
        return jButtonClearAut;
    }

    private JButton getJButtonBackAut() {
        if (jButtonBackAut == null) {
            jButtonBackAut = new JButton();
            jButtonBackAut.setBounds(new Rectangle(330, 150, 150, 30));
            jButtonBackAut.setText("Назад");
            jButtonBackAut.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            openNewWindow();
                        }
                    });
                }
            });
        }
        return jButtonBackAut;
    }

    private JButton getJButtonAut() {
        if (jButtonAut == null) {
            jButtonAut = new JButton();
            jButtonAut.setBounds(new Rectangle(10, 150, 150, 30));
            jButtonAut.setText("Авторизоваться");
            jButtonAut.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            int flagError = 0;
                            int flag = 0;
                            try {
                                System.out.println("соединение с сервером...");
                                Socket clientSocket = new Socket("127.0.0.1", 3308);
                                System.out.println("соединение установлено...");
                                ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                                String id = "2";
                                String login = jLoginFieldAut.getText();
                                String password = jPassFieldAut.getText();
                                if (login.equals("admin") && password.equals("adminadmin")) {
                                    flag = 1;
                                } else flag = 0;
                                if (login.length() == 0 || password.length() == 0) {
                                    JOptionPane.showMessageDialog(null, "Ошибка!!! Все поля должны быть заполнены!");
                                } else {
                                    String clientMessage = id + " " + login + " " + password;
                                    output.writeObject(clientMessage);
                                    String autoMessage = "" + input.readObject();
                                    String[] massMessage = autoMessage.split("  ");
                                    flagError = Integer.parseInt(massMessage[1]);
                                    String message = massMessage[0];
                                    JOptionPane.showMessageDialog(null, message);
                                }
                                output.close();
                                input.close();
                                clientSocket.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (flagError == 0)
                                openNewWindowAut();
                            else if (flag == 1)
                                openAdminWindow();
                            else openUserWindow();
                        }
                    });
                }
            });
        }
        return jButtonAut;
    }

    public static void initWindowAut() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Authorization thisClass = new Authorization();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setLocationRelativeTo(null);
                thisClass.setVisible(true);
            }
        });
    }

    public Authorization() {
        super();
        initialize();
    }

    private void initialize() {
        this.setSize(500, 300);
        this.setResizable(false);
        this.setContentPane(getJContentPane());
        this.setTitle("Авторизация");
    }

    private JPanel getJContentPane() {
        if (jContentPaneAut == null) {
            jContentPaneAut = new JPanel();
            jContentPaneAut.setLayout(null);
            jContentPaneAut.add(getJButtonAut(), null);
            jContentPaneAut.add(getJButtonClearAut(), null);
            jContentPaneAut.add(getJButtonBackAut(), null);
            jContentPaneAut.add(getJTextFieldLogin(), null);
            jContentPaneAut.add(getJTextFieldPass(), null);
            jContentPaneAut.add(getJLabelLogin(), null);
            jContentPaneAut.add(getJLabelPass(), null);
        }
        return jContentPaneAut;
    }

    public void openNewWindow() {
        this.dispose();
        initWindow();
    }

    public void openWindowAut() {
        initWindowAut();
    }

    public void openNewWindowAut() {
        this.dispose();
        initWindowAut();
    }

    public void openAdminWindow() {
        this.dispose();
        Admin admin = new Admin();
        admin.openMenuForAdmin();
    }

    public void openUserWindow() {
        this.dispose();
        User user = new User();
        user.openMenuForUser();
    }

}
