package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class TetrisSwing extends JComponent implements KeyListener, ActionListener {
    static byte fallTimer;
    static double speed;
    static int rotation;
    static int amount;
    static int score;
    static int aX, aY, bX, bY, cX, cY, dX, dY, nextBX, nextBY, nextCX, nextCY, nextDX, nextDY;
    static final int nextAX = 450;
    static final int nextAY = 120;
    static byte str;
    static boolean cantFall;
    static boolean restart = true;
    static byte field[][] = new byte[25][12]; //Игровое поле
    static boolean gameOver = true;
    byte arrayOfFigures[][][][] = {
            {{{-1,0},{0,1},{1,0}}, {{0,-1},{-1,0},{0,1}}, {{1,0},{0,-1},{-1,0}}, {{0,1},{1,0},{0,-1}}},
            {{{0,-1},{0,1},{1,1}}, {{1,0},{-1,0},{-1,1}}, {{0,1},{0,-1},{-1,-1}}, {{-1,0},{1,0},{1,-1}}},
            {{{0,-1},{0,1},{-1,1}}, {{1,0},{-1,0},{-1,-1}}, {{0,1},{0,-1},{1,-1}}, {{-1,0},{1,0},{1,1}}},
            {{{1,0},{0,1},{1,1}}},
            {{{0,-1},{0,1},{0,2}}, {{1,0},{-1,0},{-2,0}}, {{0,1},{0,-1},{0,-2}}, {{-1,0},{1,0},{2,0}}},
            {{{-1,0},{0,-1},{1,-1}}, {{0,-1},{1,0},{1,1}}, {{1,0},{0,1},{-1,1}}, {{0,1},{-1,0},{-1,-1}}},
            {{{1,0},{0,-1},{-1,-1}}, {{0,1},{1,0},{1,-1}}, {{-1,0},{0,1},{1,1}}, {{0,-1},{-1,0},{-1,1}}}
    };
    static byte figure; //Значение, показывающее номер фигуры
    static byte nextFigure;
    static byte fillAmount;

    Timer timer = new Timer(5,this);

    static final int WINDOW_WIDTH = 570;
    static final int WINDOW_HEIGHT = 660;
    static JFrame frame;
    static JLabel currentScoreLabel;
    static JLabel restartLabel;
    static Font font;

    public static void main(String[] args) {
        TetrisSwing main = new TetrisSwing();
        frame = getFrame();
        frame.add(main);
        frame.addKeyListener(main);
    }

    static JFrame getFrame() {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setBounds((dimension.width- WINDOW_WIDTH) / 2,(dimension.height - WINDOW_HEIGHT) / 2,WINDOW_WIDTH+15,WINDOW_HEIGHT+38);
        font = new Font("Arial", Font.PLAIN, 20);
        JLabel scoreLabel = new JLabel("Score: ");
        scoreLabel.setForeground(Color.LIGHT_GRAY);
        scoreLabel.setBounds(390,330,70,30);
        scoreLabel.setFont(font);
        frame.add(scoreLabel);
        Font FONT = new Font("Arial", Font.PLAIN, 40);
        restartLabel = new JLabel("Press spacebar to start", SwingConstants.CENTER);
        restartLabel.setForeground(Color.WHITE);
        restartLabel.setBounds(70,260,420,60);
        restartLabel.setFont(FONT);
        frame.add(restartLabel);
        currentScoreLabel = new JLabel(String.valueOf(score));
        currentScoreLabel.setForeground(Color.LIGHT_GRAY);
        currentScoreLabel.setBounds(460,330,80,30);
        currentScoreLabel.setFont(font);
        frame.add(currentScoreLabel);
        JLabel nextLabel = new JLabel("Next figure:");
        nextLabel.setBounds(390,27,110,30);
        nextLabel.setForeground(Color.LIGHT_GRAY);
        nextLabel.setFont(font);
        frame.add(nextLabel);
        frame.setTitle("T E T R I S");
        return(frame);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WINDOW_WIDTH+1,WINDOW_HEIGHT+1);
        for (int i = 4; i < 24; i++) {
            for (int j = 1; j < 11; j++) {
                switch(field[i][j]) {
                    case 0:
                        g.setColor(Color.BLACK);
                        break;
                    case 1:
                        g.setColor(Color.RED);
                        break;
                    case 2:
                        g.setColor(Color.ORANGE);
                        break;
                    case 3:
                        g.setColor(Color.YELLOW);
                        break;
                    case 4:
                        g.setColor(Color.GREEN);
                        break;
                    case 5:
                        g.setColor(Color.CYAN);
                        break;
                    case 6:
                        g.setColor(Color.BLUE);
                        break;
                    case 7:
                        g.setColor(Color.MAGENTA);
                        break;
                }
                g.fillRect((j+1)*30,(i-3)*30,30,30);
                g.setColor(Color.GRAY);
                g.drawRect((j+1)*30,(i-3)*30,30,30);
            }
        }
        switch(figure) {
            case 1:
                g.setColor(Color.RED);
                break;
            case 2:
                g.setColor(Color.ORANGE);
                break;
            case 3:
                g.setColor(Color.YELLOW);
                break;
            case 4:
                g.setColor(Color.GREEN);
                break;
            case 5:
                g.setColor(Color.CYAN);
                break;
            case 6:
                g.setColor(Color.BLUE);
                break;
            case 7:
                g.setColor(Color.MAGENTA);
                break;
        }
        if (aY >= 5) {
            g.fillRect((aX + 1) * 30, (aY - 4) * 30, 30, 30);
        }
        if (bY >= 5) {
            g.fillRect((bX + 1) * 30, (bY - 4) * 30, 30, 30);
        }
        if (cY >= 5) {
            g.fillRect((cX + 1) * 30, (cY - 4) * 30, 30, 30);
        }
        if (dY >= 5) {
            g.fillRect((dX + 1) * 30, (dY - 4) * 30, 30, 30);
        }
        g.setColor(Color.GRAY);
        if (aY >= 5) {
            g.drawRect((aX + 1) * 30, (aY - 4) * 30, 30, 30);
        }
        if (bY >= 5) {
            g.drawRect((bX + 1) * 30, (bY - 4) * 30, 30, 30);
        }
        if (cY >= 5) {
            g.drawRect((cX + 1) * 30, (cY - 4) * 30, 30, 30);
        }
        if (dY >= 5) {
            g.drawRect((dX + 1) * 30, (dY - 4) * 30, 30, 30);
        }
        g.setColor(Color.DARK_GRAY);
        g.fillRect(390,60,150,180);
        switch(nextFigure) {
            case 1:
                g.setColor(Color.RED);
                break;
            case 2:
                g.setColor(Color.ORANGE);
                break;
            case 3:
                g.setColor(Color.YELLOW);
                break;
            case 4:
                g.setColor(Color.GREEN);
                break;
            case 5:
                g.setColor(Color.CYAN);
                break;
            case 6:
                g.setColor(Color.BLUE);
                break;
            case 7:
                g.setColor(Color.MAGENTA);
                break;
        }
        if (!restart) {
            g.fillRect(nextAX, nextAY, 30, 30);
            g.fillRect(nextBX, nextBY, 30, 30);
            g.fillRect(nextCX, nextCY, 30, 30);
            g.fillRect(nextDX, nextDY, 30, 30);
            g.setColor(Color.GRAY);
            g.drawRect(nextAX, nextAY, 30, 30);
            g.drawRect(nextBX, nextBY, 30, 30);
            g.drawRect(nextCX, nextCY, 30, 30);
            g.drawRect(nextDX, nextDY, 30, 30);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        if(gameOver) {
            Font FONT = new Font("Arial", Font.PLAIN, 36);
            restartLabel.setFont(FONT);
            restartLabel.setBounds(30,160,470,200);
            restartLabel.setText("<html><center>Game over!<center>" +
                                "Your score: " + score +
                                "<br>Press spacebar to start again<html>");
            restartLabel.setVisible(true);
        } else {
            if (restart) {
                restart = false;
                score = 0;
                currentScoreLabel.setText(String.valueOf(score));
                speed = 30;
                nextFigure = (byte) (1 + Math.random() * 7);
                aX = 4;
                aY = 2;
                cantFall = true;
                for (int i = 0; i < 25; i++) {
                    for (int j = 0; j < 12; j++) {
                        field[i][j] = 0;
                    }
                }
            } else {
                if (cantFall) {
                    cantFall = false;
                    rotation = 0;
                    figure = nextFigure;
                    nextFigure = (byte) (1 + Math.random() * 7);
                    nextBX = nextAX + 30 * arrayOfFigures[nextFigure - 1][0][0][0];
                    nextBY = nextAY + 30 * arrayOfFigures[nextFigure - 1][0][0][1];
                    nextCX = nextAX + 30 * arrayOfFigures[nextFigure - 1][0][1][0];
                    nextCY = nextAY + 30 * arrayOfFigures[nextFigure - 1][0][1][1];
                    nextDX = nextAX + 30 * arrayOfFigures[nextFigure - 1][0][2][0];
                    nextDY = nextAY + 30 * arrayOfFigures[nextFigure - 1][0][2][1];
                    if (figure == 4) {
                        amount = 1;
                    } else {
                        amount = 4;
                    }
                    aX = 5;
                    aY = 2;
                } else {
                    if (fallTimer == speed) {
                        if ((field[aY][aX] == 0) && (aY + 1 < 25) && (field[bY][bX] == 0) && (bY + 1 < 25) && (field[cY][cX] == 0) && (cY + 1 < 25) && (field[dY][dX] == 0) && (dY + 1 < 25)) {
                            aY++;
                        } else {
                            cantFall = true;
                            field[aY - 1][aX] = figure;
                            field[bY - 1][bX] = figure;
                            field[cY - 1][cX] = figure;
                            field[dY - 1][dX] = figure;
                            str = 23;
                            while (str > 3) {
                                fillAmount = 0;
                                for (int i = 1; i < 11; i++) {
                                    if (field[str][i] != 0) {
                                        fillAmount++;
                                    }
                                }
                                if (fillAmount == 10) {
                                    score += 10;
                                    currentScoreLabel.setText(String.valueOf(score));
                                    if ((speed - 5 > 0) && (score % 200 == 0)) {
                                        speed -= 5;
                                    }
                                    for (int j = str - 1; j >= 4; j--) {
                                        for (int i = 1; i < 11; i++) {
                                            field[j + 1][i] = field[j][i];
                                        }
                                    }
                                    str++;
                                }
                                str--;
                            }
                            for (int i = 1; i < 11; i++) {
                                if (field[3][i] != 0) {
                                    restart = true;
                                    gameOver = true;
                                }
                            }
                        }
                        fallTimer = 0;
                    }
                }
                fallTimer++;
                bX = aX + arrayOfFigures[figure - 1][rotation][0][0];
                bY = aY + arrayOfFigures[figure - 1][rotation][0][1];
                cX = aX + arrayOfFigures[figure - 1][rotation][1][0];
                cY = aY + arrayOfFigures[figure - 1][rotation][1][1];
                dX = aX + arrayOfFigures[figure - 1][rotation][2][0];
                dY = aY + arrayOfFigures[figure - 1][rotation][2][1];
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!restart) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if ((aX + arrayOfFigures[figure-1][(rotation + 1) % amount][0][0] < 11)&&(aX + arrayOfFigures[figure-1][(rotation + 1) % amount][1][0] < 11)&&(aX + arrayOfFigures[figure-1][(rotation + 1) % amount][2][0] < 11)) {
                    if ((aX + arrayOfFigures[figure-1][(rotation + 1) % amount][0][0] > 0)&&(aX + arrayOfFigures[figure-1][(rotation + 1) % amount][1][0] > 0)&&(aX + arrayOfFigures[figure-1][(rotation + 1) % amount][2][0] > 0)) {
                        if ((field[aY + arrayOfFigures[figure-1][(rotation + 1) % amount][0][1] - 1][aX + arrayOfFigures[figure-1][(rotation + 1) % amount][0][0]] == 0)&&(field[aY + arrayOfFigures[figure-1][(rotation + 1) % amount][1][1] - 1][aX + arrayOfFigures[figure-1][(rotation + 1) % amount][1][0]] == 0)&&(field[aY + arrayOfFigures[figure-1][(rotation + 1) % amount][2][1] - 1][aX + arrayOfFigures[figure-1][(rotation + 1) % amount][2][0]] == 0)) {
                            if (aY + arrayOfFigures[figure - 1][(rotation + 1) % amount][0][1] < 24 && aY + arrayOfFigures[figure - 1][(rotation + 1) % amount][1][1] < 24 && aY + arrayOfFigures[figure - 1][(rotation + 1) % amount][2][1] < 24) {
                                rotation = (rotation + 1) % amount;
                            }
                        }
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (!cantFall) {
                    if ((field[aY - 1][aX - 1] == 0) && (aX - 1 > 0) && (field[bY - 1][bX - 1] == 0) && (bX - 1 > 0) && (field[cY - 1][cX - 1] == 0) && (cX - 1 > 0) && (field[dY - 1][dX - 1] == 0) && (dX - 1 > 0)) {
                        aX--;
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (!cantFall) {
                    if ((field[aY - 1][aX + 1] == 0) && (aX + 1 < 11) && (field[bY - 1][bX + 1] == 0) && (bX + 1 < 11) && (field[cY - 1][cX + 1] == 0) && (cX + 1 < 11) && (field[dY - 1][dX + 1] == 0) && (dX + 1 < 11)) {
                        aX++;
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if ((field[aY][aX] == 0) && (aY + 1 < 25) && (field[bY][bX] == 0) && (bY + 1 < 25) && (field[cY][cX] == 0) && (cY + 1 < 25) && (field[dY][dX] == 0) && (dY + 1 < 25)) {
                    aY++;
                    bY = aY + arrayOfFigures[figure - 1][rotation][0][1];
                    cY = aY + arrayOfFigures[figure - 1][rotation][1][1];
                    dY = aY + arrayOfFigures[figure - 1][rotation][2][1];
                }
            }
        }
        if (e.getKeyCode()==KeyEvent.VK_SPACE) {
            timer.start();
            gameOver = false;
            restartLabel.setVisible(false);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
