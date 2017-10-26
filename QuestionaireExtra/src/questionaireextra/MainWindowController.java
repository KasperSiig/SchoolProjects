package questionaireextra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.security.krb5.internal.crypto.Des3;

/**
 * @author Kasper Siig & Kenneth R. Pedersen
 */
public class MainWindowController implements Initializable {

    //FXML Variables.
    @FXML
    private TextField txtName;
    @FXML
    private ListView<String> listNames;

    //Lists
    private List names = new ArrayList();

    private List scores = new ArrayList();

    private List persons = new ArrayList();

    private List<Double> currentPoints = new ArrayList();

    private List<Double> avgPointsList = new ArrayList();

    //Observable Lists
    ObservableList<String> items = FXCollections.observableArrayList();

    //Booleans
    private boolean hasCreatedPersons = false;

    //Arrays
    private String[] questions = {
        "I like to program in Greenfoot",
        "I think Java is fun",
        "Java is my preferred language",
        "My assignments are fun",
        "My assignements are too easy",
        "I always do my assignments",
        "I always attend class",
        "I understand what is going on in class",
        "I think programming is fun",
        "Extra assignments are easy",
        "We got the best teachers", "I like to program in Greenfoot",
        "I think Java is fun",
        "Java is my preferred language",
        "My assignments are fun",
        "My assignements are too easy",
        "I always do my assignments",
        "I always attend class",
        "I understand what is going on in class",
        "I think programming is fun",
        "Extra assignments are easy",
        "We got the best teachers", "I like to program in Greenfoot",
        "I think Java is fun",
        "Java is my preferred language",
        "My assignments are fun",
        "My assignements are too easy",
        "I always do my assignments",
        "I always attend class",
        "I understand what is going on in class",
        "I think programming is fun",
        "Extra assignments are easy",
        "We got the best teachers"
    };

    List<Double> totalPoints = new ArrayList();

    private int avgPoints = questions.length;
    @FXML
    private Button submit;
    @FXML
    private Button btnGraph;

    //FXML Methods
    @FXML
    private void submitButton(ActionEvent event) throws IOException {
        openQuestion();
    }

    /**
     * Checks to see if an item in ListView has been doubleClicked, and shows
     * info about item if true.
     */
    @FXML
    private void onNameClicked() {
        listNames.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        try {
                            showInfo();
                        } catch (IOException ex) {
                            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
    }

    /**
     * Opens graph of average points.
     *
     * @throws IOException
     */
    @FXML
    private void showGraph() throws IOException {
        //Sets new Stage and loads GraphWindow into it.
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("GraphWindow.fxml"));
        Parent root = fxLoader.load();

        GraphWindowController cont = fxLoader.getController();
        cont.setGraph(avgPointsList);

        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.showAndWait();
    }

    /**
     * Saves data to personsData.ser file.
     */
    @FXML
    private void saveData() {
        //try-catch has to be used in Java, otherwise a stream won't work
        try {
            //Initializes a FileOutputStream, and loads personsData.ser into it
            FileOutputStream out = new FileOutputStream("personsData.ser");

            //Initializes an ObjectOutputStream and loads the FileOutputStream into it
            ObjectOutputStream oout = new ObjectOutputStream(out);

            //For loop which writes every person to the personsData.ser file.
            for (int i = 0; i < persons.size(); i++) {
                //Assigns the object to be written to a variable
                Person person = (Person) persons.get(i);

                //writeObject writes the person variable to the previously loaded file.
                oout.writeObject(person);
            }
            //Closes the stream.
            oout.close();
        } catch (IOException ex) {
        }
    }

    /**
     * Deletes items from ListView and deletes personsData.ser file.
     */
    @FXML
    private void delData() {
        //Clears the items and persons Lists
        items.clear();
        persons.clear();
        totalPoints.clear();
        avgPointsList.clear();

        //Clears the ListView by setting it to show an empty List
        listNames.setItems(items);

        //Deletes the personsData.ser file
        File f = new File("personsData.ser");
        f.delete();

        //Sets the hasCreatedPersons variable to false
        hasCreatedPersons = false;
    }

