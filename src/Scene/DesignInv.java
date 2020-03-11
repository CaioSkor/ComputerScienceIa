
package Scene;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Caio Skornicki
 */
public class DesignInv {
    HBox Bottom;
    GridPane TOP, MID;
    BorderPane layout;
    Scene ENTRANCE;
    Button backBTN,stockBTN;
    Design MAIN;
    Text TITLE;
    
    DesignInv(Stage MAINWINDOW) {
        
        Color titlecolor = Color.web("#cc0000");
        Font myFontloadFontOswaldRegularButton =
            Font.loadFont(getClass().getResourceAsStream("/fonts/Oswald-Regular.ttf"), 15);
        
        Font myFontloadFontOswaldBold = 
            Font.loadFont(getClass().getResourceAsStream("/fonts/Oswald-Bold.ttf"), 25);
        
        TITLE = new Text();
        TITLE.setText("Investments");
        TITLE.setFont(myFontloadFontOswaldBold);
        TITLE.setFill(titlecolor);
        
        backBTN = new Button();
        backBTN.setText("BACK");
        backBTN.setFont(myFontloadFontOswaldRegularButton);
        backBTN.setOnAction((ActionEvent e) -> {
            MAIN = new Design(MAINWINDOW);
            MAINWINDOW.setScene(MAIN.getScreen());
            MAINWINDOW.setTitle("Stock Organizer Software");
                });
        
        stockBTN = new Button();
        
        MID = new GridPane();
        MID.setHgap(10);
        MID.setVgap(10);
        MID.setAlignment(Pos.CENTER_LEFT);
        MID.add(stockBTN, 0, 0);
        
        TOP = new GridPane();
        TOP.setHgap(37);
        TOP.setVgap(10);
        TOP.setAlignment(Pos.TOP_LEFT);
        TOP.add(TITLE, 1, 2);
        
        Bottom = new HBox(30);
        Bottom.setAlignment(Pos.BOTTOM_LEFT);
        Bottom.getChildren().add(backBTN);
        Bottom.setPadding(new Insets(10, 20, 10, 15));
        
        layout = new BorderPane();
        layout.setTop(TOP);
        layout.setCenter(MID);
        layout.setBottom(Bottom);
        
        ENTRANCE = new Scene(layout, 700, 500);
    }


    public Scene getScreen(){
        return ENTRANCE;
    }
}
