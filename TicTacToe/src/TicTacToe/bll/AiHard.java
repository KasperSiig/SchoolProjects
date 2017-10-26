package TicTacToe.bll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Kasper Siig & Kenneth R. Pedersen
 */
public class AiHard {
    private List<Integer> corners;
    // List which contains positions of the sides
    private List<Integer> sides;
    
    // List which contains the text of all buttons
    private List<String> btnList;
    // Boolean which helps prevent shit attack from player
    private boolean hasBlockedTheUltimateDiagonalAttackFromDeath;
    // Boolean which helps prevent evil attacks from player
    private boolean hasPreventedEvil;

    public AiHard() {
        this.hasBlockedTheUltimateDiagonalAttackFromDeath = false;
        this.hasPreventedEvil = false;
        this.sides = new ArrayList();
        this.corners = new ArrayList();
    }
    
    /**
     * Calculates the best move for AI, and returns index for best position.
     * 
     * @param gridPane
     * @return 
     */
    public int bestMove(GridPane gridPane) {
        // Retrieves the actual button from the GridPane
        Button btn1 = (Button) gridPane.getChildren().get(0);
        Button btn2 = (Button) gridPane.getChildren().get(1);
        Button btn3 = (Button) gridPane.getChildren().get(2);
        Button btn4 = (Button) gridPane.getChildren().get(3);
        Button btn5 = (Button) gridPane.getChildren().get(4);
        Button btn6 = (Button) gridPane.getChildren().get(5);
        Button btn7 = (Button) gridPane.getChildren().get(6);
        Button btn8 = (Button) gridPane.getChildren().get(7);
        Button btn9 = (Button) gridPane.getChildren().get(8);

        // Gets the text from the buttons, and assigns it to a String
        String btn1txt = btn1.getText();
        String btn2txt = btn2.getText();
        String btn3txt = btn3.getText();
        String btn4txt = btn4.getText();
        String btn5txt = btn5.getText();
        String btn6txt = btn6.getText();
        String btn7txt = btn7.getText();
        String btn8txt = btn8.getText();
        String btn9txt = btn9.getText();
        
        // Adds the text from the buttons to btnList ArrayList
        btnList = new ArrayList();
        btnList.add(btn1txt);
        btnList.add(btn2txt);
        btnList.add(btn3txt);
        btnList.add(btn4txt);
        btnList.add(btn5txt);
        btnList.add(btn6txt);
        btnList.add(btn7txt);
        btnList.add(btn8txt);
        btnList.add(btn9txt);

        /* Since it will almost always be the best move to place a piece in the
        middle, this makes sure that the middle is always preferred */
        if (btn5txt.isEmpty()) {
            return 4;
        }

        // Adds position of the corners to corners List
        corners.add(0);
        corners.add(2);
        corners.add(6);
        corners.add(8);
        
        // Adds position of the sides to sides List
        sides.add(1);
        sides.add(3);
        sides.add(5);
        sides.add(7);
        
        // BEWARE OF NESTED IF STATEMENTS! too lazy to refactor to method...
        // Checks to see if it is possible to win
        int index = playToWin(btn1txt, btn2txt, btn3txt, btn4txt, btn5txt, btn6txt, btn7txt, btn8txt, btn9txt);
        
        // If a win is not possible, playToWin() will return -1
        if (index == -1) {
            index = playToDefend(btn1txt, btn2txt, btn3txt, btn4txt, btn5txt, btn6txt, btn7txt, btn8txt, btn9txt);
            if (index == -1) {
                // If a win or defense is not possible, place random
                index = getRandPos(btn1txt, btn3txt, btn7txt, btn9txt, corners, btnList);
                if (index == -1) {
                    index = getRandPos(btn2txt, btn4txt, btn6txt, btn8txt, sides, btnList);
                }
            }
        } 
        
        return index;
    }

    /**
     * Computes the move where it is possible to win
     * 
     * @param btn1
     * @param btn2
     * @param btn3
     * @param btn4
     * @param btn5
     * @param btn6
     * @param btn7
     * @param btn8
     * @param btn9
     * @return 
     */
    private int playToWin(String btn1, String btn2, String btn3, String btn4, String btn5, String btn6, String btn7, String btn8, String btn9) {
        // Assigns all the moves to String arrays, which can the be used in a for loop
        String[] input1 = {btn1, btn1, btn2, btn5, btn4, btn4, btn8, btn7, btn7, btn4, btn1, btn1, btn5, btn2, btn2, btn6, btn3, btn3, btn5, btn1, btn1, btn5, btn3, btn3};
        String[] input2 = {btn2, btn3, btn3, btn6, btn6, btn5, btn9, btn9, btn8, btn7, btn7, btn4, btn8, btn8, btn5, btn9, btn9, btn6, btn9, btn9, btn5, btn7, btn7, btn5};
        String[] input3 = {btn3, btn2, btn1, btn4, btn5, btn6, btn7, btn8, btn9, btn1, btn4, btn7, btn2, btn5, btn8, btn3, btn6, btn9, btn1, btn5, btn9, btn3, btn5, btn7};
        int[] returnValue = {2, 1, 0, 3, 4, 5, 6, 7, 8, 0, 3, 6, 1, 4, 7, 2, 5, 8, 0, 4, 8, 2, 4, 6};

        // For loop which calls calcWin() on all possible combinations
        for (int i = 0; i < input1.length; i++) {
            // Single line if statement, which return the index of the best move
            if (calcWin(input1[i], input2[i], input3[i])) return returnValue[i];
        }
        
        return -1;
    }
    
