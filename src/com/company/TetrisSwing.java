package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class TetrisSwing extends JComponent implements KeyListener, ActionListener {
    static int x,y = 0;
    Rectangle rectangle = new Rectangle(x+1,y+1,28,28);
    Timer timer = new Timer(5,this);
    static final int WINDOW_WIDTH = 570;
    static final int WINDOW_HEIGHT = 660;
        public static void main(String[] args) {
            TetrisSwing main = new TetrisSwing();
            JFrame frame = getFrame();
            frame.add(main);
            frame.addKeyListener(main);

    }
    static JFrame getFrame(){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setBounds((dimension.width- WINDOW_WIDTH) / 2,(dimension.height - WINDOW_HEIGHT) / 2,WINDOW_WIDTH+14,WINDOW_HEIGHT+37);
        frame.setTitle("T E T R I S");
        return(frame);
    }
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(30,30,330,600);
        g.setColor(Color.GRAY);
        g.drawRect(30,30,330,600);
        g2d.setColor(Color.WHITE);
        g2d.fill(rectangle);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                x += 30;
                rectangle.setLocation(x+1,y+1);
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
                y += 30;
                rectangle.setLocation(x+1,y+1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}