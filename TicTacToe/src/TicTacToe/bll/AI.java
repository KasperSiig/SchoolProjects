/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Kasper Siig
 */
public class AI
{

    private final int[] preferredMoves =
    {
        5, 1, 7, 3, 9, 4, 2, 8, 6
    };
    private List<String> btnList;

    public int bestMove(GridPane gridPane)
    {
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

        if (btn5txt.isEmpty())
        {
            return 4;
        }

        getRandPos(btn1txt, btn3txt, btn7txt, btn9txt,
                   gridPane, 0, 2, 6, 8, btnList);
        getRandPos(btn2txt, btn4txt, btn6txt, btn8txt,
                   gridPane, 1, 3, 5, 7, btnList);

        for (int i = 0; i < preferredMoves.length; i++)
        {
            int index = preferredMoves[i] - 1;
            if (btnList.get(index).isEmpty())
            {
                return index;
            }
        }

        return -1;
    }

    private int getRandPos(String btn1, String btn2, String btn3, String btn4,
                           GridPane gridPane, int num1,
                           int num2, int num3, int num4, List btnList)
    {
        if (btn1.isEmpty() || btn2.isEmpty()
            || btn3.isEmpty() || btn4.isEmpty())
        {
            List<Integer> rand = new ArrayList();
            rand.add(num1);
            rand.add(num2);
            rand.add(num3);
            rand.add(num4);
            int rnd = new Random().nextInt(rand.size());
            if (btnList.get(rand.get(rnd)) == "")
            {
                return rand.get(rnd);
            }
            else
            {
                bestMove(gridPane);
                rand.remove(rnd);
            }
        }
        return 0;
    }

}
