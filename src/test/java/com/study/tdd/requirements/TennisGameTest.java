package com.study.tdd.requirements;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TennisGameTest {

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
    void test_if_init_game_status_is_not_ready() {
        TennisGame game =  TennisGame.builder()
                .build();
        assertEquals("NOT_READY", game.showStatus());
    }

    @Test
    void set_game_play() {
        TennisGame game =  TennisGame.builder()
                .player1Name("조코비치")
                .player2Name("나달")
                .start()
                .build();
        assertEquals("PLAYING", game.showStatus());
    }

    @Test
    void show_players_scores_before_play_games() {
        TennisGame game =  TennisGame.builder()
                .player1Name("조코비치")
                .player2Name("나달")
                .build();
        assertEquals("GAME_IS_NOT_READY", game.showScores());
    }

    @Test
    void show_players_score_before_play_games() {
        TennisGame game =  TennisGame.builder()
                .player1Name("조코비치")
                .player2Name("나달")
                .build();
        assertEquals("GAME_IS_NOT_READY", game.showScores());
    }

    // TODO
    @Test
    void cannot_play_game_if_players_less_then_one() {
        TennisGame game =  TennisGame.builder()
                .start()
                .build();
        assertEquals("PLAYING", game.showStatus());
    }

    // TODO
    @Test
    void stop_game_if_players_less_then_one() {
        TennisGame game =  TennisGame.builder()
                .start()
                .build();
        assertEquals("PLAYING", game.showStatus());
    }

    // 게임 계속 연출 Builder로 해보기

    // TODO - -1점 WITHDRAW || PENALTY
    // TODO - 0점 Love
    // TODO - +1점 A FIFTEEN
    // TODO - +1점 A THIRTY
    // TODO - +1점 A FORTY
    // TODO - +1점 A wins
    // TODO - +1점 B FIFTEEN
    // TODO - +1점 B THIRTY
    // TODO - +1점 B FORTY
    // TODO - +1점 B wins
    // TODO - set end
    // TODO - set ready
    // TODO - 0점 Love
    // TODO - +1점 A FIFTEEN
    // TODO - +1점 A THIRTY
    // TODO - +1점 A FORTY
    // TODO - +1점 B FIFTEEN
    // TODO - +1점 B THIRTY
    // TODO - +1점 B FORTY
    // TODO - +1점 B deuce
    // TODO - +1점 B deuce, advantage
    // TODO - +1점 A deuce
    // TODO - +1점 A deuce, advantage
    // TODO - +1점 A wins
    // TODO - set end
}
