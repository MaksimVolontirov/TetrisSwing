package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;
class TetrisSwingTest {
    @Test
    @DisplayName("Restart game method test")
    void restartGameTest() {
        boolean expectedRestart = false;
        TetrisSwing.restartGame();
        boolean actualRestart = TetrisSwing.restart;
        Assertions.assertEquals(expectedRestart, actualRestart);
    }
}