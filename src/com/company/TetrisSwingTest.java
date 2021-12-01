package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TetrisSwingTest {
    @Test
    @DisplayName("RestartGameTest")
    void restartGameTest() {
        boolean expectedRestart = false;
        TetrisSwing.restartGame();
        boolean actualRestart = TetrisSwing.restart;
        Assertions.assertEquals(expectedRestart, actualRestart);
    }

    @Test
    @DisplayName("RestartFieldTest")
    void RestartFieldTest() {
        byte[][] expected = new byte[25][12];
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 12; j++) {
                expected[i][j] = 0;
            }
        }
        TetrisSwing.restartGame();
        byte[][] actual = TetrisSwing.field;
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 12; j++) {
                Assertions.assertEquals(expected[i][j], actual[i][j]);
            }
        }

    }

    @Test
    @DisplayName("AmountTest")
    void AmountTest() {
        TetrisSwing.nextFigure = 4;
        int expected = 1;
        TetrisSwing.makeNextFigure();
        int actual = TetrisSwing.amount;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("AmountTest2")
    void AmountTest2() {
        byte[] nextFigure = {1, 2, 3, 5, 6, 7};
        for (int i = 0; i < 6; i++) {
            TetrisSwing.nextFigure = nextFigure[i];
        }
        int expected = 4;
        TetrisSwing.makeNextFigure();
        int actual = TetrisSwing.amount;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("SpeedTest")
    void SpeedTest() {
        TetrisSwing.speed = 25;
        TetrisSwing.score = 190;
        double expected = 20;
        TetrisSwing.removeString();
        double actual = TetrisSwing.speed;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("SpeedTest2")
    void SpeedTest2() {
        TetrisSwing.speed = 10;
        TetrisSwing.score = 120;
        double expected = 10;
        TetrisSwing.removeString();
        double actual = TetrisSwing.speed;
        Assertions.assertEquals(expected, actual);
    }
}
