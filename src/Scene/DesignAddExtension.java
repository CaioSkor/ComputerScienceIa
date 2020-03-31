package Scene;

import com.sun.deploy.util.StringUtils;
import java.util.StringJoiner;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class DesignAddExtension {
    Integer INDEX, INDEXA, COUNT;
    String JOINER;
    String[] MISSING, MSGRESULTS;
    Integer[] POS;
    DesignAdd DSADD;
    ToggleGroup GROUP;
    RadioButton BTN1, BTN2;
    Text MESSAGE, DEMAND;
    
    FontMeUp MYFONT;
    
    public GridPane anotherInvestment(GridPane MID) {
        MYFONT = new FontMeUp();
                
        MID.setHgap(15);
        MID.setVgap(10);

        MID.setAlignment(Pos.CENTER);
        GROUP = new ToggleGroup();
        BTN1 = new RadioButton();
        BTN1.setText("Continue to investment");
        BTN1.setToggleGroup(GROUP);        
        
        BTN2 = new RadioButton();
        BTN2.setText("Add new investment");
        BTN2.setToggleGroup(GROUP);
        
        MID.add(BTN1, 0, 0);
        MID.add(BTN2, 0, 1);
        return MID;
    }
    
    public GridPane missingFields(GridPane MID, String[] MSG) {
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
        DEMAND.setText("Please input the following missing fields:");
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
}
