package Scene;

import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class DesignAddExtension {
    Integer INDEX, INDEXA, COUNT;
    String JOINER;
    String[] MISSING, MSGRESULTS;
    Integer[] POS;
    DesignAdd DSADD;
    DesignInv DSINV;
    Button BACKBTN;
    ToggleGroup GROUP;
    RadioButton BTN1, BTN2;
    Text MESSAGE, DEMAND;
    
    FontMeUp MYFONT;
    
    public GridPane DesignAddExtension(GridPane MID, RadioButton BTN1, RadioButton BTN2, ToggleGroup GROUP) {
        MYFONT = new FontMeUp();
                
        MID.setHgap(15);
        MID.setVgap(10);

        MID.setAlignment(Pos.CENTER);
        
        MID.add(BTN1, 0, 0);
        MID.add(BTN2, 0, 1);
        return MID;
    }
    
    public GridPane DesignAddExtension(GridPane MID, String[] MSG) {
        MYFONT = new FontMeUp();
        
        INDEXA = 0;
        for(INDEX=0;INDEX<5;INDEX++){
            if(MSG[INDEX] != null && !MSG[INDEX].trim().isEmpty()){
               INDEXA++;
            }
        MISSING = new String[INDEXA];    
        }
      
        COUNT = 0;
        for(INDEX=0;INDEX < MSG.length; INDEX++){
            if(MSG[INDEX] != null && !MSG[INDEX].trim().isEmpty()){
                MISSING[COUNT] = MSG[INDEX];
                COUNT++;      
            }
        }   
        DEMAND = new Text();
        DEMAND.setText("Please input the following missing field(s):");
        DEMAND.setFont(MYFONT.OSWALDREGULAR);
        DEMAND.setTextAlignment(TextAlignment.CENTER);
        
        MESSAGE = new Text();
        String JOINER = String.join(", ", MISSING);
        MESSAGE.setText(JOINER);
        MESSAGE.setFont(MYFONT.OSWALDREGULAR);
        MESSAGE.setFill(MYFONT.TITLECOLOR);
        MESSAGE.setTextAlignment(TextAlignment.CENTER);
        
        MID.setHgap(15);
        MID.setVgap(10);
        MID.setAlignment(Pos.CENTER);
        MID.add(DEMAND, 0, 0);
        MID.add(MESSAGE, 0, 1);
        return MID;
    }
    
    public GridPane DesignAddExtension(Stage MAINWINDOW, GridPane MID, String INVESTNAME) throws IOException{
        MYFONT = new FontMeUp();
        DSINV = new DesignInv(MAINWINDOW);
        
        BACKBTN = new Button();
        BACKBTN.setOnAction(e -> {
            MAINWINDOW.setScene(DSINV.getScreen());
        });
        
        MESSAGE = new Text();
        MESSAGE.setText("Investment " + INVESTNAME + " has been deleted." );
        MESSAGE.setFill(MYFONT.TITLECOLOR);
        MESSAGE.setFont(MYFONT.OSWALDREGULAR);
        
        
        MID.setHgap(15);
        MID.setVgap(10);
        MID.add(MESSAGE, 0, 0);
        MID.add(BACKBTN, 0, 1);
        MID.setAlignment(Pos.CENTER);
        return MID;
    }
    
}
