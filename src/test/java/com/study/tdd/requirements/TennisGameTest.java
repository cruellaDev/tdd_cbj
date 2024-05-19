package com.study.tdd.requirements;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TennisGameTest {

    private final TennisScoreTranslator translator = new TennisScoreTranslator();

    @Test
    void input_players_name() {
//        TennisGame game =  new TennisGame("조코비치", "나달");
//        assertEquals("조코비치", game.getPlayer1Name());
//        assertEquals("나달", game.getPlayer2Name());

        // Builder 패턴으로 진행
        TennisGame game2 =  TennisGame.builder()
                .player1Name("조코비치")
                .player2Name("나달").build();
        assertEquals("조코비치", game2.getPlayer1Name());
        assertEquals("나달", game2.getPlayer2Name());
    }

    @Test
    void show_players_scores_after_set_games() {
        TennisGame game =  TennisGame.builder()
                .player1Name("조코비치")
                .player2Name("나달")
                .build();
        assertEquals("조코비치 : LOVE, 나달 : LOVE", translator.showScores(game));
    }

    @Test
    void show_players_scores_after_player1_add_points() {
        TennisGame game =  TennisGame.builder()
                .player1Name("조코비치")
                .player2Name("나달")
                .addPoints(1, 0)
                .build();
        assertEquals("조코비치 : FIFTEEN, 나달 : LOVE", translator.showScores(game));

        TennisGame game2 =  TennisGame.builder()
                .player1Name("조코비치")
                .player2Name("나달")
                .addPoints(1, 0)
                .addPoints(1, 0)
                .build();
        assertEquals("조코비치 : THIRTY, 나달 : LOVE", translator.showScores(game2));

        TennisGame game3 =  TennisGame.builder()
                .player1Name("조코비치")
                .player2Name("나달")
                .addPoints(1, 0)
                .addPoints(1, 0)
                .addPoints(1, 0)
                .build();
        assertEquals("조코비치 : FORTY, 나달 : LOVE", translator.showScores(game3));
    }

    @Test
    void deuce_when_both_players_have_three_points() {
        TennisGame game3 =  TennisGame.builder()
                .player1Name("조코비치")
                .player2Name("나달")
                .addPoints(1, 0)
                .addPoints(1, 0)
                .addPoints(1, 0)
                .addPoints(0, 1)
                .addPoints(0, 1)
                .addPoints(0, 1)
                .build();
        assertEquals("DEUCE", translator.showScores(game3));
    }

    @Test
    void player1_have_advantage_when_both_players_have_more_than_three_points_and_player1_add_points_one_more() {
        TennisGame game3 =  TennisGame.builder()
                .player1Name("조코비치")
                .player2Name("나달")
                .addPoints(1, 0)
                .addPoints(1, 0)
                .addPoints(1, 0)
                .addPoints(0, 1)
                .addPoints(0, 1)
                .addPoints(0, 1)
                .addPoints(1, 0)
                .build();
        assertEquals("조코비치 : ADVANTAGE", translator.showScores(game3));
    }

    @Test
    void deuce_when_both_players_have_more_than_three_points() {
        TennisGame game3 =  TennisGame.builder()
                .player1Name("조코비치")
                .player2Name("나달")
                .addPoints(1, 0)
                .addPoints(1, 0)
                .addPoints(1, 0)
                .addPoints(0, 1)
                .addPoints(0, 1)
                .addPoints(0, 1)
                .addPoints(1, 0)
                .addPoints(0, 1)
                .build();
        assertEquals("DEUCE", translator.showScores(game3));
    }

    @Test
    void show_players_scores_after_player1_add_points_four_times_in_a_row() {
        TennisGame game3 =  TennisGame.builder()
                .player1Name("조코비치")
                .player2Name("나달")
                .addPoints(1, 0)
                .addPoints(1, 0)
                .addPoints(1, 0)
                .addPoints(1, 0)
                .build();
        assertEquals("조코비치 : WINNER", translator.showScores(game3));
    }

}
