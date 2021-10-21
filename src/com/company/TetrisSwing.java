package com.company;
import javax.swing.*;
import java.awt.*;
//import java.awt.geom.Rectangle2D;

final public class TetrisSwing {
    static final int WINDOW_WIDTH = 380;
    static final int WINDOW_HEIGHT = 440;
    public static void main(String[] args) {
        TetrisSwing.getFrame();
    }
    private static void getFrame(){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setBounds((dimension.width- WINDOW_WIDTH) / 2,(dimension.height - WINDOW_HEIGHT) / 2,WINDOW_WIDTH,WINDOW_HEIGHT);
        frame.setTitle("T E T R I S");
        DrawPanel drawPanel = new DrawPanel();

    }
    static class DrawPanel extends JPanel{
        protected void paintComponent(Graphics g){
            g.setColor(Color.BLACK);
            g.fillRect(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
            g.setColor(Color.WHITE);
            g.fillRect(0,0,20,20);
        }
    }
/*class RunApplication implements Runnable{
    Graphics g;
    void setGraphics(Graphics g){
        this.g = g;
    }
    @Override
    public void run() {
        final int SIZE = 20;
        for (int i = 0; i < 10; i++){
            g.drawRect(i*20,i*20,SIZE,SIZE);
            try{
                Thread.sleep(1000);
            }catch(Exception exc){}
        }
    }*/

}