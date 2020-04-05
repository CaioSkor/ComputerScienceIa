package Scene;

import controllers.DeletedInvestmentController;
import controllers.InvestmentController;
import controllers.ToolsUse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DesignAdd {
    Text CODETXT, PRCTXT, AMNTTXT, DATETXT, REATXT, TITLE,NAMEINVEST,OPTCODE,OPT0,OPT1,OPT2,OPT3;
    TextField STKCODE, PRC, AMNT, MYDATE, REASON;
    private String[] MSG, TXTFIELDS, INFO, FORMATMSG;
    Integer INDEX, INDEXA, CHECK, PRCINT, AMNTINT, CHECK2, CHECK3, CHOICE;
    Button SUBBTN, BCKBTN, DELETEBTN;
    HBox BOTTOM;
    GridPane MID,TOP;
    BorderPane LAYOUT;
    Scene ENTRANCE;
    Scene ENTRANCE2;
    RadioButton RADBTN1, RADBTN2;
    ToggleGroup GROUP;
    Boolean BOOLE, CASE, BOOL2;
   
    Design MAIN;
    DesignAddExtension EXTENSION;
    DesignInv DSINV, DSINV2;
    InvestmentController INVESTCONTROL; 
    DeletedInvestmentController DELETECONTROL;
    ToolsUse FILLMEUP;
    
    FontMeUp MYFONT;
    private Date DATENOW;
    private SimpleDateFormat DF;
    String STRINGDATE;
    
    public DesignAdd(Stage MAINWINDOW,Boolean BOOL,Integer POS,String INVESTNAME, Boolean BOOL2) throws IOException {
        MYFONT = new FontMeUp();
        CHOICE = 1;
        DSINV = new DesignInv(MAINWINDOW, CHOICE);
        
        /* All text */
        TITLE = new Text();
        BCKBTN = new Button();
        BCKBTN.setText("BACK");
        BCKBTN.setFont(MYFONT.OSWALDBUTTON);
        
        if (!BOOL){
            TITLE.setText("Add investment");
            BCKBTN.setOnAction((ActionEvent e) -> {
                MAIN = new Design(MAINWINDOW);
                MAINWINDOW.setScene(MAIN.getScreen());
                MAINWINDOW.setTitle("Stock Organizer Software");
            });
        }else{
            FILLMEUP = new ToolsUse();
            String CODESTRING = FILLMEUP.TextBoxFiller("data/investment.txt",INVESTNAME)[0];
            TITLE.setText(CODESTRING); 
            System.out.println(FILLMEUP.TextBoxFiller("data/investment.txt",INVESTNAME)[1]);    
            DSINV.codeName(CODESTRING);
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
            
            GROUP = new ToggleGroup();
                
            RADBTN1 = new RadioButton();
            RADBTN1.setText("Continue to investment");
            RADBTN1.setFont(MYFONT.OSWALDBUTTON);
            RADBTN1.setToggleGroup(GROUP);
            RADBTN1.setOnAction(e -> {
                BOOLE = false;
                try {
                    DSINV.continueInv(MAINWINDOW, BOOLE);
                } catch (IOException ex) {
                    Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        
            RADBTN2 = new RadioButton();
            RADBTN2.setText("Add new investment");
            RADBTN1.setFont(MYFONT.OSWALDBUTTON);
            RADBTN2.setToggleGroup(GROUP);
            RADBTN2.setOnAction(e -> {
                BOOLE = true;
                try {
                    DSINV.continueInv(MAINWINDOW, BOOLE);
                } catch (IOException ex) {
                    Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            PRC = new TextField();
            AMNT = new TextField();
            MYDATE = new TextField();
            REASON = new TextField();
            /* this array is used to store in each position the name of each respective textfield
               in its own position
            */ 
            TXTFIELDS = new String[5];
            TXTFIELDS[0] = "code";
            TXTFIELDS[1] = "price";
            TXTFIELDS[2] = "amount";
            TXTFIELDS[3] = "date";
            TXTFIELDS[4] = "reason";
            /* this array is going to store any data inputed on the textfields when the SUBBTN is clicked */
            INFO = new String[5];
            SUBBTN = new Button();
            SUBBTN.setText("Submit");
            SUBBTN.setFont(MYFONT.OSWALDBUTTON);
            SUBBTN.setOnAction((ActionEvent e) -> {
                MID.getChildren().clear();
                EXTENSION = new DesignAddExtension();
                INFO[0] = comboBox.getSelectionModel().getSelectedItem().toString();
                INFO[1] = PRC.getText();             
                INFO[2] = AMNT.getText();
                INFO[3] = MYDATE.getText();
                INFO[4] = REASON.getText();
                System.out.println(INFO[4]);
                CHECK2=0;
                INDEXA=0;   
                /* this array will store only the name of the fields that are emptry, on the same position as they are
                on the array TXTFIELDS, the filled textfields are going to be given the value null.
                */
                MSG = new String[5];
                /* the loop is going to evaluate for each position for INFO[] whether it is populated or not. 
                if it is, then the for that position in the MSG array it is going to be given the value null
                */
                for(INDEX=1;INDEX<5;INDEX++){
                    if(INFO[INDEX] != null && !INFO[INDEX].trim().isEmpty()){
                        MSG[INDEX] = null;
                /* Otherwise, it means that the field is empty/null, so  position INDEX in the array MSG
                   is going to be populated with the name of the empty field
                        */
                    }else{
                        MSG[INDEX] = TXTFIELDS[INDEX];
                    }
                /* This is going to evaluate whether there are populated 
                    
                    */
                    if(MSG[INDEX] != null && !MSG[INDEX].trim().isEmpty()){
                        INDEXA++;  
                        CHECK2++;
                    }
                } 
                
                FORMATMSG = new String[2];
                CHECK = 0;
                try{
                    PRCINT = Integer.parseInt(PRC.getText());
                } catch(NumberFormatException er){ 
                    CHECK++;
                    FORMATMSG[CHECK-1] = "price";
                    INDEXA++;
                }
                try{
                    AMNTINT = Integer.parseInt(AMNT.getText());
                } catch(NumberFormatException er){
                    CHECK++;
                    FORMATMSG[CHECK-1] = "amount";
                    INDEXA++;
                }
                
                CHECK3 = 0;
                for(INDEX=1; INDEX<3; INDEX++){
                    if(INFO[INDEX] != null && !INFO[INDEX].trim().isEmpty()){    
                    }else{
                        CHECK3++;
                        FORMATMSG[INDEX-1] = null;  
                    }
                }

                if(INDEXA<=0){
                    MID.getChildren().clear();
                    try {
                        INVESTCONTROL.createInvestment(
                                comboBox.getSelectionModel().getSelectedItem().toString(),
                                PRC.getText(), 
                                AMNT.getText(), 
                                MYDATE.getText(), 
                                REASON.getText(),
                                "000000"                  // Deletion date empty, as it is being created
                        );
                    } catch (IOException ex) {
                        System.out.println("PROBLEMS");
                    }
                        EXTENSION.DesignAddExtension(MID, RADBTN1, RADBTN2, GROUP);
                    }else{
                        if(CHECK2 <= 0){
                            CASE = false;
                            EXTENSION.DesignAddExtension(MID, FORMATMSG, CASE);
                        }else{
                            if(CHECK == 0 || CHECK3 == 2){
                                CASE = true;
                                System.out.println(Arrays.toString(MSG));
                                EXTENSION.DesignAddExtension(MID, MSG, CASE);
                            }else{
                                EXTENSION.DesignAddExtension(MID, MSG, FORMATMSG);
                            }
                        }
                    }
            });
        }else{
            OPTCODE = new Text(FILLMEUP.TextBoxFiller("data/investment.txt",INVESTNAME)[0]);
            OPT0 = new Text(FILLMEUP.TextBoxFiller("data/investment.txt",INVESTNAME)[1]);            
            OPT1 = new Text(FILLMEUP.TextBoxFiller("data/investment.txt",INVESTNAME)[2]); 
            OPT2 = new Text(FILLMEUP.TextBoxFiller("data/investment.txt",INVESTNAME)[3]); 
            OPT3 = new Text(FILLMEUP.TextBoxFiller("data/investment.txt",INVESTNAME)[4]);
            
            DELETEBTN = new Button();
            DELETEBTN.setText("Delete");
            DELETEBTN.setOnAction(e ->{
            try {
                // Get the date
                DATENOW = new Date();
                DF = new SimpleDateFormat("dd.MM");
                STRINGDATE = DF.format(DATENOW);
                System.out.println(STRINGDATE);
                // Set the date as a the deleted date for the investment and delete the investment
                InvestmentController investmentcontroller = new InvestmentController();
                investmentcontroller.deleteInvestment(
                        FILLMEUP.TextBoxFiller("data/investment.txt",INVESTNAME)[0], 
                        FILLMEUP.TextBoxFiller("data/investment.txt", INVESTNAME)[1], 
                        FILLMEUP.TextBoxFiller("data/investment.txt", INVESTNAME)[2], 
                        FILLMEUP.TextBoxFiller("data/investment.txt", INVESTNAME)[3], 
                        FILLMEUP.TextBoxFiller("data/investment.txt", INVESTNAME)[4],
                        STRINGDATE
                );
                
                // Reload the scene
                CHOICE = 1;
                MID.getChildren().clear();
                EXTENSION = new DesignAddExtension();
                EXTENSION.DesignAddExtension(MAINWINDOW, MID, FILLMEUP.TextBoxFiller("data/investment.txt",INVESTNAME)[0]); 
                
                /*
                DATENOW = new Date();
                DF = new SimpleDateFormat("dd.MM");
                STRINGDATE = DF.format(DATENOW);
                System.out.println(STRINGDATE);
                EXTENSION = new DesignAddExtension();
                DeletedInvestmentController deletedinvestmentcontroller  = new DeletedInvestmentController();
                MID.getChildren().clear();
                deletedinvestmentcontroller.deleteInvestment(FILLMEUP.TextBoxFiller("data/investment.txt",INVESTNAME)[0], FILLMEUP.TextBoxFiller("data/investment.txt", INVESTNAME)[1], FILLMEUP.TextBoxFiller("data/investment.txt", INVESTNAME)[2], FILLMEUP.TextBoxFiller("data/investment.txt", INVESTNAME)[3], FILLMEUP.TextBoxFiller("data/investment.txt", INVESTNAME)[4],STRINGDATE);
                */
                  
            } catch (IOException ex) {
                Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
            }
            });
            
            
            if(!BOOL2){
                BOOLE = true;
                BCKBTN.setOnAction((ActionEvent e) -> {   
                    try {
                        DSINV.continueInv(MAINWINDOW, BOOLE);
                    } catch (IOException ex) {
                        Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }else{
                BCKBTN.setOnAction((ActionEvent e) -> {
                    try {
                        DSINV2 = new DesignInv(MAINWINDOW, CHOICE);
                    } catch (IOException ex) {
                        Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ENTRANCE2 = DSINV2.getScreen();
                    MAINWINDOW.setScene(DSINV2.getScreen());
                    MAINWINDOW.setTitle("Investments");
                });
            }
        }
        
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
            MID.add(OPTCODE, 1, 1);
            MID.add(OPT0, 1, 2 );
            MID.add(OPT1, 1, 3);
            MID.add(OPT2, 1, 4);
            MID.add(OPT3, 1, 5);  
            MID.add(DELETEBTN, 5, 5);
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