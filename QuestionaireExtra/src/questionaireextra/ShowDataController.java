package questionaireextra;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Kasper Siig & Kenneth R. Pedersen
 */
public class ShowDataController implements Initializable {

    //FXML Variables
    @FXML
    private Label lblName;
    @FXML
    private ListView<String> listQ;
    @FXML
    private Label lblScore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    /**
     * Sets the data in the ShowData window.
     * 
     * @param answers
     * @param name
     * @param score 
     */
    public void setData(List answers, String name, int score) {
        lblName.setText("Name:  " + name);
        lblScore.setText("Score:  " + score);
        listQ.setItems(FXCollections.observableArrayList(answers));
    }
}
