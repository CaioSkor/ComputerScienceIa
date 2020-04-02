package Scene;

import controllers.GetNames;
import controllers.ToolsUse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 *
 * @author Caio Skornicki
 */
public class DesignInv {
    Text TITLE;
    ComboBox STKCODE;
    Button BCKBUTTON,MORESTOCKS;
    Integer PIECES,RESTE,INDEX,INDEXA,LASTBUTTON;
    Button[] STOCKBTN;
    String DATA, CODENAME;
    String [] NAMEINVEST;
    Boolean BOOL;
    GridPane TOP;
    HBox[] MIDDLE;    
    VBox MID;
    HBox BOTTOM;
    BorderPane LAYOUT;
    Scene ENTRANCE;

    Design MAIN;
    DesignAdd INVEST;
    FontMeUp MYFONT;
    ToolsUse NBRSTOCK;
    GetNames GAMESET;
    
    DesignInv(Stage MAINWINDOW) throws IOException {
        MYFONT = new FontMeUp();
        NBRSTOCK = new ToolsUse();
        GAMESET = new GetNames();
        BOOL = true;
        LASTBUTTON = -1;
        
        TITLE = new Text();
        TITLE.setText("Investments");
        TITLE.setFont(MYFONT.OSWALDBOLD);
        TITLE.setFill(MYFONT.TITLECOLOR);
        
        STKCODE = new ComboBox();   
        STKCODE.setEditable(true);
        STKCODE.getEditor().textProperty().addListener((obs, oldText, newText) -> {
            STKCODE.setValue(newText);
        });        
        // Fill the choicebox with items
        NBRSTOCK.BoxFiller("data/investment.txt", STKCODE,null); 
        
        STKCODE.setOnAction(new EventHandler() {
            @Override
            public void handle(Event e) {
                DATA = (String) STKCODE.getValue(); 
                try {
                    INVEST = new DesignAdd(MAINWINDOW,BOOL,0,DATA);
                    MAINWINDOW.setScene(INVEST.getScreen());                
                    MAINWINDOW.setTitle("Investment: "+DATA); 
                } catch (IOException ex) {
                    System.out.println("PROBLEMS");
                }
                   
            }    
        });

        BCKBUTTON = new Button();
        BCKBUTTON.setText("BACK");
        BCKBUTTON.setFont(MYFONT.OSWALDBUTTON);
        BCKBUTTON.setOnAction((ActionEvent e) -> {
            MAIN = new Design(MAINWINDOW);
            MAINWINDOW.setScene(MAIN.getScreen());
            MAINWINDOW.setTitle("Stock Organizer Software");
        });
        
        MORESTOCKS = new Button();
        MORESTOCKS.setText("MORE STOCKS");
        MORESTOCKS.setFont(MYFONT.OSWALDBUTTON);
        MORESTOCKS.setOnAction((ActionEvent e) -> {

        });
        
        STOCKBTN = new Button[NBRSTOCK.FileMeasure("data/investment.txt")];
        
        PIECES = NBRSTOCK.FileMeasure("data/investment.txt")/5;
        RESTE = NBRSTOCK.FileMeasure("data/investment.txt")%5;
       
        TOP = new GridPane();
        TOP.setHgap(37);
        TOP.setVgap(10);
        TOP.setAlignment(Pos.TOP_LEFT);
        TOP.add(TITLE, 1, 2);
        TOP.add(STKCODE,5,2);

        if (RESTE > 0){
            MIDDLE = new HBox[PIECES+1];
        }else{
            MIDDLE = new HBox[PIECES];        
        }
        for (INDEXA=0; INDEXA< PIECES; INDEXA++){ 
            PopulateMe(MAINWINDOW,5);
            MIDDLE[INDEXA].setAlignment(Pos.CENTER);   
        }
        if (RESTE > 0){
            PopulateMe(MAINWINDOW,RESTE);
            MIDDLE[INDEXA].setPadding(new Insets(0,0,0,55));            
        }
        
        MID = new VBox(10);
        MID.setAlignment(Pos.CENTER);
        for(INDEX=0; INDEX< PIECES; INDEX++){                     
            MID.getChildren().add(MIDDLE[INDEX]); 
        } 
        if (RESTE > 0){
            MID.getChildren().add(MIDDLE[PIECES]);        
        }        

        BOTTOM = new HBox(450);
        BOTTOM.setAlignment(Pos.BASELINE_CENTER);
        BOTTOM.getChildren().addAll(BCKBUTTON,MORESTOCKS);
        BOTTOM.setPadding(new Insets(10, 20, 10, 15));
        
        LAYOUT = new BorderPane();
        LAYOUT.setTop(TOP);
        LAYOUT.setCenter(MID);
        LAYOUT.setBottom(BOTTOM);
        
        ENTRANCE = new Scene(LAYOUT, 700, 500);
        ENTRANCE.getStylesheets().add("CAIOSTYLE.css");        
    }
    
    public void codeName(String code){
        CODENAME = code;
    }
        
    public void continueInv(Stage MAINWINDOW, Boolean BOOLE) throws IOException{
        if(!BOOLE){
            INVEST = new DesignAdd(MAINWINDOW,BOOL,0,CODENAME);
            MAINWINDOW.setScene(INVEST.getScreen());                
        }else{
            BOOL = false;
            INVEST = new DesignAdd(MAINWINDOW,BOOL,0,CODENAME);
            MAINWINDOW.setScene(INVEST.getScreen());
        }
        
    }
    
    public void PopulateMe(Stage MAINWINDOW, Integer RESTE) throws IOException{
        MIDDLE[INDEXA] = new HBox(10);
        for(INDEX=0; INDEX< RESTE; INDEX++){         
            STOCKBTN[INDEX+(5*INDEXA)] = new Button();
            STOCKBTN[INDEX+(5*INDEXA)].setPrefWidth(110);
            STOCKBTN[INDEX+(5*INDEXA)].setText(GAMESET.GetNames("data/investment.txt")[INDEX+(5*INDEXA)]); 
            STOCKBTN[INDEX+(5*INDEXA)].setOnAction((ActionEvent e) -> {
                final Integer BUTTONID = INDEX;
                System.out.println("Button pressed " + ((Button) e.getSource()).getText());
                LASTBUTTON = BUTTONID;                
                try {
                    INVEST = new DesignAdd(MAINWINDOW,BOOL,INDEX+(5*INDEXA),((Button) e.getSource()).getText());
                } catch (IOException ex) {
                    System.out.println("PROBLEMS");
                }
                MAINWINDOW.setScene(INVEST.getScreen());                
                MAINWINDOW.setTitle("Investment: "+((Button) e.getSource()).getText());
            });                
            MIDDLE[INDEXA].getChildren().add(STOCKBTN[INDEX+(5*INDEXA)]);     
        }
    }
    
    
    public Scene getScreen(){
        return ENTRANCE;
    }
}
