package com.gui;


import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
     private NestingSplitPanes nestPanes=new NestingSplitPanes();
     private static JMenuBar menuBar;
     private static JMenu    menu;           //mouseQ menu
     private static JMenuItem[] menuItems;

     private static JMenu fileMenu;
     private static JMenu userMenu;
     private static JMenu dbMenu;


     private static JMenuItem[] fileMenuItems;
     private static JMenuItem[] userMenuItems;
     private static JMenuItem[] dbMenuItems;

     public MainFrame() throws HeadlessException {
          super("mouseQ");
          makeJMenuBar();
          menuBar.add(menu);
          menuBar.add(fileMenu);
          menuBar.add(userMenu);
          menuBar.add(dbMenu);
          this.setJMenuBar(menuBar);

          this.setLayout(new BorderLayout());
          this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          this.setPreferredSize(new Dimension(960, 750));
          this.setBackground(new Color(0x535850));


          Container contentPane=this.getContentPane();
          contentPane.add(nestPanes, BorderLayout.CENTER);

          this.pack();
          this.setVisible(true);

     }

     private void makeJMenuBar(){
          menuBar =new JMenuBar();
          menu =new JMenu("mouseQ");
          menu.setBorderPainted(true);
          menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
          menu.setForeground(Color.BLACK);
          menu.setBackground(new Color(0xD2F3BF));
          menu.setFont(new Font("Calibri", Font.BOLD, 15));


          menuItems =new JMenuItem[2];

          menuItems[0] = new JMenuItem("About");
          menuItems[0].setBorder(BorderFactory.createLineBorder(Color.BLACK));
          menuItems[0].setBorderPainted(true);
          menuItems[0].setForeground(Color.BLACK);
          menuItems[0].setBackground(new Color(0xD2F3BF));
          menuItems[0].setFont(new Font("Calibri", Font.BOLD, 15));


          menuItems[1] = new JMenuItem("Exit");
          menuItems[1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
          menuItems[1].setBorderPainted(true);
          menuItems[1].setBackground(new Color(0xD2F3BF));
          menuItems[1].setForeground(Color.BLACK);
          menuItems[1].setFont(new Font("Calibri", Font.BOLD, 15));
          menu.add(menuItems[0]);
          menu.add(menuItems[1]);

          /**
           * For fileMenu
           */
          fileMenu=new JMenu("  File  ");
          makeMenu(fileMenu);
          fileMenuItems = new JMenuItem[2];
          fileMenuItems[0] = new JMenuItem("Save");
          fileMenuItems[1] = new JMenuItem("Open");
          makeMenuItem(fileMenuItems[0]);
          makeMenuItem(fileMenuItems[1]);
          fileMenu.add(fileMenuItems[0]);
          fileMenu.add(fileMenuItems[1]);


          userMenu=new JMenu(" User  ");
          makeMenu(userMenu);
          userMenuItems= new JMenuItem[2];
          userMenuItems[0] = new JMenuItem("Add User");

          userMenuItems[1] = new JMenuItem("Change PIN");
          makeMenuItem(userMenuItems[0]);
          makeMenuItem(userMenuItems[1]);
          userMenu.add(userMenuItems[0]);
          userMenu.add(userMenuItems[1]);

          dbMenu=new JMenu("Database");
          makeMenu(dbMenu);
          dbMenuItems=new JMenuItem[2];
          dbMenuItems[0]=new JMenuItem("Initialize");
          dbMenuItems[1]=new JMenuItem("Test connection");
          makeMenuItem(dbMenuItems[0]);
               dbMenuItems[0].setEnabled(false);

          makeMenuItem(dbMenuItems[1]);
          dbMenu.add(dbMenuItems[0]);
          dbMenu.add(dbMenuItems[1]);
     }

     private void makeMenu(JMenu menu){

          menu.setBorderPainted(true);
          menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
          menu.setForeground(Color.BLACK);
          menu.setBackground(new Color(0xD2F3BF));
          menu.setFont(new Font("Calibri", Font.BOLD, 15));


     }
     public void makeMenuItem(JMenuItem item){
          item.setBorder(BorderFactory.createLineBorder(Color.BLACK));
          item.setBorderPainted(true);
          item.setForeground(Color.BLACK);
          item.setBackground(new Color(0xD2F3BF));
          item.setFont(new Font("Calibri", Font.BOLD, 14));

     }

     public static void main(String[] args){
          SwingUtilities.invokeLater(new Runnable() {
               @Override
               public void run() {
                    new MainFrame();
               }
          });
     }


}
