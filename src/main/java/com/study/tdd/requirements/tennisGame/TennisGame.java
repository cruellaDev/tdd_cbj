package com.study.tdd.requirements.tennisGame;

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
 * </pre>
 * @version 1.0
 * @author Lee, Ji Won
 * @since 2024.05.25
 * @link    <a href="https://programmingwithwolfgang.com/tdd-kata/#tennis">TDD Kata | Tennis</a>
 */
public class TennisGame {

    private String player1Name;
    private String player2Name;
    private int player1Points;
    private int player2Points;

    private int playedSets;

    private TennisGame() {
        this.player1Points = 0;
        this.player2Points = 0;
        this.playedSets    = 0;
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
            if (game.playedSets > 0) {
                System.err.println("이미 종료된 세트입니다.");
                return this;
            }
            if (player1Point < 0 || player2Point < 0
                    || Math.abs(player1Point) > 1 || Math.abs(player2Point) > 1) {
                System.err.println("정상적인 입력이 아닙니다. (" + player1Point + ", " + player2Point + ")");
                return this;
            }
            game.player1Points += player1Point;
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
