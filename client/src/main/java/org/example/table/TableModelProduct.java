package org.example.table;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TableModelProduct extends AbstractTableModel {
    private final int columnCount = 8;
    private final ArrayList<String[]> dataArrayList;

    public TableModelProduct() {
        dataArrayList = new ArrayList<>();
        for (int i = 0; i < dataArrayList.size(); i++) {
            dataArrayList.add(new String[getColumnCount()]);
        }
    }

    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "#id";
            case 1:
                return "Тип";
            case 2:
                return "Название";
            case 3:
                return "Цена";
            case 4:
                return "Количество упаковок";
            case 5:
                return "Вес упаковки";
            case 6:
                return "Производитель";
            case 7:
                return "Скидка";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    public void addData(String[] row) {
        String[] rowTable = new String[getColumnCount()];
        rowTable = row;
        dataArrayList.add(rowTable);
    }

    public void printProduct(String message) {
        JFrame frame = new JFrame("Просмотр товаров");
        frame.setSize(new Dimension(1000, 400));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());
        TableModelProduct tm = new TableModelProduct();
        String[] massMessage = message.split(" ");
        int counter = 0, i = 0;
        try {
            while (counter != massMessage.length) {
                String[] row =
                        {
                                massMessage[i], massMessage[++i], massMessage[++i], massMessage[++i], massMessage[++i],
                                massMessage[++i], massMessage[++i], massMessage[++i]
                        };
                tm.addData(row);
                counter += 8;
                i++;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Ошибка при создании таблицы");
        }
        JTable productTable = new JTable(tm);
        productTable.setAutoCreateRowSorter(true);
        JScrollPane productTableScrollPage = new JScrollPane(productTable);
        productTableScrollPage.setPreferredSize(new Dimension(1000, 400));
        frame.add(productTableScrollPage, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.setVisible(true);
        frame.pack();
    }

    public void searchProduct(String message) {

        JFrame frame = new JFrame("Просмотр");
        frame.setSize(new Dimension(1000, 400));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());
        TableModelProduct tm = new TableModelProduct();
        String[] massMessage = message.split(" ");
        int counter = 0, i = 0;
        try {
            while (counter != massMessage.length) {
                String[] row =
                        {
                                massMessage[i], massMessage[++i], massMessage[++i], massMessage[++i], massMessage[++i],
                                massMessage[++i], massMessage[++i], massMessage[++i]
                        };
                tm.addData(row);
                counter += 8;
                i++;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Ошибка при создании таблицы");
        }
        JTable productTable = new JTable(tm);
        productTable.setAutoCreateRowSorter(true);
        JScrollPane productTableScrollPage = new JScrollPane(productTable);
        productTableScrollPage.setPreferredSize(new Dimension(1000, 400));
        frame.add(productTableScrollPage, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.setVisible(true);
        frame.pack();
    }
}
