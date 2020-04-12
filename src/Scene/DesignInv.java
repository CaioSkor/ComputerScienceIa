package Scene;

import controllers.GetTicker;
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
    private Text MISSINGSTOCKS;
    private final Text TITLE;
    private ComboBox STKCODE;
    private final Button BCKBUTTON;
    private final Button MORESTOCKS;
    private final Button DELSTOCKS;
    private Button DSADDBUTTON;
    private final Integer PIECES;
    private final Integer RESTE;
    private Integer INDEX,INDEXA,LASTBUTTON;
    private final Button[] STOCKBTN;
    private String DATA, CODENAME;
    private String [] NAMEINVEST;
    private Boolean BOOL, BOOL2;
    private final GridPane TOP;
    private final GridPane BOTTOM;
    private final HBox[] MIDDLE;    
    private final VBox MID;
    private final BorderPane LAYOUT;
    private final Scene ENTRANCE;

    private Design MAIN;
    private DesignAdd DSADD;
    private DesignAddExtension EXTENSION;
    private final FontMeUp MYFONT;
    private ToolsUse TOOLS;
    private final GetTicker NAMES;
    
    public DesignInv(Stage MAINWINDOW, Integer CHOICE) throws IOException {
        MYFONT = new FontMeUp();
        TOOLS = new ToolsUse();
        NAMES = new GetTicker();
        BOOL = true;
        LASTBUTTON = -1;
        
        MORESTOCKS = new Button();
        MORESTOCKS.setText("MORESTOCKS");
        MORESTOCKS.setFont(MYFONT.getOswaldButton());
        
        TITLE = new Text();
        if(CHOICE == 1){
            TITLE.setText("Investments");
        }else{
            TITLE.setText("Deleted investments");
        }
        TITLE.setFont(MYFONT.getOswaldBold());
        TITLE.setFill(MYFONT.getTitleColor());
        
        STKCODE = new ComboBox();   
        STKCODE.setEditable(true);
        STKCODE.getEditor().textProperty().addListener((obs, oldText, newText) -> {
            STKCODE.setValue(newText);
        });        
        // Fill the choicebox with items
        TOOLS.BoxFiller("data/investment.txt", STKCODE,null, CHOICE); 
        
        STKCODE.setOnAction(new EventHandler() {
            @Override
            public void handle(Event e) {
                DATA = (String) STKCODE.getValue(); 
                try {
                    DSADD = new DesignAdd(MAINWINDOW,BOOL,0,DATA, BOOL);
                    MAINWINDOW.setScene(DSADD.getScreen());                
                    MAINWINDOW.setTitle("Investment: "+DATA); 
                } catch (IOException ex) {
                    System.out.println("PROBLEMS");
                }
                   
            }    
        });
        
        BCKBUTTON = new Button();
        BCKBUTTON.setText("BACK");
        BCKBUTTON.setFont(MYFONT.getOswaldButton());
        BCKBUTTON.setOnAction((ActionEvent e) -> {
            if(CHOICE == 1){
                 MAIN = new Design(MAINWINDOW);
                 MAINWINDOW.setScene(MAIN.getScreen());
                 MAINWINDOW.setTitle("Stock Organizer Software");
             }else{
                 try {
                     TOOLS.BackDeletedWindow(MAINWINDOW);
                 } catch (IOException ex) {
                     Logger.getLogger(DesignInv.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
        });
        
        DELSTOCKS = new Button();
        DELSTOCKS.setText("DELETED STOCKS");
        DELSTOCKS.setFont(MYFONT.getOswaldButton());
        DELSTOCKS.setOnAction((ActionEvent e) -> {
             try {
                 TOOLS.DeletedWindow(MAINWINDOW);
             } catch (IOException ex) {
                 Logger.getLogger(DesignInv.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
        
        STOCKBTN = new Button[TOOLS.FileMeasure("data/investment.txt", CHOICE)];
        
        PIECES = TOOLS.FileMeasure("data/investment.txt", CHOICE)/5;
        RESTE = TOOLS.FileMeasure("data/investment.txt", CHOICE)%5;
       
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
            PopulateMe(MAINWINDOW,5, CHOICE);
            MIDDLE[INDEXA].setAlignment(Pos.CENTER);   
        }
        if (RESTE > 0){
            PopulateMe(MAINWINDOW,RESTE, CHOICE);
            MIDDLE[INDEXA].setPadding(new Insets(0,0,0,55));            
        }
        
        MID = new VBox(10);
        MID.setAlignment(Pos.CENTER);
        if(RESTE == 0 && PIECES == 0){
            MISSINGSTOCKS = new Text();
            if(CHOICE == 1){
                BOOL = false;
                //DSADD = new DesignAdd(MAINWINDOW,BOOL,0,DATA, BOOL);
                
                MISSINGSTOCKS.setText("No investment submitted. Click on button to add investment");
                MISSINGSTOCKS.setFont(MYFONT.getOswaldRegular());
                MISSINGSTOCKS.setFill(MYFONT.getTitleColor());
                
                DSADDBUTTON = new Button();
                DSADDBUTTON = new Button();
                DSADDBUTTON.setText("Add investment");
                DSADDBUTTON.setFont(MYFONT.getOswaldButton());
                DSADDBUTTON.setOnAction((ActionEvent e) -> {
                    try {
                        DSADD = new DesignAdd(MAINWINDOW,BOOL,0,"NOINV", BOOL);
                    } catch (IOException ex) {
                        Logger.getLogger(DesignInv.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    MAINWINDOW.setScene(DSADD.getScreen());
                    MAINWINDOW.setTitle("Add investment");
                });
                
                MID.getChildren().addAll(MISSINGSTOCKS, DSADDBUTTON);
            }
        }else{
            for(INDEX=0; INDEX< PIECES; INDEX++){                     
                MID.getChildren().add(MIDDLE[INDEX]); 
            } 
            if (RESTE > 0){
            MID.getChildren().add(MIDDLE[PIECES]);        
            }    
        }

        BOTTOM = new GridPane();
        BOTTOM.setVgap(15);
        BOTTOM.setHgap(15);
        BOTTOM.setAlignment(Pos.TOP_LEFT);
        BOTTOM.add(BCKBUTTON, 1, 2);
        if(CHOICE == 1){
            if(TOOLS.FileMeasure("data/investment.txt", 0) != 0){
                BOTTOM.add(DELSTOCKS, 33, 2);
            }else{
                //BOTTOM.add(MORESTOCKS, 35, 2);
            }
        }else{
            MORESTOCKS.setText("More Deleted Stocks");
            BOTTOM.add(MORESTOCKS, 33, 2);
        }
        

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
        BOOL2 = false;
        if(!BOOLE){
            BOOL = true;
            DSADD = new DesignAdd(MAINWINDOW,BOOL,0,CODENAME, BOOL2);
            MAINWINDOW.setScene(DSADD.getScreen());                
        }else{
            BOOL = false;
            DSADD = new DesignAdd(MAINWINDOW,BOOL,0,CODENAME, BOOL2);
            MAINWINDOW.setScene(DSADD.getScreen());
        }
    }
    
    public void PopulateMe(Stage MAINWINDOW, Integer RESTE, Integer CHOICE) throws IOException{
        MIDDLE[INDEXA] = new HBox(10);
        for(INDEX=0; INDEX< RESTE; INDEX++){         
            STOCKBTN[INDEX+(5*INDEXA)] = new Button();
            STOCKBTN[INDEX+(5*INDEXA)].setPrefWidth(110);
            STOCKBTN[INDEX+(5*INDEXA)].setText(NAMES.GetTicker("data/investment.txt",CHOICE)[INDEX+(5*INDEXA)]); 
            STOCKBTN[INDEX+(5*INDEXA)].setOnAction((ActionEvent e) -> {
                final Integer BUTTONID = INDEX;
                System.out.println("Button pressed " + ((Button) e.getSource()).getText());
                LASTBUTTON = BUTTONID;                
                try {
                    DSADD = new DesignAdd(MAINWINDOW,BOOL,INDEX+(5*INDEXA),((Button) e.getSource()).getText(), BOOL);
                } catch (IOException ex) {
                    System.out.println("PROBLEMS");
                }
                MAINWINDOW.setScene(DSADD.getScreen());                
                MAINWINDOW.setTitle("Investment: "+((Button) e.getSource()).getText());
            });                
            MIDDLE[INDEXA].getChildren().add(STOCKBTN[INDEX+(5*INDEXA)]);     
        }
    }
    
    public Scene getScreen(){
        return ENTRANCE;
    }
}
