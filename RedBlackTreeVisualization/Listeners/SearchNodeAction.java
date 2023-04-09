package Listeners;

import Drawer.DrawingPanel;
import RedBlackTree.RedBlackTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import RedBlackTree.Node;
public class SearchNodeAction implements ActionListener {
    private final RedBlackTree theTree;
    private final DrawingPanel drawingPanel;

    private void search(String valueToBeSearched) {
        try {
            Node search = theTree.Search(Integer.parseInt(valueToBeSearched));
            if(search.value == Integer.parseInt(valueToBeSearched)){
                JOptionPane.showMessageDialog(null, "YES");
            }else{
                JOptionPane.showMessageDialog(null, "NO");
            }
            drawingPanel.repaint();
        } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, " The input value must be a positive natural number ");
            
        }
    }

    public SearchNodeAction(RedBlackTree tree, DrawingPanel drawingPanel) {
        this.theTree = tree;
        this.drawingPanel = drawingPanel;
    }

    public void actionPerformed(ActionEvent e) {
        String valueToBeSearched = JOptionPane.showInputDialog(" Enter node to search ");
        if (valueToBeSearched == null) return;
        search(valueToBeSearched);
    }
}

