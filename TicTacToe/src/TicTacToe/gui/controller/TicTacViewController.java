package tictactoe.gui.controller;

import TicTacToe.bll.AiHard;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import tictactoe.bll.AiEasy;
import tictactoe.bll.GameBoard;
import tictactoe.bll.IGameModel;

/**
 *
 * @author Alex Tygesen & Malthe Schwartz & Kasper Siig
 */
public class TicTacViewController implements Initializable
{

    //Declares the variables used to access the FXML window
    @FXML
    private Label lblPlayer;
    @FXML
    private GridPane gridPane;

    // Declares the local variables
    private static final String TXT_PLAYER = "Player: ";
    private IGameModel game;
    private AiHard aiH;
    private AiEasy aiE;
    private boolean isHard;

    /**
     * Constructor for the JavaFX classes
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        game = GameBoard.getInstance();
        aiH = new AiHard();
        aiE = new AiEasy();
        setNextPlayerLabel();
    }

    /**
     * Sets a X or an O in an empty square and checks if that move won or caused
     * a draw
     *
     * @param event
     */
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        try
        {
            // First extracts the button from the ActionEvent
            Button btn = (Button) event.getSource();

            // Then we check to see if the game has already been won
            if (game.isGameWon(gridPane))
            {
                return;
            }

            // Then we check that the player's move is valid
            if (!game.play(btn.getText()))
            {
                return;
            }

            // If both conditions are met, allows the player to do their move
            handleMoves(btn);

        }
        catch (Exception e)
        {
            // Error message. Heck if I know how that works
            System.out.println(e.getMessage());
        }

        // Checks if the board has been filled. If so, breaks out of this code
        if (checkIsBoardFull())
        {
            return;
        }

        // Check if the game has been won. If so, breaks out of the code
        if (checkIsGameWon())
        {
            return;
        }

        // Sets the player to be the next player
        game.setPlayer(game.getNextPlayer());
        setNextPlayerLabel();

    }

    /**
     * Handles the action of placing down the pieces for both players
     *
     * @param btn The button which the piece should be attempted to be placed in
     */
    private void handleMoves(Button btn)
    {
        // Handles the player's action
        playerMove(btn);

        // Handles the AI's action
        aiMove();
    }

    /**
     * Handles the player's action
     *
     * @param btn The button the player wants to place their piece in
     */
    private void playerMove(Button btn)
    {
        // Gets the ID of the current player
        int player = game.getPlayer();

        // Compares that ID to figure which piece to use
        String xOrO = player == 0 ? "O" : "X";

        // Place that piece into on the board
        btn.setText(xOrO);
    }

    /**
     * Handles the AI's moves
     */
    private void aiMove()
    {
        /* Checks if the AI has been turned on.
        Also checks if the hasn't been won,
        to prevent an error on the last turn. */
        if (game.isAiOn() && !game.isGameWon(gridPane))
        {
            // Sets the AI to be player 2 regardless of it's current state
            game.setPlayer(1);

            // Retrieves the AI's current best move available
            int place;
            if (isHard)
            {
                place = aiH.bestMove(gridPane);
            }
            else
            {
                place = aiE.bestMove(gridPane);
            }
            Button btnAi = (Button) gridPane.getChildren().get(place);
            btnAi.setText("X");
            setNextPlayerLabel();
        }
    }

    private boolean checkIsBoardFull()
    {
        // Check if board is full
        if (!game.isBoardFull(gridPane))
        {
            // If board is full, make it a game over, draw and reset the board
            int winner = -1;
            displayWinner(winner);
            return true;
        }
        return false;
    }

    private boolean checkIsGameWon()
    {
        // Check if game has been won
        if (game.isGameWon(gridPane))
        {
            int winner = game.getWinner();
            displayWinner(winner);
            game.updateScore(winner);
            System.out.println(winner);
            return true;
        }
        return false;
    }

    @FXML
    public void handleNewGame(ActionEvent event)
    {
        game.setPlayer(0);
        clearBoard();
        aiH.preventUltimate();
    }

    public void newGame()
    {
        game.setPlayer(0);
        clearBoard();
        
    }

    /**
     * Sets the current player's name in the top label
     */
    private void setNextPlayerLabel()
    {
        lblPlayer.setText(TXT_PLAYER + game.getPlayerName());
    }

    /**
     * Prints the winning player's ID out
     *
     * @param winner The ID of the winning player
     */
    private void displayWinner(int winner)
    {
        System.out.println("Starting switch");
        String message;
        switch (winner)
        {
            case -1:
                message = "It's a draw :-(";
                break;
            default:
                message = "Player " + game.getPlayerName() + " wins!!!";
                break;
        }
        lblPlayer.setText(message);
        System.out.println("Ending switch");
    }

    /**
     * Clears the board of all pieces
     */
    private void clearBoard()
    {
        gridPane.getChildren().stream().map(
                (n) -> (Button) n).forEachOrdered((btn)
                        ->
                {
                    btn.setText("");
                });
    }

    /**
     * Sets the difficulty of the game against AI
     * 
     * @param rb 
     */
    public void setDif(RadioButton rb)
    {
        if (rb.getText().equals("Easy"))
        {

            isHard = false;
        }
        else
        {
            System.out.println("he");
            isHard = true;
        }

    }

}
