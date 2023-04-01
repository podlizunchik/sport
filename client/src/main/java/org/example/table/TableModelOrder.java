package org.example.table;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TableModelOrder extends AbstractTableModel {

    private final int columnCount = 10;
    private final ArrayList<String[]> dataArrayList;

    public TableModelOrder() {
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
                return "Регион";
            case 2:
                return "Тип";
            case 3:
                return "Название";
            case 4:
                return "КоличествоУпаковок";
            case 5:
                return "ВесУпаковки";
            case 6:
                return "Вкус";
            case 7:
                return "Скидка";
            case 8:
                return "Стоимость";
            case 9:
                return "Статус";

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

    public static void printOrder(String message) {
        JFrame frame = new JFrame("Просмотр заказов");
        frame.setSize(new Dimension(1000, 400));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());
        TableModelOrder tm = new TableModelOrder();
        String[] massMessage = message.split(" ");
        int counter = 0, i = 0;
        try {
            while (counter != massMessage.length) {
                String[] row =
                        {
                                massMessage[i], massMessage[++i], massMessage[++i], massMessage[++i], massMessage[++i],
                                massMessage[++i], massMessage[++i], massMessage[++i], massMessage[++i], massMessage[++i]
                        };
                tm.addData(row);
                counter += 10;
                i++;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Ошибка при создании таблицы");
        }
        JTable orderTable = new JTable(tm);
        orderTable.setAutoCreateRowSorter(true);
        JScrollPane orderTableScrollPage = new JScrollPane(orderTable);
        orderTableScrollPage.setPreferredSize(new Dimension(1000, 400));
        frame.add(orderTableScrollPage, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.setVisible(true);
        frame.pack();
    }

}
