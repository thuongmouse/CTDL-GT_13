package Listeners;

import Drawer.DrawingPanel;
import RedBlackTree.RedBlackTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNodeAction implements ActionListener {
    private final RedBlackTree theTree;
    private final DrawingPanel drawingPanel;
    private final JScrollPane scrollPane;

    public AddNodeAction(RedBlackTree tree, DrawingPanel drawingPanel, JScrollPane scrollPane) {
        this.theTree = tree;
        this.drawingPanel = drawingPanel;
        this.scrollPane = scrollPane;
    }

    private void addToTree(String newNodeValue) {
        try {
            theTree.insert(Integer.parseInt(newNodeValue));
            JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
            horizontalScrollBar.setValue(1850);
            drawingPanel.repaint();
        } catch (NumberFormatException exp) {
            JOptionPane.showMessageDialog(null, " The input value must be a positive natural number ");
        }
    }

    public void actionPerformed(ActionEvent e) {
        String newNodeValue = JOptionPane.showInputDialog(" Enter value for new node ");
        if (newNodeValue == null) return;
        addToTree(newNodeValue);
    }
}
