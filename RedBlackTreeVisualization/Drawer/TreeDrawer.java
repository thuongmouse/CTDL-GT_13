package Drawer;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import Listeners.*;
import RedBlackTree.RedBlackTree;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class TreeDrawer {

    private JFrame theFrame;
    private DrawingPanel drawingPanel;
    private JScrollPane scrollPane;
    private final RedBlackTree theTree;

    public TreeDrawer() {
        theTree = new RedBlackTree();
        initialize();
    }

    private void initialize() {
        ArrayList<Integer> screenDimensions = getScreenDimensions();
        int screenWidth = screenDimensions.get(0);
        int screenHeight = screenDimensions.get(1);

        drawingPanel = DrawingPanel.getNewDrawingPanel(theTree);
        scrollPane = constructScrollPane(screenWidth, screenHeight);
        theFrame = constructFrame(screenWidth, screenHeight);

        JPanel buttonPanel = constructButtonsPanel();
        setContentContainer(buttonPanel, screenWidth);

        theFrame.setVisible(true);
    }

    private ArrayList<Integer> getScreenDimensions() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        ArrayList<Integer> dimensions = new ArrayList<>();
        dimensions.add((int) (toolkit.getScreenSize().getWidth()));
        dimensions.add((int) (toolkit.getScreenSize().getHeight()));
        return dimensions;
    }

    private JFrame constructFrame(int screenWidth, int screenHeight) {
        JFrame frame = new JFrame("Red Black Tree Visualization");
        frame.setSize(screenWidth, screenHeight);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

   
    private ArrayList<Button> constructButtons() {
        ArrayList<Button> buttons = new ArrayList<>();
        Button addNodesButton = new Button("Add the tree");
        addNodesButton.addActionListener(new AddRandomNodesAction(theTree, drawingPanel, scrollPane));
        buttons.add(addNodesButton);
       
        Button searchButton = new Button("Search");
        searchButton.addActionListener(new SearchNodeAction(theTree, drawingPanel));
        buttons.add(searchButton);

        Button addButton = new Button("Insert ");
        addButton.addActionListener(new AddNodeAction(theTree, drawingPanel, scrollPane));
        buttons.add(addButton);

        Button deleteButton = new Button("Delete");
        deleteButton.setSize(200, 150);
        deleteButton.addActionListener(new DeleteNodeAction(theTree, drawingPanel));
        buttons.add(deleteButton);

        Button maxButton = new Button("Max");
        maxButton.addActionListener(new MaxAction(theTree));
        buttons.add(maxButton);

        Button minButton = new Button("Min");
        minButton.addActionListener(new MinAction(theTree));
        buttons.add(minButton);

        Button blackHeight = new Button("Height");
        blackHeight.addActionListener(new HeightAction(theTree,drawingPanel));
        buttons.add(blackHeight);

        Button clearButton = new Button("Clear the tree");
        clearButton.addActionListener(new ClearPanelAction(theTree, drawingPanel));
        buttons.add(clearButton);

        return buttons;
    }
   
    private JPanel constructButtonsPanel() {
        ArrayList<Button> buttons = constructButtons();
        JPanel buttonPanel = new JPanel();
        
        for (var button : buttons) {
            buttonPanel.add(button);
        }
        CheckboxGroup cbg = new CheckboxGroup();
        Checkbox checkbox1 = new Checkbox("preOrder", cbg, false);
        checkbox1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JOptionPane.showMessageDialog(null, theTree.preOrder());
            }
        });
        Checkbox checkbox2 = new Checkbox("inOrder", cbg, false);
        checkbox2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JOptionPane.showMessageDialog(null, theTree.inOrder());
            }
        });
        Checkbox checkbox3 = new Checkbox("postOrder", cbg, false);
        checkbox3.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JOptionPane.showMessageDialog(null, theTree.postOrder());
            }
        });

        buttonPanel.add(checkbox1);
        buttonPanel.add(checkbox2);
        buttonPanel.add(checkbox3);
        return buttonPanel;
    }

    private JScrollPane constructScrollPane(int screenWidth, int screenHeight) {
        JScrollPane scroll = new JScrollPane(drawingPanel);
        scroll.setPreferredSize(new Dimension(screenWidth - 100 , screenHeight - 150  ));
        return scroll;
    }

    private void setContentContainer(JPanel buttonPanel, int screenWidth) {
        Container contentPane = theFrame.getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, screenWidth, 20));
        contentPane.add(new JScrollPane(buttonPanel));
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }
}