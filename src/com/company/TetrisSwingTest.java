package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TetrisSwingTest {
    @Test
    @DisplayName("RestartGameTest")
    void restartGameTest() {
        boolean expectedRestart = false;
        TetrisSwing.currentScoreLabel.setText("");
        TetrisSwing.restartGame();
        boolean actualRestart = TetrisSwing.restart;
        Assertions.assertEquals(expectedRestart, actualRestart);
    }
}