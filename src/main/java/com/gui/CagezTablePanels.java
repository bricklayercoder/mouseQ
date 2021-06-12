package com.gui;

import com.models.CageZTableModel;
// import com.sun.tools.javac.util.Name;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class CagezTablePanels extends JPanel {
    private CageZTableModel cageZTableModel=new CageZTableModel();
    private JTable cagezTable= new JTable(cageZTableModel);
    private SortCagezPanel sortCagezPanel=new SortCagezPanel();
    private CagezTableScrollPane cagezTableScrollPane=new CagezTableScrollPane();

    public CagezTablePanels() {
        super();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Cage Record"));
        this.add(sortCagezPanel, BorderLayout.NORTH);
        this.add(cagezTableScrollPane, BorderLayout.CENTER);

    }

    class SortCagezPanel extends JPanel{
        JButton syncCageButton=new JButton("Sync Cage Table");
        JLabel searchLabel=new JLabel("Search Cages");
        JTextField searchTextArea=new JTextField(20);

        public SortCagezPanel() {
            this.add(syncCageButton);
            this.add(searchLabel);
            this.add(searchTextArea);
        }
    }

    class CagezTableScrollPane extends JScrollPane{
        TableRowSorter<TableModel> cageZTableRowSorter=new TableRowSorter<>(cageZTableModel);

        public CagezTableScrollPane() {
            setPreferredSize(new Dimension(800, 400));
            cagezTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            cagezTable.setGridColor(new Color(87, 156, 23));
            cagezTable.setFillsViewportHeight(true);
            cagezTable.setRowHeight(30);
            cagezTable.setBackground(new Color(0xF7F6F3));
            cagezTable.setFont(new Font("Calibri", Font.HANGING_BASELINE, 14));
            cagezTable.getTableHeader().setBackground(new Color(193, 218, 200));
            cagezTable.getTableHeader().setForeground(new Color(0x1C1c24));
            cagezTable.getTableHeader().setFont(new Font("Calibri", Font.PLAIN, 16));
            cagezTable.setRowSorter(cageZTableRowSorter);
            setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            createVerticalScrollBar();
            setViewportView(cagezTable);



        }
    }

}
