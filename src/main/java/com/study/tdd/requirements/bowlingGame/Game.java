package com.study.tdd.requirements.bowlingGame;

/**
 * <pre>
 * The Bowling Game Kata
 * </pre>
 * @version 1.0
 * @author Lee, Ji Won
 * @since 2024.06.01
 * @link <a href="http://butunclebob.com/ArticleS.UncleBob.TheBowlingGameKata">TDD Kata | Bowling Game</a>
 */
public class Game {
    private int rolls[] = new int[21];
    private int currentRoll = 0;

    /**
     * each time the player rolls a ball
     * @param pins the number of pins knocked down
     */
    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    /**
     * returns the total score for the game
     * called only at the very end of the game
     * @return total scores
     */
    public int score() {
        int score = 0;
        int frameIndex = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(frameIndex)) {
                score += 10 + strikeBonus(frameIndex);
                frameIndex++;
            } else if (isSpare(frameIndex)) {
                score += 10 + spareBonus(frameIndex);
                frameIndex += 2;
            } else {
                score += sumOfBallsInFrame(frameIndex);
                frameIndex += 2;
            }
        }
        return score;
    }

    private boolean isStrike(int frameIndex) {
        return rolls[frameIndex] == 10;
    }

    private int sumOfBallsInFrame(int frameIndex) {
        return rolls[frameIndex] + rolls[frameIndex + 1];
    }

    private int spareBonus(int frameIndex) {
        return rolls[frameIndex + 2];
    }

    private int strikeBonus(int frameIndex) {
        return rolls[frameIndex + 1] + rolls[frameIndex + 2];
    }

    private boolean isSpare(int frameIndex) {
        return rolls[frameIndex] +
                rolls[frameIndex + 1] == 10;
    }

}
