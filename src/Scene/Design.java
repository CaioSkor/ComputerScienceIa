
package Scene;
     
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Caio Skornicki
 */
public class Design {
    HBox Bottom;
    VBox MID;
    GridPane TOP;
    BorderPane layout;
    Text TITLE, DateDisplay;
    Button BTN1, BTN2, BTN3;
    Scene ENTRANCE;
    DesignInv Invest;
    DesignAdd AddInv;
    DesignPerf PortPerfor;
    
    public Design (Stage MAINWINDOW){
        /* Text management */
        Color titlecolor = Color.web("#cc0000");
        Font myFontloadFontOswaldBold = 
             Font.loadFont(getClass().getResourceAsStream("/fonts/Oswald-Bold.ttf"), 25);
        
        Font myFontloadFontOswaldRegular =
             Font.loadFont(getClass().getResourceAsStream("/fonts/Oswald-Regular.ttf"), 20);
        
        Font myFontloadFontOswaldRegularButton =
             Font.loadFont(getClass().getResourceAsStream("/fonts/Oswald-Regular.ttf"), 15);
        
        
        TITLE = new Text();
        TITLE.setText("Stock Organizer Software");
        TITLE.setFont(myFontloadFontOswaldBold);
        TITLE.setFill(titlecolor);
        
         /* Button Management */
        BTN1 = new Button();
        BTN1.setText("Investments");
        BTN1.setFont(myFontloadFontOswaldRegularButton);
        BTN1.setOnAction((ActionEvent e) -> {
            Invest = new DesignInv(MAINWINDOW);
            MAINWINDOW.setScene(Invest.getScreen());
            MAINWINDOW.setTitle("Investments");
        });
        BTN1.setMinWidth(200);
        
        BTN2 = new Button();
        BTN2.setText("Portfolio performance");
        BTN2.setFont(myFontloadFontOswaldRegularButton);
        BTN2.setOnAction((ActionEvent e) -> {
            PortPerfor = new DesignPerf(MAINWINDOW);
            MAINWINDOW.setScene(PortPerfor.getScreen());
            MAINWINDOW.setTitle("Portfolio performance");
        });
        BTN2.setMinWidth(200);
        
        BTN3 = new Button();
        BTN3.setText("Add new investment");
        BTN3.setFont(myFontloadFontOswaldRegularButton);
        BTN3.setOnAction((ActionEvent e) -> {
            AddInv = new DesignAdd(MAINWINDOW);
            MAINWINDOW.setScene(AddInv.getScreen());
            MAINWINDOW.setTitle("Add new investment");
        });
        BTN3.setMinWidth(200);
        
        /* Pane management */
        
        TOP = new GridPane();
        TOP.setHgap(37);
        TOP.setVgap(10);
        TOP.add(TITLE, 5, 12);
        TOP.setPadding(new Insets(10, 10, 10, 33));
        
        
        MID = new VBox(50);
        MID.getChildren().addAll(BTN1,BTN2,BTN3);
        MID.setAlignment(Pos.CENTER);
        
        Bottom = new HBox(50);
        Bottom.setAlignment(Pos.BOTTOM_LEFT);
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("dd.MM");
        DateDisplay = new Text();
        DateDisplay.setText(df.format(date));
        DateDisplay.setFont(myFontloadFontOswaldRegular);
        Bottom.getChildren().add(DateDisplay);
        Bottom.setPadding(new Insets(10, 10, 10, 15));
        
        layout = new BorderPane();
        layout.setTop(TOP);
        layout.setCenter(MID);
        layout.setBottom(Bottom);
        
        
        ENTRANCE = new Scene(layout, 700, 500);
        ENTRANCE.getStylesheets().add("style.css");
       
}
    public Scene getScreen(){
        return ENTRANCE;
    }
    

           
    }
    

        

                
    