/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionaire;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kasper Siig
 */
public class QuestionWindowController implements Initializable {

    @FXML
    private RadioButton rbDisagree;
    @FXML
    private ToggleGroup greenAns;
    @FXML
    private RadioButton rbNeutral;
    @FXML
    private RadioButton rbAgree;
    @FXML
    private RadioButton rbDisagree1;
    @FXML
    private ToggleGroup javaFun;
    @FXML
    private RadioButton rbDisagree2;
    @FXML
    private ToggleGroup javaPrefer;
    @FXML
    private RadioButton rbDisagree3;
    @FXML
    private ToggleGroup assFun;
    @FXML
    private RadioButton rbDisagree4;
    @FXML
    private ToggleGroup assEasy;
    @FXML
    private RadioButton rbDisagree5;
    @FXML
    private ToggleGroup alwaysDo;
    @FXML
    private RadioButton rbDisagree6;
    @FXML
    private ToggleGroup alwaysAttend;
    @FXML
    private RadioButton rbDisagree7;
    @FXML
    private ToggleGroup iUnderstand;
    @FXML
    private RadioButton rbDisagree8;
    @FXML
    private ToggleGroup programFun;
    @FXML
    private RadioButton rbNeutral1;
    @FXML
    private RadioButton rbNeutral2;
    @FXML
    private RadioButton rbNeutral3;
    @FXML
    private RadioButton rbNeutral4;
    @FXML
    private RadioButton rbNeutral5;
    @FXML
    private RadioButton rbNeutral6;
    @FXML
    private RadioButton rbNeutral7;
    @FXML
    private RadioButton rbNeutral8;
    @FXML
    private RadioButton rbAgree1;
    @FXML
    private RadioButton rbAgree2;
    @FXML
    private RadioButton rbAgree3;
    @FXML
    private RadioButton rbAgree4;
    @FXML
    private RadioButton rbAgree5;
    @FXML
    private RadioButton rbAgree6;
    @FXML
    private RadioButton rbAgree7;
    @FXML
    private RadioButton rbAgree8;
    @FXML
    private Label lblName;
    @FXML
    private Button btnCalc;
    @FXML
    private Label lblScore;

    private int score;

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
    
    @FXML
    private void calcScore() {
        ToggleGroup[] groups = {greenAns, javaFun, javaPrefer, assFun, assEasy, alwaysDo, alwaysAttend, iUnderstand, programFun};

        score = 0;

        for (int i = 0; i < groups.length; i++) {
            RadioButton rb = (RadioButton) groups[i].getSelectedToggle();
            if (rb != null) {
                calculate(rb.getText());
            }
        }

        lblScore.setText(String.valueOf(score));
    }

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

    public void changeName(String name) {
        lblName.setText(name);
    }

    private void closeQuestion() {
        Stage stage = (Stage) btnCalc.getScene().getWindow();
        stage.close();
    }

    public int getScore() {
        return score;
    }
}
