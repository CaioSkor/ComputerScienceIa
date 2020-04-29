/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import com.intrinio.invoker.ApiException;
import controllers.PerformanceController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author caio
 */
public class DesignGraph {
    Text TITLE;
    HBox BOTTOM;
    GridPane MID, TOP;
    Button BACKBTN;
    ComboBox FREQUENCY;
    CategoryAxis XAXIS;
    NumberAxis YAXIS;
    LineChart<String, Number> LINECHART;
    XYChart.Series SERIES;
    String[][] HISTORICAL;
    String[] DATE;
    BorderPane LAYOUT;
    Scene ENTRANCE;
    
    DesignAdd DSADD;
    FontMeUp MYFONT;
    PerformanceController PERF;
    
    public DesignGraph(Stage MAINWINDOW,Boolean BOOL,Integer POS,String INVESTNAME, Boolean BOOL2) throws IOException, ApiException{
        MYFONT = new FontMeUp();
        PERF = new PerformanceController();
        
        MID = new GridPane();
        MID.setHgap(15);
        MID.setVgap(10);
        MID.setAlignment(Pos.CENTER);
        MID.setMinSize(200, 200);
        MID.setPadding(new Insets(10, 10, 10, 10));
        
        TITLE = new Text();
        TITLE.setText("Stock Graph");
        TITLE.setFont(MYFONT.getOswaldBold());
        TITLE.setFill(MYFONT.getTitleColor());
        
        ObservableList<String> OPTIONS = FXCollections.observableArrayList("daily", "weekly", "monthly", "yearly");
        FREQUENCY = new ComboBox();
        FREQUENCY.setItems(OPTIONS);
        FREQUENCY.setOnAction(event ->{
            
            MID.getChildren().clear();
            try {
                SERIES = new XYChart.Series();
                
                HISTORICAL = new String[9998][2];
                String freq = (String) FREQUENCY.getValue();
                HISTORICAL = PERF.getHistPrices(INVESTNAME, freq);
                System.out.println(PERF.getHistPrices(INVESTNAME, freq)[0][0]);
                
                // Setting upper and lower bounds
                Integer i = 0;
                Double UPPER = Double.valueOf(HISTORICAL[0][1]);
                Double LOWER = Double.valueOf(HISTORICAL[0][1]);
                while(i < 90){
                    if(UPPER < Double.valueOf(HISTORICAL[i][1])){
                        UPPER = Double.valueOf(HISTORICAL[i][1]);
                    }
                    else if(LOWER > Double.valueOf(HISTORICAL[i][1])){
                        LOWER = Double.valueOf(HISTORICAL[i][1]);
                    }
                    i++;
                }
                UPPER = UPPER + (UPPER*10/100);
                LOWER = LOWER - (LOWER*15/100);
                
                Integer INDEX = 0;
                while(INDEX < 90){
                    SERIES.getData().add(new XYChart.Data(HISTORICAL[INDEX][0],Double.parseDouble(HISTORICAL[INDEX][1])));
                    INDEX++;
                }
                XAXIS = new CategoryAxis();
                YAXIS = new NumberAxis(LOWER, UPPER, 5);

                YAXIS.setUpperBound(UPPER);
                YAXIS.setLowerBound(LOWER);
                
                LINECHART = new LineChart<String, Number>(XAXIS, YAXIS);
                LINECHART.getData().add(SERIES);
                LINECHART.setCreateSymbols(false);
                MID.add(LINECHART, 0, 0);   
            } catch (IOException ex) {
                Logger.getLogger(DesignGraph.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        BACKBTN = new Button();
        BACKBTN.setText("BACK");
        BACKBTN.setOnAction(event ->{
            try {
                DSADD = new DesignAdd(MAINWINDOW,BOOL,POS,INVESTNAME,BOOL2);
            } catch (IOException ex) {
                Logger.getLogger(DesignGraph.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ApiException ex) {
                Logger.getLogger(DesignGraph.class.getName()).log(Level.SEVERE, null, ex);
            }
            MAINWINDOW.setScene(DSADD.getScreen());
            MAINWINDOW.setTitle("Investment: " + INVESTNAME);
        });
        MID.add(FREQUENCY, 0, 0);
        
        TOP = new GridPane();
        TOP.setHgap(37);
        TOP.setVgap(10);
        TOP.setAlignment(Pos.TOP_LEFT);
        TOP.add(TITLE, 1, 2);
        
        BOTTOM = new HBox();
        BOTTOM.getChildren().add(BACKBTN);
        BOTTOM.setAlignment(Pos.BOTTOM_LEFT);
        BOTTOM.setPadding(new Insets(10, 10, 10, 15));
        
        LAYOUT = new BorderPane();
        LAYOUT.setTop(TOP);
        LAYOUT.setCenter(MID);
        LAYOUT.setBottom(BOTTOM);
        
        ENTRANCE = new Scene(LAYOUT, 700, 500);
        ENTRANCE.getStylesheets().add("CAIOSTYLE.css"); 
    }
    
    public Scene getScreen(){
        return ENTRANCE;
    }
}
