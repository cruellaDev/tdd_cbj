package com.study.tdd.requirements;

public class TennisScoreTranslator {
    public String showScores(TennisGame tennisGame) {
        String player1Name = tennisGame.getPlayer1Name();
        String player2Name = tennisGame.getPlayer2Name();
        int player1Points = tennisGame.getPlayer1Points();
        int player2Points = tennisGame.getPlayer2Points();
        int maxPoints = Math.max(player1Points, player2Points);
        int pointsDiff = Math.abs(player1Points - player2Points);
        if (maxPoints >= 3 && pointsDiff == 0) {
            return TennisGameScores.DEUCE.name();
        } else if (maxPoints > 3 && pointsDiff == 1) {
            return (player1Points > player2Points ? player1Name : player2Name) + " : " + TennisGameScores.ADVANTAGE;
        } else if (maxPoints > 3 && pointsDiff > 1) {
            return (player1Points > player2Points ? player1Name : player2Name) + " : " + TennisGameScores.WINNER;
        }
        return player1Name + " : " + switchPointsToGameScores(player1Points)
                + ", " + player2Name + " : " + switchPointsToGameScores(player2Points);
    }

    private TennisGameScores switchPointsToGameScores(int gamePoints) {
        return switch (gamePoints) {
            case 0 -> TennisGameScores.LOVE;
            case 1 -> TennisGameScores.FIFTEEN;
            case 2 -> TennisGameScores.THIRTY;
            case 3 -> TennisGameScores.FORTY;
            default -> null;
        };
    }
}
