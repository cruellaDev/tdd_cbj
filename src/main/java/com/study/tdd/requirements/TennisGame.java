package com.study.tdd.requirements;

public class TennisGame {

    private String player1Name;
    private String player2Name;
    private int player1Points;
    private int player2Points;

    private int playedSets;

    private TennisGame() {
        this.player1Points = 0;
        this.player2Points = 0;
        this.playedSets = 0;
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

    public int getPlayedSets() {
        return playedSets;
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
            if (Math.abs(player1Point) > 1 || Math.abs(player2Point) > 1) {
                System.err.println("정상적인 입력이 아닙니다. (" + player1Point + ", " + player2Point + ")");
                return this;
            }
            if (game.playedSets > 0) {
                System.err.println("이미 종료된 세트입니다.");
                return this;
            }
            game.player1Points += player1Point;;
            game.player2Points += player2Point;

            boolean isWinnerDecided = isWinnerDecided(game.player2Points, game.player1Points);
            if (isWinnerDecided) {
                game.playedSets++;
            }
            return this;
        }

        public TennisGame build() {
            if (game.player1Name == null || game.player1Name.isBlank()) {
                game.player1Name = "anonym1";
            }
            if (game.player2Name == null || game.player2Name.isBlank()) {
                game.player2Name = "anonym2";
            }
            return game;
        }
    }

    public static TennisGameScores compareTo(int sourcePoints, int targetPoints) {
        int pointsDiff = Math.abs(sourcePoints - targetPoints);
        if (sourcePoints >= 3 && pointsDiff == 0) {
            return TennisGameScores.DEUCE;
        } else if (sourcePoints > 3 && pointsDiff == 1) {
            return TennisGameScores.ADVANTAGE;
        } else if (sourcePoints > 3 && pointsDiff > 1) {
            return TennisGameScores.WINNER;
        } else if (targetPoints > 3 && pointsDiff == 1) {
            return TennisGameScores.BEHIND;
        } else if (targetPoints > 3 && pointsDiff > 1) {
            return TennisGameScores.LOSER;
        } else {
            return switchPointsToGameScores(sourcePoints);
        }
    }

    public static TennisGameScores switchPointsToGameScores(int gamePoints) {
        return switch (gamePoints) {
            case 0 -> TennisGameScores.LOVE;
            case 1 -> TennisGameScores.FIFTEEN;
            case 2 -> TennisGameScores.THIRTY;
            case 3 -> TennisGameScores.FORTY;
            default -> null;
        };
    }

    public static boolean isWinnerDecided(int sumPoints1, int sumPoints2) {
        return compareTo(sumPoints1, sumPoints2).equals(TennisGameScores.WINNER)
                || compareTo(sumPoints2, sumPoints1).equals(TennisGameScores.WINNER);
    }
}
