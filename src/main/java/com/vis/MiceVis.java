package com.vis;

import com.cagezz.CageZZ;
import com.cagezz.Gender;
import com.mouse.Mouse;
import javafx.collections.ObservableList;
import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.GroupAction;
import prefuse.action.ItemAction;
import prefuse.action.RepaintAction;
import prefuse.action.animate.ColorAnimator;
import prefuse.action.animate.PolarLocationAnimator;
import prefuse.action.animate.QualityControlAnimator;
import prefuse.action.animate.VisibilityAnimator;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.assignment.FontAction;
import prefuse.action.layout.CollapsedSubtreeLayout;
import prefuse.action.layout.graph.RadialTreeLayout;
import prefuse.activity.Activity;
import prefuse.activity.SlowInSlowOutPacer;
import prefuse.controls.*;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Table;
import prefuse.data.Tuple;
import prefuse.data.event.TupleSetListener;
import prefuse.data.query.SearchQueryBinding;
import prefuse.data.search.PrefixSearchTupleSet;
import prefuse.data.search.SearchTupleSet;
import prefuse.data.tuple.DefaultTupleSet;
import prefuse.data.tuple.TupleSet;
import prefuse.render.*;
import prefuse.util.ColorLib;
import prefuse.util.FontLib;
import prefuse.util.PrefuseLib;
import prefuse.util.display.PaintListener;
import prefuse.util.ui.JFastLabel;
import prefuse.util.ui.JSearchPanel;
import prefuse.util.ui.UILib;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;
import prefuse.visual.sort.TreeDepthItemSorter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MiceVis extends AbstractVis{

    private Table nodesTable=new Table();
    private Table edgesTable=new Table();

    Color femaleColor =Color.CYAN;
    Color maleColor =new Color(0xA2A4F5);

    HashMap<Integer, Mouse> visualMiceModel=new HashMap<>();
    ObservableList<Mouse> miceArrayList ;

    CageZZ cageZZ=new CageZZ();
    JButton backColorButton=new JButton("Back-To-Original-Color");

    String tree="mice_visualization";
    String treeNodes="mice_visualization.nodes";
    String treeEdges="mice_visualization.edges";
    String linear="linear";
    String label="Status";


    public MiceVis(ObservableList<Mouse> miceArrayList) {
        super();
        this.miceArrayList=miceArrayList;
        setMinimumSize(new Dimension(700, 500));
        this.setPreferredSize(new Dimension(750, 500));
        runVis();
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
        Visualization m_vis=vis;

        dis = new Display(vis);


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
        color.add(edges);
        color.add(arrow);
        m_vis.putAction("color", color);
/**
 *
 */


        ItemAction nodeColor = new NodeColorAction(treeNodes);
        ItemAction textColor = new TextColorAction(treeNodes);
        m_vis.putAction("textColor", textColor);

        ItemAction edgeColor = new ColorAction(treeEdges,
                VisualItem.STROKECOLOR, ColorLib.rgb(200,200,200));

        FontAction fonts = new FontAction(treeNodes,
                FontLib.getFont("Tahoma", 10));
        fonts.add("ingroup('_focus_')", FontLib.getFont("Tahoma", 11));

        // recolor
        ActionList recolor = new ActionList();
        recolor.add(nodeColor);
        recolor.add(textColor);
        m_vis.putAction("recolor", recolor);

        // repaint
        ActionList repaint = new ActionList();
        repaint.add(recolor);
        repaint.add(new RepaintAction());
        m_vis.putAction("repaint", repaint);

        // animate paint change
        ActionList animatePaint = new ActionList(1200);
        animatePaint.add(new ColorAnimator(treeNodes));
        animatePaint.add(new RepaintAction());
        m_vis.putAction("animatePaint", animatePaint);

        // create the tree layout action
        RadialTreeLayout treeLayout = new RadialTreeLayout(tree);
        //treeLayout.setAngularBounds(-Math.PI/2, Math.PI);
        m_vis.putAction("treeLayout", treeLayout);

        // create the filtering and layout
        ActionList filter = new ActionList();
        filter.add(new TreeRootAction(tree));
        filter.add(fonts);
        filter.add(treeLayout);
        filter.add(textColor);
        filter.add(nodeColor);
        filter.add(edgeColor);
        m_vis.putAction("filter", filter);

        // animated transition
        ActionList animate = new ActionList(1250);
        animate.setPacingFunction(new SlowInSlowOutPacer());
        animate.add(new QualityControlAnimator());
        animate.add(new VisibilityAnimator(tree));
        animate.add(new PolarLocationAnimator(treeNodes, linear));
        animate.add(new ColorAnimator(treeNodes));
        animate.add(new RepaintAction());
        m_vis.putAction("animate", animate);
        m_vis.alwaysRunAfter("filter", "animate");

        // ------------------------------------------------

        // initialize the display
        dis.setItemSorter(new TreeDepthItemSorter());
        dis.addControlListener(new DragControl());
        dis.addControlListener(new ZoomToFitControl());
        dis.addControlListener(new ZoomControl());
        dis.addControlListener(new PanControl());
        dis.addControlListener(new FocusControl(1, "filter"));
//        dis.addControlListener(new HoverActionControl("repaint"));

        // ------------------------------------------------

        // filter graph and perform layout
        m_vis.run("filter");

        // maintain a set of items that should be interpolated linearly
        // this isn't absolutely necessary, but makes the animations nicer
        // the PolarLocationAnimator should read this set and act accordingly


        m_vis.addFocusGroup(linear, new DefaultTupleSet());
        m_vis.getGroup(Visualization.FOCUS_ITEMS).addTupleSetListener(
                new TupleSetListener() {
                    public void tupleSetChanged(TupleSet t, Tuple[] add, Tuple[] rem) {
                        TupleSet linearInterp = m_vis.getGroup(linear);
                        if ( add.length < 1 ) return; linearInterp.clear();
                        for ( Node n = (Node)add[0]; n!=null; n=n.getParent() )
                            linearInterp.addTuple(n);
                    }
                }
        );

        SearchTupleSet search = new PrefixSearchTupleSet();
        m_vis.addFocusGroup(Visualization.SEARCH_ITEMS, search);

        search.addTupleSetListener(new TupleSetListener() {
            public void tupleSetChanged(TupleSet t, Tuple[] add, Tuple[] rem) {
                m_vis.cancel("animatePaint");
                m_vis.run("recolor");
                m_vis.run("animatePaint");
            }
        });

        SearchQueryBinding sq = new SearchQueryBinding(
                (Table)vis.getGroup(treeNodes), label,
                (SearchTupleSet)vis.getGroup(Visualization.SEARCH_ITEMS));
        JSearchPanel searchPanel = sq.createSearchPanel();
        searchPanel.setShowResultCount(true);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5,5,4,0));
        searchPanel.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 11));

        final JFastLabel title = new JFastLabel("       Status          ");
        title.setPreferredSize(new Dimension(350, 20));
        title.setVerticalAlignment(SwingConstants.BOTTOM);
        title.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));
        title.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 16));

        dis.addControlListener(new ControlAdapter() {
            public void itemEntered(VisualItem item, MouseEvent e) {
                if ( item.canGetString(label) )
                    title.setText(item.getString(label));
            }
            public void itemExited(VisualItem item, MouseEvent e) {
                title.setText(null);
            }
        });

        Box box = new Box(BoxLayout.X_AXIS);
        box.add(Box.createHorizontalStrut(10));
        box.add(title);
        box.add(Box.createHorizontalGlue());
        box.add(searchPanel);
        box.add(Box.createHorizontalStrut(3));





