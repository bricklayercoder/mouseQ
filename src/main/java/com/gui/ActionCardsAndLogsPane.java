package com.gui;

import javax.swing.*;
import java.awt.*;

public class ActionCardsAndLogsPane extends JSplitPane {
    LogAndTipsPane logAndTipsPane=new LogAndTipsPane();
    ActionsTabbedPane actionsTabbedPane=new ActionsTabbedPane();







    public ActionCardsAndLogsPane() {
        this.setOrientation(JSplitPane.VERTICAL_SPLIT);
        this.setTopComponent(actionsTabbedPane);
        this.setBottomComponent(logAndTipsPane);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(250, 600));
        this.setResizeWeight(0.5);
        this.setDividerLocation(0.5);
        this.setOneTouchExpandable(true);
    }

    class LogAndTipsPane extends JTabbedPane{

        public LogAndTipsPane() {
            setFont(new Font("Calibri", Font.PLAIN, 18));
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(250, 300));
            LogsPanel logsPanel=new LogsPanel();
            TipSPanel tipSPanel=new TipSPanel();
            add("Logs", logsPanel);
            add("Tips", tipSPanel);
        }

        class LogsPanel extends JScrollPane{

            public  JTextArea runSessionInfo;

            public LogsPanel() {
                super();
                runSessionInfo=new JTextArea(25, 1500);
                runSessionInfo.setEditable(false);

                runSessionInfo.setEditable(false);
                runSessionInfo.setFont(new Font("Calibri", Font.PLAIN, 16));
                runSessionInfo.setBackground(new Color(0xD7DDE9));
                runSessionInfo.setForeground(new Color(0x2727BB));
                Dimension minmumD=new Dimension(0, 0);
                this.setMinimumSize(minmumD);
                this.add(runSessionInfo);
                this.setViewportView(runSessionInfo);
            }

        }


        class TipSPanel extends JPanel{
            JTextArea tips;
            StringBuilder sb=new StringBuilder();

            public TipSPanel() {
                super();
                tips =new JTextArea(25, 25);
                tips.setFont(new Font("Calibri", Font.BOLD, 16));
                tips.setEditable(false);
                tips.setPreferredSize(new Dimension(280, 150));
                tips.setBackground(new Color(0xBEC8D4));
                tips.setForeground(new Color(0x2D2979));

                sb.append("Click each table's column header to sort record.\n");
                sb.append("Click sync mouse or cage button to get the most resent record.\n");
                tips.setText(sb.toString());

                Dimension minmumD=new Dimension(50, 50);
                this.setMinimumSize(minmumD);
                this.setPreferredSize(new Dimension(280, 150));
                this.setLayout(new BorderLayout());
                this.add(tips);

            }
        }





    }






}
