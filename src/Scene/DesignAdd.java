package Scene;

import controllers.InvestmentController;
import controllers.ToolsUse;
import java.io.IOException;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DesignAdd {
    Text CODETXT, PRCTXT, AMNTTXT, DATETXT, REATXT, TITLE,NAMEINVEST,OPT0,OPT1,OPT2,OPT3;
    TextField PRC, AMNT, MYDATE, REASON;
    String[] MSG, TXTFIELDS, INFO;
    Integer INDEX, INDEXA;
    Boolean isMSG;
    Button SUBBTN, BCKBTN;
    HBox BOTTOM;
    GridPane MID,TOP;
    BorderPane LAYOUT;
    Scene ENTRANCE;
   
    Design MAIN;
    DesignAddExtension EXTENSION;
    InvestmentController INVESTCONTROL;  
    ToolsUse FILLMEUP;
    
    FontMeUp MYFONT;
    
    public DesignAdd(Stage MAINWINDOW,Boolean BOOL,Integer POS,String INVESTNAME) throws IOException {
        MYFONT = new FontMeUp();

        /* All text */
        TITLE = new Text();
        if (!BOOL){
            TITLE.setText("Add investment");
        }else{
            TITLE.setText("Check investment"); 
            FILLMEUP = new ToolsUse();
            FILLMEUP.TextBoxFiller("data/investment.txt",INVESTNAME);
        }
        TITLE.setFont(MYFONT.OSWALDBOLD);
        TITLE.setFill(MYFONT.TITLECOLOR);
        
        CODETXT = new Text();
        CODETXT.setText("Code");
        CODETXT.setFont(MYFONT.OSWALDREGULAR);
        CODETXT.setFill(Color.GRAY);
        
        PRCTXT = new Text();
        PRCTXT.setText("Price");
        PRCTXT.setFont(MYFONT.OSWALDREGULAR);
        PRCTXT.setFill(Color.GRAY);
        
        AMNTTXT = new Text();
        AMNTTXT.setText("Amount bought");
        AMNTTXT.setFont(MYFONT.OSWALDREGULAR);
        AMNTTXT.setFill(Color.GRAY);
        
        DATETXT = new Text();
        DATETXT.setText("Date");
        DATETXT.setFont(MYFONT.OSWALDREGULAR);
        DATETXT.setFill(Color.GRAY);
        
        REATXT = new Text();
        REATXT.setText("Reason");
        REATXT.setFont(MYFONT.OSWALDREGULAR);
        REATXT.setFill(Color.GRAY);
        
        ComboBox comboBox = new ComboBox();
        INVESTCONTROL = new InvestmentController();
        comboBox.setItems(FXCollections.observableList(INVESTCONTROL.readTickers()));
        /* All TextFields */
        if (!BOOL){
            //STKCODE = new TextField();
        }else{
            if(POS>-1) {
                NAMEINVEST = new Text(INVESTNAME);            
            }
        }

        /* All buttons */
        if (!BOOL){
            PRC = new TextField();
            AMNT = new TextField();
            MYDATE = new TextField();
            REASON = new TextField();

            TXTFIELDS = new String[5];
            TXTFIELDS[0] = "code";
            TXTFIELDS[1] = "price";
            TXTFIELDS[2] = "amount";
            TXTFIELDS[3] = "date";
            TXTFIELDS[4] = "reason";
            INFO = new String[5];
            SUBBTN = new Button();
            SUBBTN.setText("Submit");
            SUBBTN.setFont(MYFONT.OSWALDBUTTON);
            SUBBTN.setOnAction((ActionEvent e) -> {
                EXTENSION = new DesignAddExtension();
                INFO[0] = comboBox.getSelectionModel().getSelectedItem().toString();
                INFO[1] = PRC.getText();             
                INFO[2] = AMNT.getText();
                INFO[3] = MYDATE.getText();
                INFO[4] = REASON.getText();
                INDEX=0;
                INDEXA=0;   
                MSG = new String[5];
                for(INDEX=0;INDEX<5;INDEX++){
                    if(INFO[INDEX] != null && !INFO[INDEX].trim().isEmpty()){
                        MSG[INDEX] = null;
                    }else{
                        MSG[INDEX] = TXTFIELDS[INDEX];
                    }
                    if(MSG[INDEX] != null && !MSG[INDEX].trim().isEmpty()){
                        INDEXA++;  
                    }
                } 
                if(INDEXA<=0){
                    MID.getChildren().clear();
                    try {
                        System.out.println("flag");
                        INVESTCONTROL.createInvestment(comboBox.getSelectionModel().getSelectedItem().toString(),PRC.getText(), AMNT.getText(), MYDATE.getText(), REASON.getText());
                    } catch (IOException ex) {
                        System.out.println("PROBLEMS");
                    }
                    EXTENSION.anotherInvestment(MID);
                }else{
                    MID.getChildren().clear();
                    System.out.println("Empty fields");
                    System.out.println(Arrays.toString(MSG));
                    EXTENSION.missingFields(MID, MSG);
                    isMSG = false;
                }
            });
        }else{
            OPT0 = new Text(FILLMEUP.TextBoxFiller("data/investment.txt",INVESTNAME)[1]);            
            OPT1 = new Text(FILLMEUP.TextBoxFiller("data/investment.txt",INVESTNAME)[2]); 
            OPT2 = new Text(FILLMEUP.TextBoxFiller("data/investment.txt",INVESTNAME)[3]); 
            OPT3 = new Text(FILLMEUP.TextBoxFiller("data/investment.txt",INVESTNAME)[4]); 
        }
        BCKBTN = new Button();
        BCKBTN.setText("BACK");
        BCKBTN.setFont(MYFONT.OSWALDBUTTON);
        BCKBTN.setOnAction((ActionEvent e) -> {
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
        
        MID = new GridPane();
        MID.setHgap(15);
        MID.setVgap(10);
        MID.setAlignment(Pos.CENTER);
        if (!BOOL){
            MID.add(comboBox, 1, 1);
        }else{
            if(POS>-1){
                MID.add(NAMEINVEST, 1, 1);            
            }
        }
        if (!BOOL){
            MID.add(PRC, 1, 2 );
            MID.add(AMNT, 1, 3);
            MID.add(MYDATE, 1, 4);
            MID.add(REASON, 1, 5);
        }else{
            MID.add(OPT0, 1, 2 );
            MID.add(OPT1, 1, 3);
            MID.add(OPT2, 1, 4);
            MID.add(OPT3, 1, 5);            
        }
        MID.add(CODETXT, 0, 1);
        MID.add(PRCTXT, 0, 2);
        MID.add(AMNTTXT, 0, 3);
        MID.add(DATETXT, 0, 4);
        MID.add(REATXT, 0, 5);
        if (!BOOL){
            MID.add(SUBBTN, 2, 5);            
        }
        MID.setMinSize(200, 200);
        MID.setPadding(new Insets(10, 10, 10, 10));
            
        BOTTOM = new HBox(30);
        BOTTOM.getChildren().add(BCKBTN);
        BOTTOM.setAlignment(Pos.BOTTOM_LEFT);
        BOTTOM.setPadding(new Insets(10, 10, 10, 15));
        
        LAYOUT = new BorderPane();
        LAYOUT.setTop(TOP);
        LAYOUT.setCenter(MID);
        LAYOUT.setBottom(BOTTOM);
        
        ENTRANCE = new Scene(LAYOUT, 700, 500);
        ENTRANCE.getStylesheets().add("CAIOSTYLE.css");        
    }

    public Scene getScreen(){
        return ENTRANCE;
    }
  }