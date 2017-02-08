package com.estella;

import java.awt.BorderLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CanvasView implements CanvasObserver {
  private final CanvasModel model;
  private JFrame frame;
  private PaintingBoard board;
  private JButton clearButton;
  
  /**
   * Build a canvas view as an observer, and add this view to the observer list 
   * in the singleton canvas model.
   * @param md - the singleton canvas model.
   */
  public CanvasView(CanvasModel md) {
    model = md;
    model.addObserver(this);
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        buildGUI();
      }
    });
  }
  
  private void buildGUI() {
    frame = new JFrame();
    frame.setTitle("Canvas");
    frame.setSize(400, 400);
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    addCustomExit();
    board = new PaintingBoard(model);
    JPanel buttonPanel = new JPanel();
    clearButton = createClearButton("Clear Canvas");
    buttonPanel.add(clearButton);
    frame.add(board, BorderLayout.CENTER);
    frame.add(buttonPanel, BorderLayout.SOUTH);
    frame.setVisible(true);
  }
  
  private void addCustomExit() {
    CanvasView thisView = this;
    WindowListener exitListener = new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
          int confirm = JOptionPane.showOptionDialog(
               null, "Are You Sure to Close Canvas?", 
               "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
               JOptionPane.QUESTION_MESSAGE, null, null, null);
          if (confirm == 0) {
             model.removeObserver(thisView);
             System.exit(0);
          }
      }
    };
    frame.addWindowListener(exitListener);
  }

  private JButton createClearButton(String name){
    JButton button = new JButton(name);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        model.fireClearCanvasEvent();
      }
    });
    return button;  
  }
  
  /**
   * after a mouse-pressed movement, make a corresponding stroke on canvas.
   * @param stroke - the stroke to be made.
   */
  @Override
  public void makeStroke(Stroke stroke) {
    float x1 = (float)stroke.getStartPoint().getX();
    float y1 = (float)stroke.getStartPoint().getY();
    float x2 = (float)stroke.getEndPoint().getX();
    float y2 = (float)stroke.getEndPoint().getY();
    Shape shape = board.drawLine(x1, y1, x2, y2);
    stroke.setShape(shape);
    board.addStroke(stroke);
    board.repaint();
  }
  
  /**
   * When we start or reset the canvas, clear all previous strokes on the canvas.
   */
  @Override
  public void clearCanvas() {
    board.resetBoard();
  }
}