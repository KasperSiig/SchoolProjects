package tictactoe.gui.controller;

import TicTacToe.bll.AiHard;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tictactoe.bll.GameBoard;
import tictactoe.bll.IGameModel;

/**
 * FXML Controller class
 *
 * @author Alex Tygesen & Malthe Schwartz & Kasper Siig
 */
public class MainWindowController implements Initializable
{

    // Declaring the FXML variables
    @FXML
    private TextField txtPlayerOneName;
    @FXML
    private TextField txtPlayerTwoName;
    @FXML
    private Label lblEnterName;
    @FXML
    private Label lblPlayerOnePoints;
    @FXML
    private Label lblPlayerTwoPoints;
    @FXML
    private Label lblScoreboard;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnRestart;
    @FXML
    private Button btnNewPlayers;
    @FXML
    private CheckBox chkboxAI;
    @FXML
    private Label lblPlayerOneName;
    @FXML
    private Label lblPlayerTwoName;

    // Declaring instance variables
    private IGameModel game;
    private TicTacViewController ttCont;
    private boolean gameStart;
    private boolean checkBoxCheck;
    private String gamePath;
    private AiHard aiH;
    @FXML
    private RadioButton rbEasy;
    @FXML
    private RadioButton rbHard;
    @FXML
    private ToggleGroup rb;
    

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        gameStart = false;
        game = GameBoard.getInstance();
        aiH = new AiHard();

        gamePath = "/tictactoe/gui/views/TicTacView.fxml";
    }

    /**
     * Handles the actions that happen when the button is pressed in the main
     * window.
     *
     * @throws IOException
     */
    @FXML
    public void handleButtonActionStart() throws IOException
    {
        if (gameStart == false)
        {
            // Opens the game
            createGame();

            // Updates the score on the main menu
            updateScore();

            // Changes the UI after the game has been closed
            changeUI();
        }
        else
        {
            gameStart = true;
            //In case something goes wrong, it sets the game state to started regardless.
        }
    }

    /**
     * Reset the player names and scores
     */
    @FXML
    private void handleButtonActionNewPlayers()
    {
        game.setPlayerOneName("");
        game.setPlayerTwoName("");
        game.setPlayerOneScore(0);
        game.setPlayerTwoScore(0);
        gameStart = false;

        lblPlayerOneName.setText("Player 1:");
        lblPlayerTwoName.setText("Player 2:");
        txtPlayerOneName.setVisible(true);
        txtPlayerTwoName.setVisible(true);
        lblEnterName.setVisible(true);
        btnStart.setVisible(true);
        chkboxAI.setVisible(true);
        lblPlayerOnePoints.setVisible(false);
        lblPlayerTwoPoints.setVisible(false);
        lblScoreboard.setVisible(false);
        btnRestart.setVisible(false);
        btnNewPlayers.setVisible(false);
    }

    /**
     *
     * @throws IOException
     */
    @FXML
    private void handleButtonActionRestart() throws IOException
    {
        // Creates a new game
        createGame();

        // Updates the score
        updateScore();
    }

    /**
     * Checks whether the AI has been activated or not
     */
    @FXML
    private void handleCheckBoxAI()
    {
        if (checkBoxCheck == false)
        {
            txtPlayerTwoName.setVisible(false);
            lblPlayerTwoName.setText("AI:");
            txtPlayerTwoName.setText("AI");
            checkBoxCheck = true;
            game.setAiIsOn(true);
            rbEasy.setVisible(true);
            rbHard.setVisible(true);

        }
        else
        {
            txtPlayerTwoName.setVisible(true);
            lblPlayerTwoName.setText("Player 2:");
            txtPlayerTwoName.setText("");
            checkBoxCheck = false;
            game.setAiIsOn(false);
            rbEasy.setVisible(false);
            rbHard.setVisible(false);
        }
    }

    /**
     * Creates a new game
     *
     * @throws IOException
     */
    private void createGame() throws IOException
    {
        /* Sets the starting player to always be player 1
        game.setPlayer(0); */

        // Assigns the player names
        game.setPlayerOneName(txtPlayerOneName.getText());
        game.setPlayerTwoName(txtPlayerTwoName.getText());

        // Creates the window with the game
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource(gamePath));
        Parent root = fxLoader.load();
        gameStart = true;

        ttCont = fxLoader.getController();
        RadioButton rbDif = (RadioButton) rb.getSelectedToggle();
        ttCont.setDif(rbDif);
        

        // Opens the window and waits until it's closed before continuing
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.showAndWait();
        ttCont.newGame();
        aiH.preventUltimate();
    }

    /**
     * Updates the two players' scores. Also works for the AI
     */
    private void updateScore()
    {
        lblPlayerOneName.setText(game.getPlayerOneName() + ":");
        lblPlayerTwoName.setText(game.getPlayerTwoName() + ":");
        lblPlayerOnePoints.setText(game.getPlayerOneScore());
        lblPlayerTwoPoints.setText(game.getPlayerTwoScore());
    }

    /**
     * Change the UI from it's default layout
     */
    private void changeUI()
    {
        txtPlayerOneName.setVisible(false);
        txtPlayerTwoName.setVisible(false);
        lblEnterName.setVisible(false);
        btnStart.setVisible(false);
        chkboxAI.setVisible(false);
        lblPlayerOnePoints.setVisible(true);
        lblPlayerTwoPoints.setVisible(true);
        lblScoreboard.setVisible(true);
        btnRestart.setVisible(true);
        btnNewPlayers.setVisible(true);
    }
}
