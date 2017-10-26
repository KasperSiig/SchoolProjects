package tictactoe.bll;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Alex Tygesen & Malthe Schwartz & Kasper Siig
 */
public class GameBoard implements IGameModel
{
    
    // Declaring the local variables.
    private int currentPlayer;
    private String playerOneName = "";
    private String playerTwoName = "";
    private int playerOneScore;
    private int playerTwoScore;
    private boolean aiIsOn;
    private static GameBoard instance;

    // Uses singleton to prevent multiple instances
    public static GameBoard getInstance()
    {
        if (instance == null)
        {
            instance = new GameBoard();
        }
        return instance;
    }

    
    // Private constructor makes it so only one instance can be made
    private GameBoard() {}

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    @Override
    public int getNextPlayer()
    {
        // Checks if the current player's ID is 0, as in player 1
        if (currentPlayer == 0)
        {
            //If the current player's ID is 0, change it to be 1, as in player 2
            currentPlayer = 1;
        }
        else
        {
            /*
             * The the current player's ID isn't 0, as in it's player 2, set the
             * ID to be 0, player 1
             */
            currentPlayer = 0;
        }

        // Return the current player's value after it's been changed
        return currentPlayer;
    }

    /**
     * Attempts to let the current player play at the given coordinates. If the
     * attempt is succesful the current player has ended his turn and it is the
     * next players turn.
     *
     * @param btn The text of the button clicked
     *
     * @return true if the move is accepted, otherwise false. If gameOver ==
     *         true this method will always return false.
     */
    @Override
    public boolean play(String btn)
    {
        return btn.isEmpty();
    }

