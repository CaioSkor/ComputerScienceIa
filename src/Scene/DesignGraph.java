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
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author caio
 */
public class DesignGraph {
    Text TITLE, CHOICE;
    HBox BOTTOM, MID;
    GridPane MIDCHOICE, MIDGRAPH, TOP;
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
        
        MIDCHOICE = new GridPane();
        MIDCHOICE.setHgap(15);
        MIDCHOICE.setVgap(10);
        MIDCHOICE.setAlignment(Pos.CENTER);
        MIDCHOICE.setMinSize(200, 200);
        MIDCHOICE.setPadding(new Insets(10, 10, 10, 10));
        
        MIDGRAPH = new GridPane();
        MIDGRAPH.setHgap(15);
        MIDGRAPH.setVgap(10);
        MIDGRAPH.setAlignment(Pos.CENTER);
        MIDGRAPH.setMinSize(200, 200);
        MIDGRAPH.setPadding(new Insets(10, 10, 10, 10));
        
        CHOICE = new Text();
        CHOICE.setText("Choose graph frequency");
        CHOICE.setFont(MYFONT.getOswaldRegular());
        CHOICE.setFill(Color.GRAY);
        
        MIDGRAPH.add(CHOICE, 0, 0);
        
        TITLE = new Text();
        TITLE.setText("Stock Graph");
        TITLE.setFont(MYFONT.getOswaldBold());
        TITLE.setFill(MYFONT.getTitleColor());
        
        ObservableList<String> OPTIONS = FXCollections.observableArrayList("daily", "weekly", "monthly", "yearly");
        FREQUENCY = new ComboBox();
        FREQUENCY.setItems(OPTIONS);
        FREQUENCY.setOnAction(event ->{
            
            MIDGRAPH.getChildren().clear();
            try {
                SERIES = new XYChart.Series();
                SERIES.setName("line");
                
               // HISTORICAL = new String[9998][2];
                String freq = (String) FREQUENCY.getValue();
                    HISTORICAL = PERF.getHistPrices(INVESTNAME, freq);
                System.out.println(HISTORICAL[0][1]);
                
                // Setting upper and lower bounds
                Integer i = 0;
                Double UPPER = Double.valueOf(HISTORICAL[0][1]);
                Double LOWER = Double.valueOf(HISTORICAL[0][1]);
                while(i < HISTORICAL.length){
                    // Find the maximum vlaue
                    if(UPPER < Double.valueOf(HISTORICAL[i][1])){
                        UPPER = Double.valueOf(HISTORICAL[i][1]);
                    }
                    // Find the minimum value
                    else if(LOWER > Double.valueOf(HISTORICAL[i][1])){
                        LOWER = Double.valueOf(HISTORICAL[i][1]);
                    }
                    i++;
                }
                // Set maximum and minimum on graph
                UPPER = UPPER + (UPPER*10/100);
                LOWER = LOWER - (LOWER*15/100);
                
                Integer INDEX = 0;
                while(INDEX < HISTORICAL.length){
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
                LINECHART.setLegendVisible(false);
                
                Node line = SERIES.getNode().lookup(".SERIES-SERIES-area-line;");
                Integer LENGTH = HISTORICAL.length -1;
                if((Double.valueOf(HISTORICAL[0][1]) - Double.valueOf(HISTORICAL[LENGTH][1])) > 0){
                    SERIES.getNode().setStyle("-fx-stroke: #cc0000ff;");
                }else{
                    SERIES.getNode().setStyle("-fx-stroke: #7fff00;");
                }
                MIDGRAPH.add(LINECHART, 0, 0);   
            } catch (IOException ex) {
                Logger.getLogger(DesignGraph.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        MIDCHOICE.add(FREQUENCY, 0, 0);
        
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
        
        TOP = new GridPane();
        TOP.setHgap(37);
        TOP.setVgap(10);
        TOP.setAlignment(Pos.TOP_LEFT);
        TOP.add(TITLE, 1, 2);
        
        BOTTOM = new HBox();
        BOTTOM.getChildren().add(BACKBTN);
        BOTTOM.setAlignment(Pos.BOTTOM_LEFT);
        BOTTOM.setPadding(new Insets(10, 10, 10, 15));
        
        MID = new HBox();
        MID.getChildren().add(MIDCHOICE);
        MID.getChildren().add(MIDGRAPH);
        MID.setAlignment(Pos.BOTTOM_LEFT);
        MID.setPadding(new Insets(10, 10, 10, 15));
        
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
