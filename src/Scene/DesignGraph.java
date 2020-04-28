/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import com.intrinio.invoker.ApiException;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author caio
 */
public class DesignGraph {
    Text TITLE;
    HBox BOTTOM;
    GridPane MID, TOP;
    Button BACKBTN;
    
    DesignAdd DSADD;
    FontMeUp MYFONT;
    
    public DesignGraph(Stage MAINWINDOW,Boolean BOOL,Integer POS,String INVESTNAME, Boolean BOOL2) throws IOException, ApiException{
        MYFONT = new FontMeUp();
        DSADD = new DesignAdd(MAINWINDOW,BOOL,POS,INVESTNAME,BOOL2);
        
        TITLE = new Text();
        TITLE.setText("Stock Graph");
        TITLE.setFont(MYFONT.getOswaldBold());
        TITLE.setFill(MYFONT.getTitleColor());
        
        BACKBTN = new Button();
        
        TOP = new GridPane();
        
        BOTTOM = new HBox(30);
    }
}
