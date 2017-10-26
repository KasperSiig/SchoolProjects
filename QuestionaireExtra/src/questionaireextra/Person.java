package questionaireextra;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kasper Siig & Kenneth R. Pedersen
 */
public class Person implements java.io.Serializable{

    // Variables
    int score;
    String name;
    List<String> answers = new ArrayList();
    List<Double> currentPoints = new ArrayList();
    List<String> pointsAsString = new ArrayList();


    /**
     * Returns the score
     * 
     * @return 
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the name.
     * 
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * Returns answers array.
     * 
     * @return 
     */
    public List getAnswers() {
        return answers;
    }
}
