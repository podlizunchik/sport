package org.example.frame.business;

import org.example.page.Admin;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FrameDisplayOfStatistics extends Admin {

    private JPanel jContentPaneDisplayOfStatistics = null;
    private JButton jButtonDisplayOfStatistics = null;
    private JButton jButtonBack = null;

    public void printStatistics(String message){
        String [] massMessage = message.split(" ");
        int countProt = Integer.parseInt(massMessage[0]);
        int countCrea = Integer.parseInt(massMessage[1]);
        int countAmin = Integer.parseInt(massMessage[2]);
        int countBCAA = Integer.parseInt(massMessage[3]);
        int countJiro = Integer.parseInt(massMessage[4]);
        int countGein = Integer.parseInt(massMessage[5]);
        int countEner = Integer.parseInt(massMessage[6]);
        int countVita = Integer.parseInt(massMessage[7]);

        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        dataSet.setValue(countProt,"", "Протеины");
        dataSet.setValue(countCrea, "", "Креатины");
        dataSet.setValue(countAmin, "", "Аминокислоты");
        dataSet.setValue(countBCAA, "", "BCAA");
        dataSet.setValue(countJiro,"", "Жиросжигатели");
        dataSet.setValue(countGein, "", "Гейнеры");
        dataSet.setValue(countEner, "", "Энергетики");
        dataSet.setValue(countVita, "", "Витаминные комплексы");
        JFreeChart chart = ChartFactory.createBarChart("Статистика заказов по типу спортивного питания", "", "", dataSet, PlotOrientation.HORIZONTAL, false, false, false);
        CategoryPlot catPlot = chart.getCategoryPlot();
        catPlot.setRangeGridlinePaint(Color.BLACK);
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame("Статистика");
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(sSize);
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());
        panel.removeAll();
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.validate();
        frame.add(panel);
        frame.setVisible(true);
    }

    private JButton getJButtonDisplayOfStatistics() {
        if (jButtonDisplayOfStatistics == null) {
            jButtonDisplayOfStatistics = new JButton();
            jButtonDisplayOfStatistics.setBounds(new Rectangle(65, 10, 150, 50));
            jButtonDisplayOfStatistics.setText("Просмотр");
            jButtonDisplayOfStatistics.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            try {
                                System.out.println("соединение с сервером...");
                                Socket clientSocket = new Socket("127.0.0.1", 3308);
                                System.out.println("соединение установлено...");
                                ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                                String id = "12";
                                String clientMessage = id;
                                output.writeObject(clientMessage);
                                String autoMessage = "" + input.readObject();
                                printStatistics(autoMessage);
                                FrameDisplayOfStatistics.this.setState(JFrame.ICONIFIED);
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
        return jButtonDisplayOfStatistics;
    }

    private JButton getJButtonBack() {
        if (jButtonBack == null) {
            jButtonBack = new JButton();
            jButtonBack.setBounds(new Rectangle(65, 70, 150, 50));
            jButtonBack.setText("Назад");
            jButtonBack.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            openMenuAdmin();
                        }
                    });
                }
            });
        }
        return jButtonBack;
    }

    public void displayStatistics() {

        initWindowDisplayOfStatistics();

    }


    public static void initWindowDisplayOfStatistics() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FrameDisplayOfStatistics thisClass = new FrameDisplayOfStatistics();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setLocationRelativeTo(null);
                thisClass.setVisible(true);
            }
        });
    }

    public FrameDisplayOfStatistics() {
        super();
        initializeDisplayOfStatistics();
    }

    private void initializeDisplayOfStatistics() {
        this.setSize(300, 200);
        this.setResizable(false);
        this.setContentPane(getjContentPaneDisplayOfStatistics());
        this.setTitle("Просмотр статистики");
    }

    private JPanel getjContentPaneDisplayOfStatistics() {
        if (jContentPaneDisplayOfStatistics == null) {
            jContentPaneDisplayOfStatistics = new JPanel();
            jContentPaneDisplayOfStatistics.setLayout(null);
            jContentPaneDisplayOfStatistics.add(getJButtonDisplayOfStatistics(), null);
            jContentPaneDisplayOfStatistics.add(getJButtonBack(), null);
        }
        return jContentPaneDisplayOfStatistics;
    }

    public void openMenuAdmin() {

        this.dispose();
        initWindowAdmin();
    }

}
