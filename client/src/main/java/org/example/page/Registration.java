package org.example.page;

import org.example.Client;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends Client {
    private JPanel jContentPaneReg = null;
    private JButton jButtonReg = null;
    private JButton jButtonClearReg = null;
    private JButton jButtonBackReg = null;
    private JTextField jLoginFieldReg = null;
    private JPasswordField jPassFieldReg = null;
    private JTextField jEmailFieldReg = null;
    private JLabel jLabelLogin = null;
    private JLabel jLabelPass = null;
    private JLabel jLabelEmail = null;

    private JLabel getJLabelLogin() {
        if (jLabelLogin == null) {
            jLabelLogin = new JLabel("Введите логин:");
            jLabelLogin.setBounds(new Rectangle(10, 10, 100, 10));
        }
        return jLabelLogin;
    }

    private JLabel getJLabelPass() {
        if (jLabelPass == null) {
            jLabelPass = new JLabel("Введите пароль(от 8 до 16 символов):");
            jLabelPass.setBounds(new Rectangle(10, 50, 300, 10));
        }
        return jLabelPass;
    }

    private JLabel getJLabelEmail() {
        if (jLabelEmail == null) {
            jLabelEmail = new JLabel("Введите e-mail:");
            jLabelEmail.setBounds(new Rectangle(10, 90, 100, 10));
        }
        return jLabelEmail;
    }

    private JTextField getJTextFieldLogin() {
        if (jLoginFieldReg == null) {
            jLoginFieldReg = new JTextField();
            jLoginFieldReg.setBounds(new Rectangle(10, 25, 250, 20));
        }
        return jLoginFieldReg;
    }

    private JPasswordField getJTextFieldPass() {
        if (jPassFieldReg == null) {
            jPassFieldReg = new JPasswordField();
            jPassFieldReg.setBounds(new Rectangle(10, 65, 250, 20));
        }
        return jPassFieldReg;
    }

    private JTextField getJTextFieldEmail() {
        if (jEmailFieldReg == null) {
            jEmailFieldReg = new JTextField();
            jEmailFieldReg.setBounds(new Rectangle(10, 105, 250, 20));
        }
        return jEmailFieldReg;
    }

    private JButton getJButtonClearReg() {
        if (jButtonClearReg == null) {
            jButtonClearReg = new JButton();
            jButtonClearReg.setBounds(new Rectangle(170, 150, 150, 30));
            jButtonClearReg.setText("Очистить");
            jButtonClearReg.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            jLoginFieldReg.setText("");
                            jPassFieldReg.setText("");
                            jEmailFieldReg.setText("");
                        }
                    });
                }
            });
        }
        return jButtonClearReg;
    }

    private JButton getJButtonBackReg() {
        if (jButtonBackReg == null) {
            jButtonBackReg = new JButton();
            jButtonBackReg.setBounds(new Rectangle(330, 150, 150, 30));
            jButtonBackReg.setText("Назад");
            jButtonBackReg.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            openNewWindow();
                        }
                    });
                }
            });
        }
        return jButtonBackReg;
    }

    private JButton getJButtonReg() {
        if (jButtonReg == null) {
            jButtonReg = new JButton();
            jButtonReg.setBounds(new Rectangle(10, 150, 150, 30));
            jButtonReg.setText("Зарегистрировать");
            jButtonReg.addActionListener(new java.awt.event.ActionListener() {
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
                                String id = "1";
                                String login = jLoginFieldReg.getText();
                                String password = jPassFieldReg.getText();
                                String email = jEmailFieldReg.getText();
                                Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                                Matcher m = p.matcher(email);
                                boolean isEmail = m.matches();
                                System.out.println(isEmail);
                                if (login.length() == 0 || password.length() == 0 || email.length() == 0) {
                                    JOptionPane.showMessageDialog(null, "Ошибка!!! Все поля должны быть заполнены!");
                                } else if (password.length() < 8 || password.length() > 16) {
                                    JOptionPane.showMessageDialog(null, "Ошибка!!! Пароль должен содержать не менее 8 и не более 16 символов!");
                                } else if (isEmail == false) {
                                    JOptionPane.showMessageDialog(null, "Некорректно введен e-mail");
                                } else {
                                    String clientMessage = id + " " + login + " " + password + " " + email;
                                    output.writeObject(clientMessage);
                                    String autoMessage = "" + input.readObject();
                                    String[] massMessage = autoMessage.split("  ");
                                    flag = Integer.parseInt(massMessage[1]);
                                    String message = massMessage[0];
                                    JOptionPane.showMessageDialog(null, message);
                                }
                                output.close();
                                input.close();
                                clientSocket.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (flag == 1) openNewWindow();
                        }
                    });
                }
            });
        }
        return jButtonReg;
    }

    public static void initWindowReg() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Registration thisClass = new Registration();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setLocationRelativeTo(null);
                thisClass.setVisible(true);
            }
        });
    }

    public Registration() {
        super();
        initialize();
    }

    private void initialize() {
        this.setSize(500, 300);
        this.setResizable(false);
        this.setContentPane(getJContentPane());
        this.setTitle("Регистрация");
    }

    private JPanel getJContentPane() {
        if (jContentPaneReg == null) {
            jContentPaneReg = new JPanel();
            jContentPaneReg.setLayout(null);
            jContentPaneReg.add(getJButtonReg(), null);
            jContentPaneReg.add(getJButtonClearReg(), null);
            jContentPaneReg.add(getJButtonBackReg(), null);
            jContentPaneReg.add(getJTextFieldLogin(), null);
            jContentPaneReg.add(getJTextFieldPass(), null);
            jContentPaneReg.add(getJTextFieldEmail(), null);
            jContentPaneReg.add(getJLabelLogin(), null);
            jContentPaneReg.add(getJLabelPass(), null);
            jContentPaneReg.add(getJLabelEmail(), null);
        }
        return jContentPaneReg;
    }

    public void OpenWindowReg() {
        initWindowReg();
    }

    public void openNewWindow() {
        this.dispose();
        initWindow();
    }
}
