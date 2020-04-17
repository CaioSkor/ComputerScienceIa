/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author caio
 */
public class DesignPerfExtension {
    private Text MSG;
    
    private FontMeUp MYFONT;
    private DesignPerf DSPERF;
    
    public GridPane DesignPerfExtension(GridPane MID, String MESSAGE, Button RESETBTN){
        MYFONT = new FontMeUp();
        
        MSG = new Text();
        MSG.setText(MESSAGE);
        MSG.setFont(MYFONT.getOswaldRegular());
        MSG.setFill(Color.GRAY);
        
        MID.setVgap(20);
        MID.setHgap(40);
        MID.add(MSG, 0, 0);
        MID.add(RESETBTN, 0, 1);
        MID.setAlignment(Pos.CENTER);
        System.out.println("Hello");
        
        return MID;
    }
    
    public GridPane DesignPerfExtension(GridPane MID, String MESSAGE){
        MYFONT = new FontMeUp();
        
        MSG = new Text();
        MSG.setText(MESSAGE);
        MSG.setFont(MYFONT.getOswaldRegular());;
        MSG.setFill(Color.CHARTREUSE);
        
        MID.setHgap(40);
        MID.add(MSG, 0, 0);
        MID.setAlignment(Pos.CENTER);
        
       
        return MID;
    }
    
}