    /**
     * Computes the move where it is possible to defend
     * 
     * @param btn1
     * @param btn2
     * @param btn3
     * @param btn4
     * @param btn5
     * @param btn6
     * @param btn7
     * @param btn8
     * @param btn9
     * @return 
     */
    private int playToDefend(String btn1, String btn2, String btn3, String btn4, String btn5, String btn6, String btn7, String btn8, String btn9) {
        // Assigns all the moves to String arrays, which can the be used in a for loop
        String[] input1 = {btn1, btn1, btn2, btn5, btn4, btn4, btn8, btn7, btn7, btn4, btn1, btn1, btn5, btn2, btn2, btn6, btn3, btn3, btn5, btn1, btn1, btn5, btn3, btn3};
        String[] input2 = {btn2, btn3, btn3, btn6, btn6, btn5, btn9, btn9, btn8, btn7, btn7, btn4, btn8, btn8, btn5, btn9, btn9, btn6, btn9, btn9, btn5, btn7, btn7, btn5};
        String[] input3 = {btn3, btn2, btn1, btn4, btn5, btn6, btn7, btn8, btn9, btn1, btn4, btn7, btn2, btn5, btn8, btn3, btn6, btn9, btn1, btn5, btn9, btn3, btn5, btn7};
        int[] returnValue = {2, 1, 0, 3, 4, 5, 6, 7, 8, 0, 3, 6, 1, 4, 7, 2, 5, 8, 0, 4, 8, 2, 4, 6};
        
        // Prevents an attack which would ensure a win every time
        if (preventTheUltimateAttackFromDeath(btn1, btn9, btn5, btn2, btn4, btn6, btn8, btn3, btn7)) return getRandPos(btn2, btn4, btn6, btn8, sides, btnList);
        
        // An evil attack from player could be made, this prevents that
        if (hasPreventedEvil) {
            String[] input4 = {btn1, btn3, btn2, btn6, btn2, btn4, btn3, btn1};            
            String[] input5 = {btn8, btn4, btn9, btn7, btn7, btn9, btn8, btn6};            
            String[] input6 = {btn7, btn1, btn3, btn9, btn1, btn7, btn9, btn3};
            int[] returnValue1 = {6, 0, 2, 8, 0, 6, 8, 2};
            
            // Evil attacks
            for (int i = 0; i < input1.length; i++) {
                if (calcDefend(input4[i], input5[i], input6[i])) return returnValue1[i];
            }
        }
        
        // For loop which calls calcDefend() on all possible combinations
        for (int i = 0; i < input1.length; i++) {
            if (calcDefend(input1[i], input2[i], input3[i])) return returnValue[i];
        }
        
        return -1;
    }

    /**
     * Calculates the move that will win the match
     * 
     * @param btn1
     * @param btn2
     * @param btn3
     * @return 
     */
    private boolean calcWin(String btn1, String btn2, String btn3) {
        return "X".equals(btn1) && "X".equals(btn2) && btn3.isEmpty();
    }
    
    /**
     * Calculates the moves that will prevent player from winning
     * 
     * @param btn1
     * @param btn2
     * @param btn3
     * @return 
     */
    private boolean calcDefend(String btn1, String btn2, String btn3) {
        return "O".equals(btn1) && "O".equals(btn2) && btn3.isEmpty();
    }
    
    /**
     * Prevents a stupid move that would otherwise ensure win for player
     * every time
     * 
     * @param btn1
     * @param btn9
     * @param btn5
     * @param btn2
     * @param btn4
     * @param btn6
     * @param btn8
     * @param btn3
     * @param btn7
     * @return 
     */
    private boolean preventTheUltimateAttackFromDeath(String btn1, String btn9, String btn5, String btn2, String btn4, String btn6, String btn8, String btn3, String btn7) {
        if (!hasBlockedTheUltimateDiagonalAttackFromDeath) {
            if ("O".equals(btn1) && "O".equals(btn9) && "X".equals(btn5)) {
                hasBlockedTheUltimateDiagonalAttackFromDeath = true;
                return true;
            }
            if ("O".equals(btn3) && "O".equals(btn7) && "X".equals(btn5)) {
                hasBlockedTheUltimateDiagonalAttackFromDeath = true;
                return true;
            }
        }
        return false;
    }

    /**
     * Places the X at a random position
     * 
     * @param btn1
     * @param btn2
     * @param btn3
     * @param btn4
     * @param list
     * @param btnList
     * @return 
     */
    private int getRandPos(String btn1, String btn2, String btn3, String btn4, List list, List btnList) {
        // First checks if the given buttons are empty.
        if (btn1.isEmpty() || btn2.isEmpty() || btn3.isEmpty() || btn4.isEmpty()) {

            // Shuffles the given list
            Collections.shuffle(list);
            
            /* For loop which checks all the buttons in list if isEmpty, and returns
            the first button that is empty.
            This is why shuffle is used, to ensure different outcomes. */
            for (int i = 0; i < 4; i++) {
                // Integers in list are being turned into Objects, so a little conversion is used
                String text = String.valueOf(list.get(i));
                int num = Integer.parseInt(text);
                
                // Sets variable btn to be the button that we would like to check if empty
                String btn = String.valueOf(btnList.get(num));
                
                // If the button is actually empty, return the index of this button
                if (btn.isEmpty()) {
                    return num;
                }
            }
        }
        return -1;
    }
    
    public void preventUltimate() {
        hasBlockedTheUltimateDiagonalAttackFromDeath = false;
    }
    
}
