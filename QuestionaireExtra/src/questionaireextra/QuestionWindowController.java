package questionaireextra;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kasper Siig & Kenneth R. Pedersen
 */
public class QuestionWindowController implements Initializable {

    //FXML Variables
    @FXML
    private Label lblName;
    @FXML
    private Button btnCalc;
    @FXML
    private Label lblScore;
    @FXML
    private VBox vboxAgree;
    @FXML
    private VBox vboxNeutral;
    @FXML
    private VBox vboxDisagree;
    @FXML
    private VBox vboxQ;

    //Variables
    private int score;

    //Lists
    private List<ToggleGroup> groups = new ArrayList();

    private List<String> answers = new ArrayList();

    private List<Double> currentPoints = new ArrayList();

    //FXML Methods
    /**
     * FXML method which calculates score and closes QuestionWindow.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void btnCalc(ActionEvent event) throws IOException {
        calcScore();
        closeQuestion();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //Methods
    /**
     * Calls calculate() and adds score to label.
     */
    private void calcScore() {
        score = 0;
        for (int i = 0; i < groups.size(); i++) {
            RadioButton rb = (RadioButton) groups.get(i).getSelectedToggle();
            if (rb != null) {
                calculate(rb.getText());
            }
        }

        lblScore.setText(String.valueOf(score));
    }

    /**
     * Calculates the score
     *
     * @param ans
     */
    private void calculate(String ans) {
        switch (ans) {
            case "Agree":
                score++;
                break;
            case "Disagree":
                score--;
                break;
            default:
                return;
        }
    }

    /**
     * Changes the name at top of QuestionWindow
     *
     * @param name
     */
    public void changeName(String name) {
        lblName.setText(name);
    }

    /**
     * Closes QuestionWindow and adds answers to answer array.
     */
    private void closeQuestion() {
        for (int i = 0; i < groups.size(); i++) {
            RadioButton rb = (RadioButton) groups.get(i).getSelectedToggle();
            if (rb != null) {
                answers.add("Q" + (i + 1) + ":  " + rb.getText());
            } else {
                answers.add("Q" + (i + 1) + ":  " + "N/A");
            }
            if (rb != null) {
                switch (rb.getText()) {
                    case "Disagree":
                        currentPoints.add(-1.0);
                        break;
                    case "Neutral":
                        currentPoints.add(0.0);
                        break;
                    case "Agree":
                        currentPoints.add(1.0);
                        break;
                }
            } else if (rb == null) {
                currentPoints.add(0.0);
            }
        }
        Stage stage = (Stage) btnCalc.getScene().getWindow();

        stage.close();
    }

    /**
     * Gets score
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    public List getPoints() {
        return currentPoints;
    }
    /**
     * Dynamically inserts questions, taken from questions array in
     * MainWindowController.
     *
     * @param questions
     */
    public void setQuestions(String[] questions) {

        for (int i = 0; i < questions.length; i++) {
            //Adds question to vBox
            Label label1 = new Label(questions[i]);
            vboxQ.getChildren().add(label1);

            ToggleGroup toggleGroup = new ToggleGroup();

            //Creates new RadioButton, sets ToggleGroup
            //and calculates score when RadioButton is pressed.
            RadioButton rbDisagree = new RadioButton("Disagree");
            vboxDisagree.getChildren().add(rbDisagree);
            rbDisagree.setToggleGroup(toggleGroup);
            rbDisagree.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    calcScore();
                }
            });

            RadioButton rbNeutral = new RadioButton("Neutral");
            vboxNeutral.getChildren().add(rbNeutral);
            rbNeutral.setToggleGroup(toggleGroup);
            rbNeutral.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    calcScore();
                }
            });

            RadioButton rbAgree = new RadioButton("Agree");
            vboxAgree.getChildren().add(rbAgree);
            rbAgree.setToggleGroup(toggleGroup);
            rbAgree.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    calcScore();
                }
            });

            groups.add(toggleGroup);
        }
    }

    /**
     * Returns answers.
     *
     * @return
     */
    public List getAnswers() {
        return answers;
    }
}
