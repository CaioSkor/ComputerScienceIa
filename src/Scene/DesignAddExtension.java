/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import Scene.DesignAdd;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class DesignAddExtension {
    DesignAdd designADD;
    GridPane mid;
    RadioButton btn1, btn2;
    
    public void anotherInvestment() {
        mid = new GridPane();
        mid.setHgap(15);
        mid.setVgap(10);

        mid.setAlignment(Pos.CENTER);
        btn1 = new RadioButton();
        btn2 = new RadioButton();
        mid.add(btn1, 0, 0);
        mid.add(btn2, 0, 0);
    }
    
    public GridPane getDesignAddExtension(){
        return mid;
    }
    
}

