package Drawer;

import RedBlackTree.RedBlackTree;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;

public class DrawingPanel extends JPanel {
    private final RedBlackTree theTree;
    static private DrawingPanel drawingPanel;
    private int nodeDiameter;

    private DrawingPanel(RedBlackTree theTree) {
        nodeDiameter = 50;
        this.theTree = theTree;
    }

    static public DrawingPanel getNewDrawingPanel(RedBlackTree theTree) {
        if (drawingPanel == null) {
            drawingPanel = new DrawingPanel(theTree);
        }
        return drawingPanel;
    }

    public void setNodeDiameter(int nodeDiameter) {
        this.nodeDiameter = nodeDiameter;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new ColorUIResource(220,220,220));
        g.fillRect(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight());
        g.setColor(Color.red);
        if (theTree == null) return;
        setPreferredSize(new Dimension(5000, 5000));
        draw(g);
        revalidate();
    }

    private void draw(Graphics g) {
        g.setFont(new Font("Courier", Font.BOLD, nodeDiameter / 4));
        theTree.drawTree(g,nodeDiameter);
    }
}