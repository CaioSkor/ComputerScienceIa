package Scene;

import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class DesignAddExtension {
    private Integer INDEX, INDEXA, COUNT;
    private String JOINER;
    private String[] MISSING, MSGRESULTS;
    private Integer[] POS;
    private DesignAdd DSADD;
    private DesignInv DSINV;
    private Button BACKBTN;
    private ToggleGroup GROUP;
    private RadioButton BTN1, BTN2;
    private Text MESSAGE, MESSAGE2, DEMAND, DEMAND2;
    private Scene ENTRANCE;
    
    private FontMeUp MYFONT;
    
    public GridPane DesignAddExtension(GridPane MID, RadioButton BTN1, RadioButton BTN2, ToggleGroup GROUP) {
        MYFONT = new FontMeUp();
                
        MID.setHgap(15);
        MID.setVgap(10);

        MID.setAlignment(Pos.CENTER);
        
        MID.add(BTN1, 0, 0);
        MID.add(BTN2, 0, 1);
        return MID;
    }
    
    public GridPane DesignAddExtension(GridPane MID, String[] MSG, Boolean CASE) {
        MYFONT = new FontMeUp();
        
        MESSAGE = new Text();
        MESSAGE.setFont(MYFONT.getOswaldRegular());
        MESSAGE.setFill(MYFONT.getTitleColor());
        MESSAGE.setTextAlignment(TextAlignment.CENTER);
        
        DEMAND = new Text();
        DEMAND.setFont(MYFONT.getOswaldRegular());
        DEMAND.setTextAlignment(TextAlignment.CENTER);
        
        if(!CASE){
            String JOINER = String.join(" and ", MSG);
            
            DEMAND.setText("Please input the following field(s) as integers");
            
            if(MSG[1] != null && !MSG[1].trim().isEmpty()){
                MESSAGE.setText(JOINER);
            }else{
                MESSAGE.setText(MSG[0]);
            }
            
            
        }else{
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
            DEMAND.setText("Please input the following missing field(s):");
            
            String JOINER = String.join(", ", MISSING);
            MESSAGE.setText(JOINER);
        }
        MID.setHgap(15);
        MID.setVgap(10);
        MID.setAlignment(Pos.CENTER);
        MID.add(DEMAND, 0, 0);
        MID.add(MESSAGE, 0, 1);
        return MID;
    }
    
    public GridPane DesignAddExtension(GridPane MID, String[] MSG, String[] FORMATMSG){
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
        
        MESSAGE = new Text();
        MESSAGE.setFont(MYFONT.getOswaldRegular());
        MESSAGE.setFill(MYFONT.getTitleColor());
        MESSAGE.setTextAlignment(TextAlignment.CENTER);
        
        String JOINER = String.join(" and ", FORMATMSG);
        COUNT = 0;
        for(INDEX=0;INDEX<2;INDEX++){
            if(FORMATMSG[INDEX] != null && !FORMATMSG[INDEX].trim().isEmpty()){
                COUNT++;    
            }
        }
        if(COUNT==1){
           if(FORMATMSG[0] != null && !FORMATMSG[0].trim().isEmpty()){
           MESSAGE.setText(FORMATMSG[0]);
           }else{
           MESSAGE.setText(FORMATMSG[1]);
           }
        }else{
           MESSAGE.setText(JOINER);
        }
        
        /* if(FORMATMSG[1] != null && !FORMATMSG[1].trim().isEmpty()){
            MESSAGE.setText(JOINER);
        }else{
            MESSAGE.setText(FORMATMSG[0]);
        }*/
        
        DEMAND = new Text();
        DEMAND.setFont(MYFONT.getOswaldRegular());
        DEMAND.setTextAlignment(TextAlignment.CENTER);
        DEMAND.setText("Please input the following field(s) as integers:");
        
        MESSAGE2 = new Text();
        MESSAGE2.setFont(MYFONT.getOswaldRegular());
        MESSAGE2.setFill(MYFONT.getTitleColor());
        MESSAGE2.setTextAlignment(TextAlignment.CENTER);
        String JOINER2 = String.join(", ", MISSING);
        MESSAGE2.setText(JOINER2);
        
        DEMAND2 = new Text();
        DEMAND2.setFont(MYFONT.getOswaldRegular());
        DEMAND2.setTextAlignment(TextAlignment.CENTER);
        DEMAND2.setText("Please input the following missing field(s):");
        
        MID.setHgap(15);
        MID.setVgap(10);
        MID.setAlignment(Pos.CENTER);
        MID.add(DEMAND, 0, 0);
        MID.add(MESSAGE, 0, 1);
        MID.add(DEMAND2, 0, 3);
        MID.add(MESSAGE2, 0, 4);
        
        return MID;
    }
    
    public GridPane DesignAddExtension(Stage MAINWINDOW, GridPane MID, String INVESTNAME) throws IOException{
        MYFONT = new FontMeUp();
        Integer CHOICE = 1;
        DSINV = new DesignInv(MAINWINDOW, CHOICE);
        
        MESSAGE = new Text();
        MESSAGE.setText("Investment " + INVESTNAME + " has been deleted." );
        MESSAGE.setFill(MYFONT.getTitleColor());
        MESSAGE.setFont(MYFONT.getOswaldRegular());
        
        MID.setHgap(15);
        MID.setVgap(10);
        MID.add(MESSAGE, 0, 0);
        MID.setAlignment(Pos.CENTER);
        return MID;
    }
    
    public void DelStockPage(Stage MAINWINDOW) throws IOException{
        DSINV = new DesignInv(MAINWINDOW, 0);
        ENTRANCE = DSINV.getScreen();
        MAINWINDOW.setScene(DSINV.getScreen());
        MAINWINDOW.setTitle("Deleted Investments");
    }
    
}
