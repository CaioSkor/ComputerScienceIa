package Scene;

import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class DesignAddExtension {
    DesignAdd DSADD;
    ToggleGroup GROUP;
    RadioButton BTN1, BTN2;
    
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
}
