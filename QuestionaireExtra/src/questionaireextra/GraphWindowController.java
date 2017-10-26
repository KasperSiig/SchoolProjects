/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionaireextra;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Kasper Siig & Kenneth R. Pedersen
 */
public class GraphWindowController implements Initializable {

    //FXML Variables
    @FXML
    private AnchorPane anchor;
    @FXML
    private AnchorPane window;
    @FXML
    private ScrollPane sPane;

    //Integers
    private final int WINDOW_WIDTH = 1300;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    /**
     * Creates graph and sets data, along with the width and height of the window.
     * 
     * @param avgPointsList 
     */
    public void setGraph(List avgPointsList) {
        //Defines the width of the window
        int width = avgPointsList.size() * 30;
        
        //Creates the X and Y axis of the LineChart
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        //Sets upper bound to always be 1, and lower bound to always be -1.
        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(2);
        yAxis.setLowerBound(-2);
        
        //Sets label under LineChar
        xAxis.setLabel("Question Number");
        
        //Creates the chart
        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

        //Sets title of LineChart
        lineChart.setTitle("Average amount of points");
        
        //Defines the series of data
        XYChart.Series series = new XYChart.Series();
        series.setName("Points");
        
        //Sets maxWidth and preferred size of window
        window.setMaxWidth(WINDOW_WIDTH);
        window.setPrefSize(width, 450);

        //Sets maxWidth and preferred size of ScrollPane
        sPane.setMaxWidth(WINDOW_WIDTH);
        sPane.setPrefWidth(width);

        //Sets preferred width of LineChart
        lineChart.setPrefWidth(width);

        //Sets preferred width of AnchorPane
        anchor.setPrefWidth(width);
        
        //Populates the series with data
        for (int i = 0; i < avgPointsList.size(); i++) {
            //Creates the string to be shown on the X-axis
            String num = "Q" + (i + 1);
            
            //Sets the data on corresponding point
            series.getData().add(new XYChart.Data(num, avgPointsList.get(i)));

        }

        //Adds the data to the LineChart
        lineChart.getData().add(series);
        
        //Adds the LineChart to the AnchorPane
        anchor.getChildren().add(lineChart);

    }
}
