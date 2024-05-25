package com.study.tdd.requirements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Tennis
 * This kata is about implementing a simple tennis game. I came up with it while thinking about Wii tennis, where they have simplified tennis, so each set is one game. The scoring system is rather simple:
 *
 * Each player can have either of these points in one game 0 15 30 40
 * If you have 40 and you win the ball you win the game, however, there are special rules.
 * If both have 40 the players are deuce.
 * After deuce, the winner of a ball will have advantage and game ball.
 * If the player with advantage wins the ball he wins the game
 * If the player without advantage wins they are back at deuce.
 * A game is won by the first player to have won at least four points in total and at least two points more than the opponent.
 * The running score of each game is described in a manner peculiar to tennis: scores from zero to three points are described as “love”, “fifteen”, “thirty”, and “forty” respectively.
 * If at least three points have been scored by each player, and the scores are equal, the score is “deuce”.
 * If at least three points have been scored by each side and a player has one more point than his opponent, the score of the game is “advantage” for the player in the lead.
 * Rating: Medium
 * ... etc up to 100
 * </pre>
 * @version 1.0
 * @author Lee, Ji Won
 * @since 2024.05.25
 * @link    <a href="https://programmingwithwolfgang.com/tdd-kata/#tennis">TDD Kata | Tennis</a>
 */
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
