package com.vis;

import com.cagezz.CageZZ;
import com.cagezz.Gender;
import com.javaFXGUI.AppLaunch;
import com.mouse.Mouse;
import javafx.collections.ObservableList;
import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.layout.CircleLayout;
import prefuse.action.layout.GridLayout;
import prefuse.action.layout.RandomLayout;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.action.layout.graph.NodeLinkTreeLayout;
import prefuse.action.layout.graph.RadialTreeLayout;
import prefuse.activity.Activity;
import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Graph;
import prefuse.data.Table;
import prefuse.render.*;
import prefuse.util.ColorLib;
import prefuse.util.FontLib;
import prefuse.util.PrefuseLib;
import prefuse.util.display.PaintListener;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MiceVis extends AbstractVis{

    private Table nodesTable=new Table();
    private Table edgesTable=new Table();

    Color femaleColor =Color.CYAN;
    Color maleColor =new Color(0xA2A4F5);

    HashMap<Integer, Mouse> visualMiceModel=new HashMap<>();
//    ObservableList<Mouse> miceArrayList =AppLaunch.mainFrameBorderPane.miceVBox.getObservableListOfMouse();

    ArrayList<Mouse> miceArrayList;

    CageZZ cageZZ=new CageZZ();


    public MiceVis() {
        super();
        setMinimumSize(new Dimension(700, 500));
        this.setPreferredSize(new Dimension(750, 500));
        runVis();
//        legendPanel= new LegendPanel();
    }


    @Override
    public void setupData() {

        makeVisualMiceModel();

        nodesTable.addColumn("Id", int.class);
        nodesTable.addColumn("Tag-Number", String.class);
        nodesTable.addColumn("Mother", String.class);
        nodesTable.addColumn("Father", String.class);
        nodesTable.addColumn("Genotype", String.class);
        nodesTable.addColumn("Birth-Date", String.class);
        nodesTable.addColumn("Gender", String.class);
        nodesTable.addColumn("Strain", String.class);
        nodesTable.addColumn("Coat-Color", String.class);
        nodesTable.addColumn("Wean-Date", String.class);
        nodesTable.addColumn("Cage-Number", String.class);
        nodesTable.addColumn("Status", String.class);
        nodesTable.addColumn("Notes", String.class);

        nodesTable.addRows(visualMiceModel.size());

        int row=0;
        for (Map.Entry<Integer, Mouse> entry: visualMiceModel.entrySet()){

            int col=0;
            nodesTable.set(row, col++, entry.getKey().intValue());
            nodesTable.set(row, col++, entry.getValue().getTagNumber());
            nodesTable.set(row, col++, entry.getValue().getMaternalTagNumber());
            nodesTable.set(row, col++, entry.getValue().getPaternalTagNumber());
            nodesTable.set(row, col++, entry.getValue().getGenotype());
            nodesTable.set(row, col++, entry.getValue().getBirthDate());
            nodesTable.set(row, col++, entry.getValue().getGender());
            nodesTable.set(row, col++, entry.getValue().getStrain());
            nodesTable.set(row, col++, entry.getValue().getCoatColour());
            nodesTable.set(row, col++, entry.getValue().getWeanDate());
            nodesTable.set(row, col++, entry.getValue().getCageNumber());
            nodesTable.set(row, col++, entry.getValue().getStatus());
            nodesTable.set(row, col, entry.getValue().getNotes());
            row++;
        }

        edgesTable.addColumn("Child", int.class);
        edgesTable.addColumn("Parent", int.class);

        edgesTable.addRows(2 * visualMiceModel.size());

        int rrow =0 ;
        for (Map.Entry<Integer, Mouse> entry : visualMiceModel.entrySet()) {
            int col = 0;
            edgesTable.set(rrow, col++, entry.getKey().intValue());
            edgesTable.set(rrow++,  col,   tagIdLookUp(entry.getValue().getMaternalTagNumber()) );
            col=0;
            edgesTable.set(rrow, col++, entry.getKey().intValue() );
            edgesTable.set(rrow++, col, tagIdLookUp(entry.getValue().getPaternalTagNumber()));
        }
        miceGraph=new Graph(nodesTable, edgesTable, true, "Id", "Parent", "Child");

        System.out.println("Inside set up data");
    }


    @Override
    public void setUpVisualization() {
        vis=new Visualization();
        vis.add("mice_visualization", miceGraph);
        System.out.println("inside set up visual");

    }

    @Override
    public void setUpRenderers() {

 //       FinalRenderer r=new FinalRenderer();

        FinalRenderer r=new FinalRenderer();
        DefaultRendererFactory dfr = new DefaultRendererFactory(r);

        EdgeRenderer edgeRenderer;
        edgeRenderer = new EdgeRenderer(prefuse.Constants.EDGE_TYPE_LINE,
                Constants.EDGE_ARROW_REVERSE);
        dfr.setDefaultEdgeRenderer(edgeRenderer);

        LabelRenderer labelRenderer=new LabelRenderer("Tag-Number");

        dfr.add(new InGroupPredicate("nodedec"), labelRenderer);
        vis.setRendererFactory(dfr);

        final prefuse.data.Schema DECORATOR_SCHEMA= PrefuseLib.getVisualItemSchema();
        DECORATOR_SCHEMA.setDefault(VisualItem.INTERACTIVE, false);
        DECORATOR_SCHEMA.setDefault(VisualItem.TEXTCOLOR, ColorLib.rgb(0, 200, 0));
        DECORATOR_SCHEMA.setDefault(VisualItem.FONT, FontLib.getFont("Tahoma", 16));


        vis.addDecorators("nodedec", "mice_visualization.nodes", DECORATOR_SCHEMA);

        System.out.println("inside set up visual");
    }

    @Override
    public void setUpActions() {


        int[] palette ={ColorLib.rgb(femaleColor.getRed(), femaleColor.getGreen(), femaleColor.getBlue()),
                ColorLib.rgb(maleColor.getRed(), maleColor.getGreen(), maleColor.getBlue())};

        DataColorAction fill=new DataColorAction("mice_visualization.nodes", "Gender",
                                                            Constants.NOMINAL, VisualItem.FILLCOLOR, palette);

//        ColorAction nodes=new ColorAction("mice_visualization.nodes", VisualItem.FILLCOLOR, ColorLib.gray(200));
        ColorAction edges =
                            new ColorAction("mice_visualization.edges",
                                                VisualItem.STROKECOLOR,
                                                        ColorLib.gray(200));
        ColorAction arrow = new ColorAction("mice_visualization.edges",
                VisualItem.FILLCOLOR,
                ColorLib.gray(200));

        ActionList color = new ActionList();

        color.add(fill);
//        color.add(nodes);
        color.add(edges);
        color.add(arrow);

        ActionList layout=new ActionList(Activity.INFINITY);

        layout.add(new FinalDecoratorLayout("nodedec"));

//        NodeLinkTreeLayout nodeLinkTreeLayout=new NodeLinkTreeLayout("mice_visualization");
//        layout.add(nodeLinkTreeLayout);

//        ForceDirectedLayout fdl=new ForceDirectedLayout("mice_visualization");
//        layout.add(fdl);

        RadialTreeLayout radialTreeLayout =new RadialTreeLayout("mice_visualization");
        layout.add(radialTreeLayout);

//        CircleLayout circleLayout=new CircleLayout("mice_visualization");
//        layout.add(circleLayout);

        layout.add(new RepaintAction());

        vis.putAction("color", color);
        vis.putAction("layout", layout);

        System.out.println("inside set up action");

    }

    @Override
    public void setUpDisplay() {

        dis = new Display(vis);

        dis.setSize(720, 500);

        dis.addPaintListener(new PaintListener() {
            @Override
            public void prePaint(Display display, Graphics2D graphics2D) {

            }

            @Override
            public void postPaint(Display display, Graphics2D graphics2D) {
                makeLegends(graphics2D);
            }
        });

        dis.addControlListener(new DragControl());
        dis.addControlListener(new PanControl());
        dis.addControlListener(new ZoomControl());

        dis.addControlListener(new FinalControlListener());

        System.out.println("Inside set up display");
    }

    @Override
    public void runVis() {

        setupData();
        setUpVisualization();
        setUpRenderers();
        setUpActions();
        setUpDisplay();

        dis.setBackground(Color.WHITE);
/**
        scrollDis.createVerticalScrollBar();
        scrollDis.createHorizontalScrollBar();
        scrollDis.setViewportView(dis);
*/
        containerComponent.add(dis);
        vis.run("color");
        vis.run("layout");

    }

    private void makeLegends(Graphics2D g2){

        Ellipse2D m_box=new Ellipse2D.Double();
        Double width= 30d;
        Double height= 25d;

        AffineTransform oldTransform= g2.getTransform();
        Paint oldPaint=g2.getPaint();

        AffineTransform nullTransform=new AffineTransform();

        g2.setTransform(nullTransform);
        Color femaleColor =Color.CYAN;
        Color maleColor =new Color(0xA2A4F5);

        m_box.setFrame(30d, 20d,
                width,
                height);

        g2.setPaint(femaleColor);
        g2.draw(m_box);
        g2.fill(m_box);
        g2.setPaint(Color.BLACK);
        g2.setFont(new Font("Calibri", Font.PLAIN, 18));
        g2.drawString("Female", 30, 65);

        m_box.setFrame(30d, 80d, width, height);
        g2.setPaint(maleColor);
        g2.draw(m_box);
        g2.fill(m_box);
        g2.setPaint(Color.BLACK);
        g2.drawString("Male", 30, 125);

        g2.setPaint(oldPaint);
        g2.setTransform(oldTransform);


    }



    private int tagIdLookUp(String tagNumber){
        int tagId = 0;
        for (Map.Entry entry : visualMiceModel.entrySet()){
            Mouse mouse = (Mouse) entry.getValue();
            Integer key= (Integer) entry.getKey();
            if (mouse.getTagNumber().toLowerCase().equals(tagNumber.toLowerCase())){
                tagId =  key.intValue();
                break;
            }
        }
        return tagId;
    }

    private void makeVisualMiceModel(){
        Mouse anonymMouse=new Mouse("anonym",
                "anonym",
                "anonym","anonym", Gender.FEMALE.toString(), "anonym");
        anonymMouse.setStatus("anonym");
        anonymMouse.setCageNumber("anoym");
        anonymMouse.setNotes("anonym");
        anonymMouse.setGenotype("anonym");
        anonymMouse.setWeanDate("anonym");
        anonymMouse.setCoatColour("anonym");
        cageZZ.loadMiceRecords();

        miceArrayList=cageZZ.getMiceList();

        int i=0;
        visualMiceModel.put(Integer.valueOf(i), anonymMouse);
        for (Mouse mouse : miceArrayList){
            visualMiceModel.put(Integer.valueOf(++i), mouse);
        }

    }


    public static void main(String[] args){
        JFrame jFrame=new JFrame();
        jFrame.getContentPane().add(new MiceVis());
        jFrame.setVisible(true);
    }


}
