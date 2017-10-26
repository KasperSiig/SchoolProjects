/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

import javafx.scene.layout.GridPane;

/**
 *
 * @author Stegger
 */
public interface IGameModel
{

    // Player 1
    // Getters
    public String getPlayerOneName();

    public String getPlayerOneScore();

    // Setters
    public void setPlayerOneName(String string);

    public void setPlayerOneScore(int i);

    // Player 2
    // Getters
    public String getPlayerTwoName();

    public String getPlayerTwoScore();

    // Setters
    public void setPlayerTwoName(String string);

    public void setPlayerTwoScore(int i);

    public String getPlayerName();

    public int getPlayer();

    public int getNextPlayer();

    public int getWinner();

    public void setPlayer(int i);

    public void updateScore(int winner);

    public boolean play(String btn);

    public boolean isGameWon(GridPane gridPane);

    public boolean isBoardFull(GridPane gridPane);

    public void setAiIsOn(boolean b);

    public boolean isAiOn();

    public void newGame();
}
