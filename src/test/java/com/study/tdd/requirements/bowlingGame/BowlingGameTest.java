package com.study.tdd.requirements.bowlingGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BowlingGameTest {

    private Game g;

    @BeforeEach
    protected void setUp() throws Exception {
        g = new Game();
    }

    private void rollMany(int n, int pins) {
        for (int i = 0; i < n; i++) {
            g.roll(pins);
        }
    }

    private void rollSpare() {
        g.roll(5);
        g.roll(5);
    }

    private void rollStrikes() {
        g.roll(10);
    }

    @Test
    void testGutterGame() throws Exception {
        rollMany(20, 0);
        assertEquals(0, g.score());
    }

    @Test
    void testAllOnes() throws Exception {
        rollMany(20, 1);
        assertEquals(20, g.score());
    }

    @Test
    void testOneSpare() throws Exception {
        rollSpare();
        g.roll(3);
        rollMany(17,0);
        assertEquals(16, g.score());
    }

    @Test
    void testOneStrike() throws Exception {
        rollStrikes();
        g.roll(3);
        g.roll(4);
        rollMany(16, 0);
        assertEquals(24, g.score());
    }

    @Test
    void testPerfectGame() throws Exception {
        rollMany(12, 10);
        assertEquals(300, g.score());
    }
}
