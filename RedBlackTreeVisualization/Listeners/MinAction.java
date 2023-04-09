package Listeners;


import RedBlackTree.RedBlackTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinAction implements ActionListener {
    private final RedBlackTree theTree;
    


    public MinAction(RedBlackTree tree) {
        this.theTree = tree;
       
    }

    public void actionPerformed(ActionEvent e) {
        int minValue = theTree.minimum();
        JOptionPane.showMessageDialog(null, "Min value of the tree : " + minValue);
    }
}