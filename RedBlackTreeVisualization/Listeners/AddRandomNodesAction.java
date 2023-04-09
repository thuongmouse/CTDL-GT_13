package Listeners;

import Drawer.DrawingPanel;
import RedBlackTree.RedBlackTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AddRandomNodesAction implements ActionListener {
    private final RedBlackTree theTree;
    private final DrawingPanel drawingPanel;
    private final JScrollPane scrollPane;

    private void addNodesToTree(String numberOfNodes) {
        try {
            generateRandomNodes(Integer.parseInt(numberOfNodes));
            JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
            horizontalScrollBar.setValue(1850);
            drawingPanel.repaint();
        } catch (NumberFormatException exp) {
            JOptionPane.showMessageDialog(null, "Enter digits only");
        }
    }

    private void generateRandomNodes(int numberOfNodes) {
        Random r = new Random();
        for (int i = 0; i < numberOfNodes; i++) {
            theTree.insert(r.nextInt(1000));
        }
    }

    public AddRandomNodesAction(RedBlackTree theTree, DrawingPanel drawingPanel, JScrollPane scrollPane) {
        this.theTree = theTree;
        this.drawingPanel = drawingPanel;
        this.scrollPane = scrollPane;
    }

    public void actionPerformed(ActionEvent e) {
        String numberOfNodes = JOptionPane.showInputDialog("Enter the number of nodes to add", "10");
        if (numberOfNodes == null) return;
        addNodesToTree(numberOfNodes);
    }
}