/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionaire;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Kasper Siig
 */
public class MainWindowController implements Initializable {

    @FXML
    private Button submit;
    @FXML
    private TextField txtName;
    @FXML
    private ListView<String> listNames;

    private List names = new ArrayList();

    private List scores = new ArrayList();

    ObservableList<String> items = FXCollections.observableArrayList();

    @FXML
    private void submitButton(ActionEvent event) throws IOException {
        openQuestion();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void openQuestion() throws IOException {
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("QuestionWindow.fxml"));
        Parent root = fxLoader.load();

        QuestionWindowController cont = fxLoader.getController();
        cont.changeName(txtName.getText());

        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.showAndWait();
        printScore(cont.getScore());
    }

    private void addName(String name) {
        names.add(name);
    }

    private void addScore(String score) {
        scores.add(score);
    }

    private void printScore(int score) {
        items.clear();
        if (names.contains(txtName.getText())) {
            int pos = names.indexOf(txtName.getText());
            scores.set(pos, score);
        } else {
            addName(txtName.getText());
            addScore(Integer.toString(score));
        }
        for (int i = 0; i < names.size(); i++) {
            String participant = names.get(i) + ":  " + scores.get(i);
            items.add(participant);
            listNames.setItems(items);
        }
    }
}
