package Scene;

import java.util.StringJoiner;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class DesignAddExtension {
    Integer INDEX, INDEXA;
    StringJoiner JOINER;
    String[] MISSING;
    Integer[] POS;
    DesignAdd DSADD;
    ToggleGroup GROUP;
    RadioButton BTN1, BTN2;
    Text MESSAGE;
    
    public GridPane anotherInvestment(GridPane MID) {
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
    
    public GridPane missingFields(GridPane MID) {
        INDEXA = 0;
        for(INDEX=0;INDEX<5;INDEX++){
            if(DSADD.getMSG()[INDEX] !=  null && !DSADD.getMSG()[INDEX].trim().isEmpty()){
               INDEXA++;
            }
        }
        MISSING = new String[INDEXA];
        for(INDEX=0;INDEX<5;INDEX++){
            if(DSADD.getMSG()[INDEX] !=  null && !DSADD.getMSG()[INDEX].trim().isEmpty()){
               MISSING[INDEX] = DSADD.getMSG()[INDEX];
            }
        }
        MESSAGE = new Text();
        JOINER = new StringJoiner(System.lineSeparator());
        for(INDEX=0;INDEX<=INDEXA;INDEX++){
            JOINER.add(MISSING[INDEX]);
        }
        MESSAGE.setText(JOINER.toString());
       
        MID.setHgap(15);
        MID.setVgap(10);
        MID.setAlignment(Pos.CENTER);
        MID.add(MESSAGE, 0, 0);
        return MID;
    }
}
