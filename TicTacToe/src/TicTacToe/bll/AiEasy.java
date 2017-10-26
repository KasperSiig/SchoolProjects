package tictactoe.bll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Alex Tygesen & Malthe Schwartz & Kasper Siig
 */
public class AiEasy {

    // List which contains positions of the corners
    private List<Integer> corners = new ArrayList();
    
    // List which contains positions of the sides
    private List<Integer> sides = new ArrayList();
    
    // List which contains the text of all buttons
    private List<String> btnList;

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
        
        /* getRandPos() returns the index of the best possible move. Hereafter 
        it is assigned to the variable index */
        int index = getRandPos(btn1txt, btn3txt, btn7txt, btn9txt, corners, btnList);
        // if no match is found, getRandPos() returns -1
        if (index == -1) {
            index = getRandPos(btn2txt, btn4txt, btn6txt, btn8txt, sides, btnList);
        }
        return index;
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

}
