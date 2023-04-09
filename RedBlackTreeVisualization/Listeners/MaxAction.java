package Listeners;
import RedBlackTree.RedBlackTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaxAction implements ActionListener {
    private final RedBlackTree theTree;
 

    public MaxAction(RedBlackTree tree) {
        this.theTree = tree;
      
    }

    public void actionPerformed(ActionEvent e) {
        int maxvalue = theTree.maximum();
        JOptionPane.showMessageDialog(null, "Max value of the tree : " + maxvalue);
    }
}