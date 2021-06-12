package com.gui;

import com.models.MiceTableModel;
import javafx.scene.control.Tab;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class MiceTablePanels extends JPanel {
    private MiceTableModel miceTableModel=new MiceTableModel();
    private JTable miceTable=new JTable(miceTableModel);
    private SortMicePanel sortMicePanel=new SortMicePanel();
    private MiceTableScrollPane miceTableScrollPane=new MiceTableScrollPane();

    public MiceTablePanels() {
        super();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Mice Record"));
        this.add(sortMicePanel, BorderLayout.NORTH);
        this.add(miceTableScrollPane, BorderLayout.CENTER);
    }

    class SortMicePanel extends JPanel{
        JButton syncMiceTable=new JButton("Sync Mice Table");
        JLabel searchLabel=new JLabel("Search Mice");
        JTextField textField =new JTextField(20);

        public SortMicePanel() {
            this.add(syncMiceTable);
            this.add(searchLabel);
            this.add(textField);
        }
    }

    class MiceTableScrollPane extends JScrollPane{
        TableRowSorter<TableModel> miceRowSorter=new TableRowSorter<>(miceTableModel);
        public MiceTableScrollPane() {
            setPreferredSize(new Dimension(800, 600));
            miceTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            miceTable.setGridColor(new Color(87, 156, 23));
            miceTable.setFillsViewportHeight(true);
            miceTable.setRowHeight(30);
            miceTable.setBackground(new Color(0xF7F6F3));
            miceTable.setFont(new Font("Calibri", Font.HANGING_BASELINE, 14));
            miceTable.getTableHeader().setBackground(new Color(193, 218, 200, 247));
            miceTable.getTableHeader().setForeground(new Color(0x1C1C24));
            miceTable.getTableHeader().setFont(new Font("Calibri", Font.PLAIN, 16));
            miceTable.setRowSorter(miceRowSorter);


 //           miceTable.setDefaultRenderer(Object.class, new MiceTableRenderer());
 //           miceTable.setDefaultRenderer(java.sql.Date.class, new MiceTableRenderer());

            setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            createVerticalScrollBar();
            setViewportView(miceTable);

        }
    }

}
