package Scene;

import controllers.InvestmentController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 *
 * @author Caio Skornicki
 */
public class DesignPerf {
    HBox Bottom;
    VBox BOTTOM2, BOTTOM3;
    GridPane TOP,MID;
    BorderPane layout;
    Scene ENTRANCE;
    Button backBTN, RESETBTN, RESETBTN2;
    Design MAIN;
    Text mainTXT, verb, gainTXT, lossTXT, perfTXT, TITLE;
    TextFlow Sentence;
    FontMeUp MYFONT;
    double performance;
    
    private InvestmentController INVESTCONTROL;
    private DesignPerfExtension EXTENSION;
    
    DesignPerf(Stage MAINWINDOW) throws IOException {
        EXTENSION = new DesignPerfExtension();
        INVESTCONTROL = new InvestmentController();
        /* Performance Management */
        performance = -10;
        String performanceString = Double.toString(performance);
        
        /* Text Management */
        MYFONT = new FontMeUp();
        
        perfTXT = new Text();
        perfTXT.setText(performanceString);
        perfTXT.setFont(MYFONT.getOswaldRegular());
        
        mainTXT = new Text();
        mainTXT.setText("Your portfolio "); 
        mainTXT.setFont(MYFONT.getOswaldRegular());
        
        verb = new Text();
        verb.setText(" is ");
        verb.setFont(MYFONT.getOswaldRegular());
        
        gainTXT = new Text();
        gainTXT.setText("gain");
        gainTXT.setFont(MYFONT.getOswaldRegular());
        gainTXT.setFill(Color.GREENYELLOW);
        
        lossTXT = new Text();
        lossTXT.setText("loss");
        lossTXT.setFont(MYFONT.getOswaldRegular());
        lossTXT.setFill(MYFONT.getTitleColor());
        
        TITLE = new Text();
        TITLE.setText("Portfolio Performance");
        TITLE.setFont(MYFONT.getOswaldBold());
        TITLE.setFill(MYFONT.getTitleColor());
        
        Sentence = new TextFlow();
        Sentence.setLayoutX(50);
        Sentence.setLayoutY(40);
        
        if(performance>=0){
            Sentence.getChildren().addAll(mainTXT, gainTXT, verb, perfTXT);
        }else{
            Sentence.getChildren().addAll(mainTXT, lossTXT, verb, perfTXT);
        }
 
        /* Button management  */
        RESETBTN2 = new Button();
        RESETBTN2.setText("RESET ALL PORTFOLIO");
        RESETBTN2.setFont(MYFONT.getOswaldButton());
        RESETBTN2.setOnAction((ActionEvent e) -> {
            try {
                INVESTCONTROL.deleteAll();
            } catch (IOException ex) {
                Logger.getLogger(DesignPerf.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        RESETBTN = new Button();
        RESETBTN.setText("RESET ALL PORTFOLIO");
        RESETBTN.setFont(MYFONT.getOswaldButton());
        RESETBTN.setOnAction((ActionEvent e) -> {
            MID.getChildren().clear();
            String MESSAGE = "This will reset all investments submitted and deleted. Are you sure?";
            EXTENSION.DesignPerfExtension(MID, MESSAGE, RESETBTN2);
        });
        
 
        backBTN = new Button("BACK");
        backBTN.setFont(MYFONT.getOswaldButton());
        backBTN.setOnAction((ActionEvent e) -> {
            MAIN = new Design(MAINWINDOW);
            MAINWINDOW.setScene(MAIN.getScreen());
            MAINWINDOW.setTitle("Stock Organizer Software");
                });
        
        /* Pane management */
        MID = new GridPane();
        MID.setHgap(40);
        MID.add(Sentence, 0, 0);
        MID.setAlignment(Pos.CENTER);     
        
        TOP = new GridPane();
        TOP.setHgap(37);
        TOP.setVgap(10);
        TOP.setAlignment(Pos.TOP_LEFT);
        TOP.add(TITLE, 1, 2);
        
        BOTTOM2 = new VBox(20);
        BOTTOM2.setAlignment(Pos.BOTTOM_RIGHT);
        BOTTOM2.getChildren().add(RESETBTN);
        BOTTOM2.setPadding(new Insets(10, 10, 10, 15));
        
        BOTTOM3 = new VBox(20);
        BOTTOM3.getChildren().add(backBTN);
        BOTTOM3.setAlignment(Pos.BOTTOM_LEFT);
        BOTTOM3.setPadding(new Insets(10, 10, 10, 15));
        
        Bottom = new HBox(410);
        Bottom.getChildren().add(BOTTOM3);
        Bottom.getChildren().add(BOTTOM2);
        Bottom.setPadding(new Insets(10, 20, 10, 15));
        
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


