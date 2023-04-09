package Listeners;

import Drawer.DrawingPanel;
import RedBlackTree.RedBlackTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class HeightAction implements ActionListener {
    private final RedBlackTree theTree;
    private final DrawingPanel drawingPanel;

    private void Height(String valueToBeInputed) {
        try {
            int height = theTree.getTreeHeight(theTree.Search(Integer.parseInt(valueToBeInputed)));
            JOptionPane.showMessageDialog(null,"The height of node: " + height);
            drawingPanel.repaint();
        } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, " The input value must be a positive natural number ");
            
        }
    }

    public HeightAction(RedBlackTree tree, DrawingPanel drawingPanel) {
        this.theTree = tree;
        this.drawingPanel = drawingPanel;
    }

    public void actionPerformed(ActionEvent e) {
        String valueToBeInputed = JOptionPane.showInputDialog(" Enter node :  ");
        if (valueToBeInputed == null) return;
        Height(valueToBeInputed);
    }
}

