package com.gui;

import javax.swing.*;
import java.awt.*;

public class ActionsTabbedPane extends JTabbedPane {
    AddMousePanel addMousePanel=new AddMousePanel();
    UpdateMousePanel updateMousePanel=new UpdateMousePanel();

    public ActionsTabbedPane() {
        setFont(new Font("Calibri", Font.PLAIN, 18));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(250, 380));
        add("Add Mouse", addMousePanel);
        add("Update Mouse", updateMousePanel);
    }
}