    /**
     * Checks if the game is over
     *
     * @param gridPane The board as a GridPane
     *
     * @return True of all win conditions are met, false if none of them are met
     */
    @Override
    public boolean isGameWon(GridPane gridPane)
    {
        // <editor-fold desc="Getting and converting GridPane to an useful format">
        Button btn1 = (Button) gridPane.getChildren().get(0);
        Button btn2 = (Button) gridPane.getChildren().get(1);
        Button btn3 = (Button) gridPane.getChildren().get(2);
        Button btn4 = (Button) gridPane.getChildren().get(3);
        Button btn5 = (Button) gridPane.getChildren().get(4);
        Button btn6 = (Button) gridPane.getChildren().get(5);
        Button btn7 = (Button) gridPane.getChildren().get(6);
        Button btn8 = (Button) gridPane.getChildren().get(7);
        Button btn9 = (Button) gridPane.getChildren().get(8);

        String btn1txt = btn1.getText();
        String btn2txt = btn2.getText();
        String btn3txt = btn3.getText();
        String btn4txt = btn4.getText();
        String btn5txt = btn5.getText();
        String btn6txt = btn6.getText();
        String btn7txt = btn7.getText();
        String btn8txt = btn8.getText();
        String btn9txt = btn9.getText();
        // </editor-fold>

        return checkMatch(btn1txt, btn2txt, btn3txt)
               || checkMatch(btn4txt, btn5txt, btn6txt)
               || checkMatch(btn7txt, btn8txt, btn9txt)
               || checkMatch(btn1txt, btn4txt, btn7txt)
               || checkMatch(btn2txt, btn5txt, btn8txt)
               || checkMatch(btn3txt, btn6txt, btn9txt)
               || checkMatch(btn1txt, btn5txt, btn9txt)
               || checkMatch(btn3txt, btn5txt, btn7txt);
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    @Override
    public int getWinner()
    {
        return currentPlayer;
    }

    /**
     * Might be obsolete?
     */
    @Override
    public void newGame()
    {
        //TODO Implement this method
    }

    /**
     * Checks if all 3 input match each other.
     *
     * @param first  The first input
     * @param second The second input
     * @param third  The last input
     *
     * @return True if all input are the same. False is even one is different,
     *         or empty
     */
    private boolean checkMatch(String first, String second, String third)
    {
        // First makes sure the given inputs aren't empty
        if (!first.isEmpty())
        {
            // Set the winner to be the current player
            return first.equals(second) && first.equals(third);
        }
        /*
         * If one or more of them are empty, set he current winner to -1 and
         * return false
         */
        return false;
    }

    /**
     *
     * @param gridPane
     *
     * @return
     */
    @Override
    public boolean isBoardFull(GridPane gridPane)
    {
        // <editor-fold desc="Getting and converting GridPane to an useful format">
        Button btn1 = (Button) gridPane.getChildren().get(0);
        Button btn2 = (Button) gridPane.getChildren().get(1);
        Button btn3 = (Button) gridPane.getChildren().get(2);
        Button btn4 = (Button) gridPane.getChildren().get(3);
        Button btn5 = (Button) gridPane.getChildren().get(4);
        Button btn6 = (Button) gridPane.getChildren().get(5);
        Button btn7 = (Button) gridPane.getChildren().get(6);
        Button btn8 = (Button) gridPane.getChildren().get(7);
        Button btn9 = (Button) gridPane.getChildren().get(8);

        String btn1txt = btn1.getText();
        String btn2txt = btn2.getText();
        String btn3txt = btn3.getText();
        String btn4txt = btn4.getText();
        String btn5txt = btn5.getText();
        String btn6txt = btn6.getText();
        String btn7txt = btn7.getText();
        String btn8txt = btn8.getText();
        String btn9txt = btn9.getText();
        // </editor-fold>

        return btn1txt.isEmpty()
               || btn2txt.isEmpty()
               || btn3txt.isEmpty()
               || btn4txt.isEmpty()
               || btn5txt.isEmpty()
               || btn6txt.isEmpty()
               || btn7txt.isEmpty()
               || btn8txt.isEmpty()
               || btn9txt.isEmpty();

    }

    /**
     * Sets boolean variable to show that AI should be used
     * 
     * @param aiIsOn 
     */
    @Override
    public void setAiIsOn(boolean aiIsOn)
    {
        this.aiIsOn = aiIsOn;
    }

    /**
     * Sets boolean which checks if AI is on
     * 
     * @return 
     */
    @Override
    public boolean isAiOn()
    {
        return aiIsOn;
    }

    /**
     * Returns the current player's name
     *
     * @return A String containing the current player's name
     */
    @Override
    public String getPlayerName()
    {
        if (currentPlayer == 0)
        {
            return playerOneName;
        }
        else
        {
            return playerTwoName;
        }
    }

    /**
     * Getter for currentPlayer
     * 
     * @return 
     */
    @Override
    public int getPlayer()
    {
        return currentPlayer;
    }

    /**
     * Getter for playerOneName
     * @return 
     */
    @Override
    public String getPlayerOneName()
    {
        return playerOneName;
    }

    /**
     * Getter for playerOneScore
     * 
     * @return 
     */
    @Override
    public String getPlayerOneScore()
    {
        return "" + playerOneScore;
    }

    /**
     * Getter for playerTwoName
     * 
     * @return 
     */
    @Override
    public String getPlayerTwoName()
    {
        return playerTwoName;
    }

    /**
     * Getter for playerTwoScore
     * 
     * @return 
     */
    @Override
    public String getPlayerTwoScore()
    {
        return "" + playerTwoScore;
    }

    /**
     * Setter for currentPlayer
     * 
     * @param i 
     */
    @Override
    public void setPlayer(int i)
    {
        currentPlayer = i;
    }

    /**
     * Setter for playerOneName
     * 
     * @param name 
     */
    @Override
    public void setPlayerOneName(String name)
    {
        playerOneName = name;
    }

    /**
     * Setter for playerOneScore
     * 
     * @param score 
     */
    @Override
    public void setPlayerOneScore(int score)
    {
        playerOneScore = score;
    }

    /**
     * Setter for playerTwoName
     * 
     * @param name 
     */
    @Override
    public void setPlayerTwoName(String name)
    {
        playerTwoName = name;
    }

    /**
     * Setter for playerTwoScore
     * 
     * @param score 
     */
    @Override
    public void setPlayerTwoScore(int score)
    {
        playerTwoScore = score;
    }

    /**
     * Updates the score shown on MainWindow
     * 
     * @param winner 
     */
    @Override
    public void updateScore(int winner)
    {
        if (winner == 0)
        {
            playerOneScore++;
        }
        else
        {
            playerTwoScore++;
        }
    }

}
