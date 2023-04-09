package Listeners;

import Drawer.DrawingPanel;
import RedBlackTree.RedBlackTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteNodeAction implements ActionListener {
    private final RedBlackTree theTree;
    private final DrawingPanel drawingPanel;

    private void delete(String valueToBeDeleted) {
        try {
            theTree.remove(theTree.Search(Integer.parseInt(valueToBeDeleted)));
            drawingPanel.repaint();
        } catch (Exception ex) {
            
            // if(theTree.isContain(Integer.parseInt(valueToBeDeleted)) == false ){
            //     JOptionPane.showMessageDialog(null, " Node doesn't exists in the tree ");
            // }else{
                JOptionPane.showMessageDialog(null, " The input value must be a positive natural number ");
            
        }
    }

    public DeleteNodeAction(RedBlackTree tree, DrawingPanel drawingPanel) {
        this.theTree = tree;
        this.drawingPanel = drawingPanel;
    }

    public void actionPerformed(ActionEvent e) {
        String valueToBeDeleted = JOptionPane.showInputDialog(" Enter node to delete ");
        if (valueToBeDeleted == null) return;
        delete(valueToBeDeleted);
    }
}
