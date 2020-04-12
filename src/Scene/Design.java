package Scene;
     
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class Design {
    private final Text TITLE;
    private final Text DATEDISPLAY;
    private final Button BTN1;
    private final Button BTN2;
    private final Button BTN3;
    private final Date DATENOW;   
    private final DateFormat DF; 
    private Boolean BOOL;
    private final HBox BOTTOM;
    private final VBox MID;
    private final GridPane TOP;
    private final BorderPane LAYOUT;
    private final Scene ENTRANCE;
    
    private final FontMeUp MYFONT;
    private DesignInv DSINV;
    private DesignAdd DSADD;
    private DesignPerf PORTPERF;
    
    public Design (Stage MAINWINDOW){
        /* Text management */
        MYFONT = new FontMeUp();
        BOOL = false;
        
        TITLE = new Text();
        TITLE.setText("Stock Organizer Software");
        TITLE.setFont(MYFONT.getOswaldBold());
        TITLE.setFill(MYFONT.getTitleColor());
        
         /* Button Management */
        BTN1 = new Button();
        BTN1.setText("Investments");
        BTN1.setFont(MYFONT.getOswaldButton());
        BTN1.setMinWidth(200);
        BTN1.setOnAction((ActionEvent e) -> {
            Integer CHOICE = 1;
            try {
                DSINV = new DesignInv(MAINWINDOW,CHOICE);
            } catch (IOException ex) {
                System.out.println("PROBLEMS");
            }
            MAINWINDOW.setScene(DSINV.getScreen());
            MAINWINDOW.setTitle("Investments");
        });
        
        BTN2 = new Button();
        BTN2.setText("Portfolio performance");
        BTN2.setFont(MYFONT.getOswaldButton());
        BTN2.setMinWidth(200);
        BTN2.setOnAction((ActionEvent e) -> {
            PORTPERF = new DesignPerf(MAINWINDOW);
            MAINWINDOW.setScene(PORTPERF.getScreen());
            MAINWINDOW.setTitle("Portfolio performance");
        });
        
        BTN3 = new Button();
        BTN3.setText("Add new investment");
        BTN3.setFont(MYFONT.getOswaldButton());
        BTN3.setMinWidth(200);
        BTN3.setOnAction((ActionEvent e) -> {
            try {
                DSADD = new DesignAdd(MAINWINDOW,BOOL,-1,"", BOOL);
            } catch (IOException ex) {
                System.out.println("PROBLEMS");
            }
            MAINWINDOW.setScene(DSADD.getScreen());
            MAINWINDOW.setTitle("Add new investment");
        });
        
        /* Pane management */
        TOP = new GridPane();
        TOP.setHgap(37);
        TOP.setVgap(10);
        TOP.add(TITLE, 5, 12);
        TOP.setPadding(new Insets(10, 10, 10, 33));
        
        MID = new VBox(50);
        MID.getChildren().addAll(BTN1,BTN2,BTN3);
        MID.setAlignment(Pos.CENTER);
        
        BOTTOM = new HBox(50);
        BOTTOM.setAlignment(Pos.BOTTOM_LEFT);
        DATENOW = new Date();
        DF = new SimpleDateFormat("dd.MM");
        DATEDISPLAY = new Text();
        DATEDISPLAY.setText(DF.format(DATENOW));
        DATEDISPLAY.setFont(MYFONT.getOswaldRegular());
        BOTTOM.getChildren().add(DATEDISPLAY);
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
    

        

                
    