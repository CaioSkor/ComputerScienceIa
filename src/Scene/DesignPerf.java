package Scene;

import controllers.FontDesign;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 *
 * @author Caio Skornicki
 */
public class DesignPerf {
    HBox Bottom;
    GridPane TOP,MID;
    BorderPane layout;
    Scene ENTRANCE;
    Button backBTN;
    Design MAIN;
    Text mainTXT, verb, gainTXT, lossTXT, perfTXT, TITLE;
    TextFlow Sentence;
    FontDesign FontCust;
    FontMeUp MYFONT;
    double performance;
    
    DesignPerf(Stage MAINWINDOW) {
        
        /* Performance Management */
        performance = -10;
        String performanceString = Double.toString(performance);
        
        /* Text Management */
        MYFONT = new FontMeUp();
        
        perfTXT = new Text();
        perfTXT.setText(performanceString);
        perfTXT.setFont(MYFONT.OSWALDREGULAR);
        
        mainTXT = new Text();
        mainTXT.setText("Your portfolio "); 
        mainTXT.setFont(MYFONT.OSWALDREGULAR);
        
        verb = new Text();
        verb.setText(" is ");
        verb.setFont(MYFONT.OSWALDREGULAR);
        
        gainTXT = new Text();
        gainTXT.setText("gain");
        gainTXT.setFont(MYFONT.OSWALDREGULAR);
        gainTXT.setFill(Color.GREENYELLOW);
        
        lossTXT = new Text();
        lossTXT.setText("loss");
        lossTXT.setFont(MYFONT.OSWALDREGULAR);
        lossTXT.setFill(MYFONT.TITLECOLOR);
        
        TITLE = new Text();
        TITLE.setText("Portfolio Performance");
        TITLE.setFont(MYFONT.OSWALDBOLD);
        TITLE.setFill(MYFONT.TITLECOLOR);
        
        Sentence = new TextFlow();
        Sentence.setLayoutX(50);
        Sentence.setLayoutY(40);
        
        if(performance>=0){
            Sentence.getChildren().addAll(mainTXT, gainTXT, verb, perfTXT);
        }else{
            Sentence.getChildren().addAll(mainTXT, lossTXT, verb, perfTXT);
        }
 
        /* Button management  */
        backBTN = new Button("BACK");
        backBTN.setFont(MYFONT.OSWALDBUTTON);
        backBTN.setOnAction((ActionEvent e) -> {
            MAIN = new Design(MAINWINDOW);
            MAINWINDOW.setScene(MAIN.getScreen());
            MAINWINDOW.setTitle("Stock Organizer Software");
                });
        
        /* Pane management */
        MID = new GridPane();
        MID.add(Sentence, 0, 0);
        MID.setAlignment(Pos.CENTER);     
        
        TOP = new GridPane();
        TOP.setHgap(37);
        TOP.setVgap(10);
        TOP.setAlignment(Pos.TOP_LEFT);
        TOP.add(TITLE, 1, 2);
        
        Bottom = new HBox(30);
        Bottom.getChildren().add(backBTN);
        Bottom.setAlignment(Pos.BOTTOM_LEFT);
        Bottom.setPadding(new Insets(10, 10, 10, 15));
        
        layout = new BorderPane();
        layout.setTop(TOP);
        layout.setCenter(MID);
        layout.setBottom(Bottom);
        
        ENTRANCE = new Scene(layout, 700, 500);
        ENTRANCE.getStylesheets().add("CAIOSTYLE.css");        
    }


    public Scene getScreen(){
        return ENTRANCE;
    }
}


