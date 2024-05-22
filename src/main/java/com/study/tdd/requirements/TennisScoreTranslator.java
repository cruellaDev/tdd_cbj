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
        TennisGameScores player1Scores = TennisGame.compareTo(player1Points, player2Points);
        TennisGameScores player2Scores = TennisGame.compareTo(player2Points, player1Points);
        Map<String, TennisGameScores> matchMap = new HashMap<>();
        if (player1Scores.equals(TennisGameScores.DEUCE) || player2Scores.equals(TennisGameScores.DEUCE)) {
            return matchMap;
        }
        if (player1Scores.equals(TennisGameScores.WINNER) || player1Scores.equals(TennisGameScores.ADVANTAGE)) {
            matchMap.put(player1Name, player1Scores);
        }
        else if (player2Scores.equals(TennisGameScores.WINNER) || player2Scores.equals(TennisGameScores.ADVANTAGE)) {
            matchMap.put(player2Name, player2Scores);
        } else {
            matchMap.put(player1Name, player1Scores);
            matchMap.put(player2Name, player2Scores);
        }
        return matchMap;
    }


}