/**
 *
  */

        ActionList layout=new ActionList(Activity.INFINITY);

        layout.add(new FinalDecoratorLayout("nodedec"));

        RadialTreeLayout radialTreeLayout =new RadialTreeLayout("mice_visualization");
        layout.add(radialTreeLayout);

        layout.add(new RepaintAction());

        vis.putAction("color", color);
        vis.putAction("layout", layout);

        containerComponent.setLayout(new BorderLayout());
        containerComponent.add(box, BorderLayout.NORTH);
        containerComponent.add(backColorButton, BorderLayout.SOUTH);
        backColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vis.run("color");
            }
        });







    }

    @Override
    public void setUpDisplay() {


        dis.setSize(1500, 900);

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

        containerComponent.add(dis, BorderLayout.CENTER);


        Color BACKGROUND = Color.WHITE;
        Color FOREGROUND = Color.DARK_GRAY;
        UILib.setColor(containerComponent, BACKGROUND, FOREGROUND);

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

 //       miceArrayList=cageZZ.getMiceList();

        int i=0;
        visualMiceModel.put(Integer.valueOf(i), anonymMouse);
        for (Mouse mouse : miceArrayList){
            visualMiceModel.put(Integer.valueOf(++i), mouse);
        }

    }



    public static class NodeColorAction extends ColorAction {
        public NodeColorAction(String group) {
            super(group, VisualItem.FILLCOLOR, ColorLib.rgba(255,255,255,0));
            add("_hover", ColorLib.gray(220,230));
            add("ingroup('_search_')", ColorLib.rgb(255,190,190));
            add("ingroup('_focus_')", ColorLib.rgb(198,229,229));
        }

    } // end of inner class NodeColorAction

    /**
     * Set node text colors
     */
    public static class TextColorAction extends ColorAction {
        public TextColorAction(String group) {
            super(group, VisualItem.TEXTCOLOR, ColorLib.rgb(255, 0, 0));
            add("_hover", ColorLib.rgb(255,0,0));
        }
    }


    public static class TreeRootAction extends GroupAction {
        public TreeRootAction(String graphGroup) {
            super(graphGroup);
        }
        public void run(double frac) {
            TupleSet focus = m_vis.getGroup(Visualization.FOCUS_ITEMS);
            if ( focus==null || focus.getTupleCount() == 0 ) return;

            Graph g = (Graph)m_vis.getGroup(m_group);
            Node f = null;
            Iterator tuples = focus.tuples();
            while (tuples.hasNext() && !g.containsTuple(f=(Node)tuples.next()))
            {
                f = null;
            }
            if ( f == null ) return;
            g.getSpanningTree(f);
        }
    }




}