    //Method that initializes MainWindow
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File f = new File("personsData.ser");
        if (f.isFile()) {
            loadData();
        }
    }

    //Methods
    /**
     * Opens up QuestionWindow
     *
     * @throws IOException
     */
    private void openQuestion() throws IOException {
        //Sets new Stage and loads QuestionWindow into it.
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("QuestionWindow.fxml"));
        Parent root = fxLoader.load();

        //Gets all methods from QuestionWindowController, and uses some.
        QuestionWindowController cont = fxLoader.getController();
        cont.changeName(txtName.getText());
        cont.setQuestions(questions);

        //Sets the new scene, and runs a method once it has been closed.
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.showAndWait();
        currentPoints = cont.getPoints();
        printScore(cont.getScore(), cont.getAnswers());
    }

    /**
     * Creates new Person if participant doesn't exist. Otherwise updates
     * participants score and answers.
     *
     * @param score
     * @param answers
     */
    private void printScore(int score, List answers) {
        //In order for the for loop to work, a Person has to exist first,
        //Therefore a boolean tells if a Person already exists or not.
        if (hasCreatedPersons == false) {
            addParticipant(score, answers, 0, currentPoints);
            Person person = (Person) persons.get(0);
            saveListAsString(currentPoints, person);
            calcAvg();
            hasCreatedPersons = true;
        } else {
            //For loop to check if Person should be updated or added.
            for (int i = 0; i < persons.size(); i++) {
                Person person = (Person) persons.get(i);
                if (person.name.equals(txtName.getText()) && checkName()) {
                    //subAvg() is used so that one person cannot manipulate the avg by answering multiple times.
                    //subAvg();
                    updateParticipant(score, answers, i, currentPoints);
                    saveListAsString(person.currentPoints, person);
                    calcAvg();

                } else if (!checkName()) {
                    addParticipant(score, answers, i, currentPoints);
                    saveListAsString(person.currentPoints, person);

                }
            }
        }
        listNames.setItems(items);

    }

    /**
     * Creates new Person object.
     *
     * @param score
     * @param name
     * @param answers
     */
    private void createPerson(int score, String name, List answers, List currentPoints) {
        Person person = new Person();
        person.score = score;
        person.name = txtName.getText();
        person.answers = answers;
        person.currentPoints = currentPoints;

        persons.add(person);
    }

    /**
     * Opens up ShowData Window which shows what answers a person has given to
     * each question.
     *
     * @throws IOException
     */
    private void showInfo() throws IOException {
        int index = listNames.getSelectionModel().getSelectedIndex();
        Person person = (Person) persons.get(index);
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("ShowData.fxml"));
        Parent root = fxLoader.load();

        ShowDataController cont = fxLoader.getController();
        cont.setData(person.getAnswers(), person.getName(), person.getScore());

        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.showAndWait();
    }

    /**
     * Checks to see if Person already exist in persons array.
     *
     * @return
     */
    private boolean checkName() {
        for (int i = 0; i < persons.size(); i++) {
            Person person = (Person) persons.get(i);
            if (person.name.equals(txtName.getText())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds participant.
     *
     * @param score
     * @param answers
     * @param index
     */
    private void addParticipant(int score, List answers, int index, List currentPoints) {
        createPerson(score, txtName.getText(), answers, currentPoints);
        Person person = (Person) persons.get(index);
        String participant = person.getName() + ": " + person.score;
        items.add(participant);
    }

    /**
     * Updates the participant.
     *
     * @param score
     * @param answers
     * @param index
     */
    private void updateParticipant(int score, List answers, int index, List currentPoints) {
        Person person = (Person) persons.get(index);
        person.score = score;
        person.answers = answers;
        person.currentPoints = currentPoints;
        String participant = person.getName() + ": " + person.score;
        items.set(index, participant);
    }

    /**
     * Calculates the average points
     */
    private void calcAvg() {
        //If avgPointsList is empty, set it to currentPoints
        //This way it is possible to get values from the List in the else statement
        if (avgPointsList.isEmpty()) {
            avgPointsList = currentPoints;
            totalPoints = currentPoints;
        } else {
            //Adds the current points to the total points
            List<Double> temp = new ArrayList();
            for (int i = 0; i < avgPointsList.size(); i++) {
                double cur = currentPoints.get(i);
                double tot = totalPoints.get(i);
                double sum = cur + tot;
                temp.add(sum);
                //totalPoints.set(i, sum);
            }
            totalPoints = temp;
        }

        //Calculates the average, and sets the new number in avgPointsList
        for (int i = 0; i < avgPointsList.size(); i++) {
            double tot = totalPoints.get(i);
            double sum = tot / persons.size();
            avgPointsList.set(i, sum);
        }
    }

    /**
     * Subtracts the current points from the total points.
     */
    private void subAvg() {
        for (int i = 0; i < totalPoints.size(); i++) {
            double tot = totalPoints.get(i);
            double cur = currentPoints.get(i);
            double sum = tot - cur;
            totalPoints.set(i, sum);
        }
    }

    /**
     * Loads the data and inserts it into the ListView
     */
    private void loadData() {
        try {
            //Initializes the ObjectInputStream, and loads the personsData.ser file into it
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("personsData.ser"));

            //While-loop is used to run through every object in personsData.ser file until empty.
            //This will produce an exception in terminal, this can be ignored
            while (true) {
                //Reads the next (or first) object from personsData.ser and loads it into a Person variable
                Person person = (Person) ois.readObject();

                //Creates string to be loaded into ListView
                String participant = person.getName() + ": " + person.score;
                loadListAsDouble(person.pointsAsString, person);
                items.add(participant);
                persons.add(person);
                calcAvg();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        listNames.setItems(items);
        hasCreatedPersons = true;
        addTotalAvg();
    }

    /**
     * Adds all persons currentPoints to totalPoints
     */
    private void addTotalAvg() {
        for (int i = 0; i < persons.size(); i++) {
            Person person = (Person) persons.get(i);

            if (totalPoints.isEmpty()) {
                totalPoints = person.currentPoints;
            } else {
                totalPoints(person);
            }

        }
        //Calculates the average, and sets the new number in avgPointsList
        for (int i = 0; i < totalPoints.size(); i++) {
            double tot = totalPoints.get(i);
            double sum = tot / persons.size();
            avgPointsList.add(sum);
        }
    }

    /**
     * Saves persons currentPoints as a String List
     * 
     * @param curPoints
     * @param person 
     */
    private void saveListAsString(List<Double> curPoints, Person person) {

        List<String> strings = new ArrayList<String>();
        for (Double d : curPoints) {
            // Apply formatting to the string if necessary
            strings.add(d.toString());
        }
        person.pointsAsString = strings;
    }

    /**
     * Parses a persons String list with points, to a Double list
     * 
     * @param listAsString
     * @param person 
     */
    private void loadListAsDouble(List<String> listAsString, Person person) {
        List<Double> listAsDouble = new ArrayList();
        for (int i = 0; i < listAsString.size(); i++) {
            String text = listAsString.get(i);
            double value = Double.parseDouble(text);
            listAsDouble.add(value);
        }
        person.currentPoints = listAsDouble;
    }

    /**
     * Adds persons currentPoints to totalPoints
     * 
     * @param person 
     */
    private void totalPoints(Person person) {
        for (int j = 0; j < totalPoints.size(); j++) {
            double cur = person.currentPoints.get(j);
            double tot = totalPoints.get(j);
            double sum = cur + tot;
            totalPoints.set(j, sum);

        }
    }
}
