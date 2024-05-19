package com.study.tdd.requirements;

public class TennisGame {

    private String player1Name;
    private String player2Name;
    private int player1Score;
    private int player2Score;
    private GameStatus status;

    private TennisGame() {
        this.status = GameStatus.NOT_READY;
        this.player1Score = 0;
        this.player2Score = 0;
    }

    private enum GameStatus {
        NOT_READY,
        PLAYING,
        END
    }

    private enum GameScores {
        LOVE,
        FIFTEEN,
        THIRTY,
        FORTY,
        DEUCE,
        ADVANTAGE
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    private GameStatus getStatus() {
        return status;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public String showStatus() {
        return getStatus().name();
    }

    public String showScores() {
        if ((GameStatus.NOT_READY).equals(getStatus())) {
            return "GAME_IS_" + GameStatus.NOT_READY.name();
        }
        return null;
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

        public Builder start() {
            game.status = GameStatus.PLAYING;
            return this;
        }

        public TennisGame build() {
            return game;
        }
    }

}
