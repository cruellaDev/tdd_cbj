package com.study.tdd.requirements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TennisScoreTranslator {
    public String showScores(TennisGame tennisGame) {
        Map<String, TennisGameScores> matchMap = analyzeMatch(
                tennisGame.getPlayer1Name(), tennisGame.getPlayer1Points(),
                tennisGame.getPlayer2Name(), tennisGame.getPlayer2Points());
        List<String> scoresScript = new ArrayList<>();
        if (matchMap.isEmpty()) {
            scoresScript.add(TennisGameScores.DEUCE.name());
        }
        if (matchMap.containsKey(tennisGame.getPlayer1Name())) {
            scoresScript.add(tennisGame.getPlayer1Name() + " : " + matchMap.get(tennisGame.getPlayer1Name()));
        }
        if (matchMap.containsKey(tennisGame.getPlayer2Name())) {
            scoresScript.add(tennisGame.getPlayer2Name() + " : " + matchMap.get(tennisGame.getPlayer2Name()));
        }
        return String.join(", ", scoresScript);
    }

    private Map<String, TennisGameScores> analyzeMatch(String player1Name, int player1Points, String player2Name, int player2Points) {
        String defender = player1Points > player2Points ? player1Name : player2Name;
        String chaser = player1Points > player2Points ? player2Name : player1Name;
        int maxPoints = Math.max(player1Points, player2Points);
        int minPoints = Math.min(player1Points, player2Points);
        int pointsDiff = Math.abs(player1Points - player2Points);
        Map<String, TennisGameScores> matchMap = new HashMap<>();
        if (maxPoints >= 3 && pointsDiff == 0) {
            // matchMap is empty when Deuce
        } else if (maxPoints > 3 && pointsDiff == 1) {
            matchMap.put(defender, TennisGameScores.ADVANTAGE);
        } else if (maxPoints > 3 && pointsDiff > 1) {
            matchMap.put(defender, TennisGameScores.WINNER);
        } else {
            matchMap.put(defender, switchPointsToGameScores(maxPoints));
            matchMap.put(chaser, switchPointsToGameScores(minPoints));
        }

        return matchMap;
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
