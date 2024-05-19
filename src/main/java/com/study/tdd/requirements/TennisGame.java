package com.study.tdd.requirements;

public class TennisGame {

    private String player1Name;
    private String player2Name;
    private int player1Points;
    private int player2Points;

    private TennisGame() {
        this.player1Points = 0;
        this.player2Points = 0;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public int getPlayer1Points() {
        return player1Points;
    }

    public int getPlayer2Points() {
        return player2Points;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final TennisGame game = new TennisGame();

        public Builder player1Name(String player1Name) {
            game.player1Name = player1Name;
            return this;
        }

        public Builder player2Name(String player2Name) {
            game.player2Name = player2Name;
            return this;
        }

        public Builder addPoints(int player1Point, int player2Point) {
            game.player1Points += player1Point;
            game.player2Points += player2Point;
            return this;
        }

        public TennisGame build() {
            return game;
        }
    }
}
