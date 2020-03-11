package Scene;

import controllers.InvestmentController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DesignAdd {
    HBox Bottom;
    GridPane MID,TOP;
    BorderPane layout;
    Scene ENTRANCE;
    TextField StkCODE, PRC, AMNT, Date, Reason;
    Text CodeTXT, PrcTXT, AmntTXT, DateTXT, ReaTXT, TITLE;
    Button SubmitBTN, backBTN;
    private RadioButton continueBTN, newBTN;
    ToggleGroup group;
    Design MAIN;
    DesignAddExtension EXTENSION;
    boolean isExtension;
    
    public DesignAdd(Stage MAINWINDOW) {
        
        /* All TextFields */
        
        StkCODE = new TextField();
        
        PRC = new TextField();
        
        AMNT = new TextField();
        
        Date = new TextField();
        
        Reason = new TextField();
   
        
        /* All text */
        
        Color titlecolor = Color.web("#cc0000");
        Font myFontloadFontOswaldRegularButton =
            Font.loadFont(getClass().getResourceAsStream("/fonts/Oswald-Regular.ttf"), 15);
        
        Font myFontloadFontOswaldBold = 
            Font.loadFont(getClass().getResourceAsStream("/fonts/Oswald-Bold.ttf"), 25);
        
        Font myFontloadFontOswaldRegular =
             Font.loadFont(getClass().getResourceAsStream("/fonts/Oswald-Regular.ttf"), 20);
        
        
        CodeTXT = new Text();
        CodeTXT.setText("Code");
        CodeTXT.setFont(myFontloadFontOswaldRegular);
        CodeTXT.setFill(Color.GRAY);
        
        PrcTXT = new Text();
        PrcTXT.setText("Price");
        PrcTXT.setFont(myFontloadFontOswaldRegular);
        PrcTXT.setFill(Color.GRAY);
        
        AmntTXT = new Text();
        AmntTXT.setText("Amount bought");
        AmntTXT.setFont(myFontloadFontOswaldRegular);
        AmntTXT.setFill(Color.GRAY);
        
        DateTXT = new Text();
        DateTXT.setText("Date");
        DateTXT.setFont(myFontloadFontOswaldRegular);
        DateTXT.setFill(Color.GRAY);
        
        ReaTXT = new Text();
        ReaTXT.setText("Reason");
        ReaTXT.setFont(myFontloadFontOswaldRegular);
        ReaTXT.setFill(Color.GRAY);
        
        TITLE = new Text();
        TITLE.setText("Add investment");
        TITLE.setFont(myFontloadFontOswaldBold);
        TITLE.setFill(titlecolor);
         
        
          /* Radio buttons */
        group = new ToggleGroup();  
          
        continueBTN = new RadioButton();
        continueBTN.setText("Continue to investment");
        continueBTN.setToggleGroup(group);
          
        newBTN = new RadioButton();
        newBTN.setText("Add new investment");
        newBTN.setToggleGroup(group);
        newBTN.setOnAction((ActionEvent e) -> {
            MID.getChildren().clear();
            StkCODE.clear();
            PRC.clear();
            AMNT.clear();
            Date.clear();
            Reason.clear();
            MID.setHgap(15);
            MID.setVgap(10);
            MID.setAlignment(Pos.CENTER);
            MID.add(StkCODE, 1, 1);
            MID.add(PRC, 1, 2 );
            MID.add(AMNT, 1, 3);
            MID.add(Date, 1, 4);
            MID.add(Reason, 1, 5);
            MID.add(CodeTXT, 0, 1);
            MID.add(PrcTXT, 0, 2);
            MID.add(AmntTXT, 0, 3);
            MID.add(DateTXT, 0, 4);
            MID.add(ReaTXT, 0, 5);
            MID.add(SubmitBTN, 2, 5);
            MID.setMinSize(200, 200);
        });
          
          /* All buttons */
        isExtension = false;
        SubmitBTN = new Button();
        SubmitBTN.setText("Submit");
        SubmitBTN.setFont(myFontloadFontOswaldRegularButton);
        SubmitBTN.setOnAction((ActionEvent e) -> {
            try {
                InvestmentController investmentController = new InvestmentController();
                investmentController.createInvestment(StkCODE.getText(),PRC.getText(), AMNT.getText(), Date.getText(), Reason.getText());
                EXTENSION = new DesignAddExtension();
                isExtension = true;
            } catch (IOException ex) {
                Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        
        backBTN = new Button();
        backBTN.setText("BACK");
        backBTN.setFont(myFontloadFontOswaldRegularButton);
        backBTN.setOnAction((ActionEvent e) -> {
            MAIN = new Design(MAINWINDOW);
            MAINWINDOW.setScene(MAIN.getScreen());
            MAINWINDOW.setTitle("Stock Organizer Software");
        });
        
        /* Panes */
       
        TOP = new GridPane();
        TOP.setHgap(37);
        TOP.setVgap(10);
        TOP.setAlignment(Pos.TOP_LEFT);
        TOP.add(TITLE, 1, 2);
        
        System.out.println("true");
        MID = new GridPane();
        MID.setHgap(15);
        MID.setVgap(10);
        MID.setAlignment(Pos.CENTER);
        MID.add(StkCODE, 1, 1);
        MID.add(PRC, 1, 2 );
        MID.add(AMNT, 1, 3);
        MID.add(Date, 1, 4);
        MID.add(Reason, 1, 5);
        MID.add(CodeTXT, 0, 1);
        MID.add(PrcTXT, 0, 2);
        MID.add(AmntTXT, 0, 3);
        MID.add(DateTXT, 0, 4);
        MID.add(ReaTXT, 0, 5);
        MID.add(SubmitBTN, 2, 5);
        MID.setMinSize(200, 200);
        MID.setPadding(new Insets(10, 10, 10, 10));
        
        if(isExtension){
            MID = EXTENSION.getDesignAddExtension();
        }
            
        Bottom = new HBox(30);
        Bottom.getChildren().add(backBTN);
        Bottom.setAlignment(Pos.BOTTOM_LEFT);
        Bottom.setPadding(new Insets(10, 10, 10, 15));
        
        
        layout = new BorderPane();
        layout.setTop(TOP);
        layout.setCenter(MID);
        layout.setBottom(Bottom);
        
        ENTRANCE = new Scene(layout, 700, 500);
    }
     
    //public GridPane getTop(){
      //  return TOP;
    //}
   

    public Scene getScreen(){
        return ENTRANCE;
    }

    public GridPane getMID(){
        return MID;
    }
    
    public void setMID(GridPane mid){
        this.MID = mid;
    }
    
    public RadioButton getContinueBTN(){
        return continueBTN;
    }

    public RadioButton getNewBTN(){
        return newBTN;
    }
}