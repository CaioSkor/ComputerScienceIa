package Scene;

import controllers.InvestmentController;
import controllers.PerformanceController;
import controllers.ToolsUse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.util.Duration;

public class DesignAdd {

    private final Text CODETXT;
    private final Text PRCTXT;
    private final Text AMNTTXT;
    private final Text DATETXT;
    private final Text REATXT;
    private final Text TITLE;
    private Text NEWPRCTXT;
    private Text NAMEINVEST, PERFTXT, INDIVPERFTXT, PERCENTAGEPERFTXT, PERFTOTALTXT ,OPTCODE,OPT0,OPT1,OPT2,OPT3, OPT4,OPTPERF, OPTPERCENTAGEPERF, OPTTOTALPERF, DELTXT;
    private TextField STKCODE, PRC, AMNT, MYDATE, REASON, NEWPRC;
    private String[] MSG, TXTFIELDS, INFO, FORMATMSG;
    private Integer INDEX, INDEXA, CHECK, AMNTINT, CHECK2, CHECK3, CHOICE;
    private Button SUBBTN;
    private final Button BCKBTN;
    private Button DELETEBTN;
    private final HBox BOTTOM;
    private GridPane MID;
    private final GridPane TOP;
    private final BorderPane LAYOUT;
    private final Scene ENTRANCE;
    private Scene ENTRANCE2;
    private RadioButton RADBTN1, RADBTN2, RADBTN3, RADBTN4, RADBTN5, RADBTN6;
    private ToggleGroup GROUP, GROUP2, GROUP3;
    private Boolean BOOLE, CASE, BOOL2;
    private double PRCDOUBLE;
    
    private Design MAIN;
    private DesignAddExtension EXTENSION;
    private DesignInv DSINV, DSINV2;
    private InvestmentController INVESTCONTROL; 
    private ToolsUse TOOLS;
    private PerformanceController PERF;
    
    private final FontMeUp MYFONT;
    private Date DATENOW;
    private SimpleDateFormat DF;
    private String STRINGDATE;
    
    public DesignAdd(Stage MAINWINDOW,Boolean BOOL,Integer POS,String INVESTNAME, Boolean BOOL2) throws IOException {
        MYFONT = new FontMeUp();
        CHOICE = 1;
        DSINV = new DesignInv(MAINWINDOW, CHOICE);
        
        BOTTOM = new HBox(30);
        
        TITLE = new Text();
        BCKBTN = new Button();
        BCKBTN.setText("BACK");
        BCKBTN.setFont(MYFONT.getOswaldButton());
        
        if (!BOOL){
            TITLE.setText("Add investment");
            
            BCKBTN.setOnAction((ActionEvent e) -> {
                if(INVESTNAME.equals("NOINV")){
                    MAINWINDOW.setScene(DSINV.getScreen());
                    MAINWINDOW.setTitle("Investments");
                }else{
                    MAIN = new Design(MAINWINDOW);
                    MAINWINDOW.setScene(MAIN.getScreen());
                    MAINWINDOW.setTitle("Stock Organizer Software");
                }
                
            });
        }else{
            TOOLS = new ToolsUse();
            String CODESTRING = TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[0];
            TITLE.setText(CODESTRING);   
            DSINV.codeName(CODESTRING);
            if(!TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[5].equals("000000")){
                CHOICE = 0;
            }
                
        }
        TITLE.setFont(MYFONT.getOswaldBold());
        TITLE.setFill(MYFONT.getTitleColor());
        
        CODETXT = new Text();
        CODETXT.setText("Code");
        CODETXT.setFont(MYFONT.getOswaldRegular());
        CODETXT.setFill(Color.GRAY);
        
        PRCTXT = new Text();
        PRCTXT.setText("Price");
        PRCTXT.setFont(MYFONT.getOswaldRegular());
        PRCTXT.setFill(Color.GRAY);
        
        AMNTTXT = new Text();
        AMNTTXT.setText("Amount bought");
        AMNTTXT.setFont(MYFONT.getOswaldRegular());
        AMNTTXT.setFill(Color.GRAY);
        
        DATETXT = new Text();
        DATETXT.setText("Date");
        DATETXT.setFont(MYFONT.getOswaldRegular());
        DATETXT.setFill(Color.GRAY);
        
        REATXT = new Text();
        REATXT.setText("Reason");
        REATXT.setFont(MYFONT.getOswaldRegular());
        REATXT.setFill(Color.GRAY);
        
        ComboBox comboBox = new ComboBox();
        INVESTCONTROL = new InvestmentController();
        comboBox.setItems(FXCollections.observableList(INVESTCONTROL.readTickers()));
        
        /* All TextFields */
        if (!BOOL){
        }else{
            if(POS>-1) {
                NAMEINVEST = new Text(INVESTNAME);            
            }
        }

        /* All buttons */
        if (!BOOL){
            TOOLS = new ToolsUse();
            
            GROUP = new ToggleGroup();
                
            RADBTN1 = new RadioButton();
            RADBTN1.setText("Continue to investment");
            RADBTN1.setFont(MYFONT.getOswaldButton());
            RADBTN1.setTextFill(Color.GRAY);
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
            RADBTN2.setFont(MYFONT.getOswaldButton());
            RADBTN2.setTextFill(Color.GRAY);
            RADBTN2.setToggleGroup(GROUP);
            RADBTN2.setOnAction(e -> {
                BOOLE = true;
                try {
                    DSINV.continueInv(MAINWINDOW, BOOLE);
                } catch (IOException ex) {
                    Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            GROUP3 = new ToggleGroup();
            
            RADBTN5 = new RadioButton();
            RADBTN5.setText("Re-introduce investment");
            RADBTN5.setFont(MYFONT.getOswaldButton());
            RADBTN5.setTextFill(Color.GRAY);
            RADBTN5.setToggleGroup(GROUP);
            
            RADBTN6 = new RadioButton();
            RADBTN6.setText("Cancel");
            RADBTN6.setFont(MYFONT.getOswaldButton());
            RADBTN6.setTextFill(Color.GRAY);
            RADBTN6.setToggleGroup(GROUP);
            RADBTN6.setOnAction(e -> {
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
            
            TXTFIELDS = new String[5];
            TXTFIELDS[0] = "code";
            TXTFIELDS[1] = "price";
            TXTFIELDS[2] = "amount";
            TXTFIELDS[3] = "date";
            TXTFIELDS[4] = "reason";
            
            INFO = new String[5];
            
            SUBBTN = new Button();
            SUBBTN.setText("Submit");
            SUBBTN.setFont(MYFONT.getOswaldButton());
            SUBBTN.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    MID.getChildren().clear();
                    
                    EXTENSION = new DesignAddExtension();
                    
                    String CODE = comboBox.getSelectionModel().getSelectedItem().toString();
                    INFO[0] = CODE;
                    INFO[1] = PRC.getText();
                    INFO[2] = AMNT.getText();
                    INFO[3] = MYDATE.getText();
                    INFO[4] = REASON.getText();
                    
                    CHECK2=0;
                    INDEXA=0;
                    MSG = new String[5];
                    for(INDEX=1;INDEX<5;INDEX++){
                        if(INFO[INDEX] != null && !INFO[INDEX].trim().isEmpty()){
                            MSG[INDEX] = null;
                        }else{
                            MSG[INDEX] = TXTFIELDS[INDEX];
                        }
                        if(MSG[INDEX] != null && !MSG[INDEX].trim().isEmpty()){
                            INDEXA++;
                            CHECK2++;
                        }
                    }
                    
                    FORMATMSG = new String[2];
                    CHECK = 0;
                    try{
                        PRCDOUBLE = Double.parseDouble(PRC.getText());
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
                        try {
                            MID.getChildren().clear();
                            if(CODE.equals(TOOLS.TextBoxFiller("data/investment.txt", CODE)[0])){
                                if(!TOOLS.TextBoxFiller("data/investment.txt", CODE)[5].equals("000000")){
                                    RADBTN5.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent e) {
                                            try {
                                                INVESTCONTROL.recoverDeletedInvestment(CODE,TOOLS.TextBoxFiller("data/investment.txt", CODE)[1] , TOOLS.TextBoxFiller("data/investment.txt", CODE)[2], TOOLS.TextBoxFiller("data/investment.txt", CODE)[3],TOOLS.TextBoxFiller("data/investment.txt", CODE)[4], TOOLS.TextBoxFiller("data/investment.txt", CODE)[5], INFO[1], INFO[2], INFO[3], INFO[4]);
                                                DSINV = new DesignInv(MAINWINDOW, 1);
                                                MAINWINDOW.setScene(DSINV.getScreen());
                                            } catch (IOException ex) {
                                                Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    });
                                    String MESSAGE = "Investment " + CODE+ " is deleted.";                      
                                    EXTENSION.DesignAddExtension(MID, RADBTN5, RADBTN6, GROUP, MESSAGE);
                                }else{
                                    System.out.println(TOOLS.TextBoxFiller("data/investment.txt", CODE)[1]);
                                    INVESTCONTROL.updateInvestment(CODE, TOOLS.TextBoxFiller("data/investment.txt", CODE)[1], TOOLS.TextBoxFiller("data/investment.txt", CODE)[2] , TOOLS.TextBoxFiller("data/investment.txt", CODE)[3], TOOLS.TextBoxFiller("data/investment.txt", CODE)[4], PRC.getText(), AMNT.getText(), MYDATE.getText(), REASON.getText(), "000000");
                                    String MESSAGE = "Investment " + CODE + " has been updated with sucess";
                                    EXTENSION.DesignAddExtension(MID, RADBTN1, RADBTN2, GROUP, MESSAGE);
                                }
                            }else{
                                INVESTCONTROL.createInvestment(
                                        CODE,
                                        PRC.getText(),
                                        AMNT.getText(),
                                        MYDATE.getText(),
                                        REASON.getText(),
                                        "000000"
                                );
                                EXTENSION.DesignAddExtension(MID, RADBTN1, RADBTN2, GROUP);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        if(CHECK2 <= 0){
                            CASE = false;
                            EXTENSION.DesignAddExtension(MID, FORMATMSG, CASE);
                        }else{
                            if(CHECK == 0 || CHECK3 == 2){
                                CASE = true;
                                EXTENSION.DesignAddExtension(MID, MSG, CASE);
                            }else{
                                EXTENSION.DesignAddExtension(MID, MSG, FORMATMSG);
                            }
                        }
                    }
                BCKBTN.setOnAction(event ->{
                        try {
                            DSINV.backToAdd(MAINWINDOW);
                        } catch (IOException ex) {
                            Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                }         
            });
        }else{
            EXTENSION = new DesignAddExtension();
            GROUP2 = new ToggleGroup();
            
            RADBTN3 = new RadioButton();
            RADBTN3.setText("Simply erased");
            RADBTN3.setFont(MYFONT.getOswaldButton());
            RADBTN3.setTextFill(Color.GRAY);
            RADBTN3.setToggleGroup(GROUP2);
            RADBTN3.setOnAction(e -> {
                try {
                // Get the date
                    DATENOW = new Date();
                    DF = new SimpleDateFormat("dd.MM");
                    STRINGDATE = DF.format(DATENOW);
                    // Set the date as a the deleted date for the investment and delete the investment
                    InvestmentController investmentcontroller = new InvestmentController();
                    investmentcontroller.deleteInvestment(TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[0], 
                        TOOLS.TextBoxFiller("data/investment.txt", INVESTNAME)[1], 
                        TOOLS.TextBoxFiller("data/investment.txt", INVESTNAME)[2], 
                        TOOLS.TextBoxFiller("data/investment.txt", INVESTNAME)[3], 
                        TOOLS.TextBoxFiller("data/investment.txt", INVESTNAME)[4],
                        STRINGDATE
                    );
                
                    // Reload the scene
                    CHOICE = 1;
                    MID.getChildren().clear();
                    EXTENSION = new DesignAddExtension();
                    EXTENSION.DesignAddExtension(MAINWINDOW, MID, TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[0]); 
                    BCKBTN.setOnAction(event ->{
                        try {
                            DSINV=new DesignInv(MAINWINDOW, 1);
                            MAINWINDOW.setScene(DSINV.getScreen());
                        } catch (IOException ex) {
                            Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    });
                    
                  
                    } catch (IOException ex) {
                    Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            RADBTN4 = new RadioButton();
            RADBTN4.setText("Considered as sold");
            RADBTN4.setFont(MYFONT.getOswaldButton());
            RADBTN4.setTextFill(Color.GRAY);
            RADBTN4.setToggleGroup(GROUP2);
            RADBTN4.setOnAction(e -> {
                MID.getChildren().clear();
                
                NEWPRCTXT = new Text();
                NEWPRCTXT.setText("Sell price");
                NEWPRCTXT.setFont(MYFONT.getOswaldRegular());
                NEWPRCTXT.setFill(Color.DARKGRAY);
                
                NEWPRC = new TextField();
                
                SUBBTN = new Button();
                SUBBTN.setText("Submit");
                SUBBTN.setFont(MYFONT.getOswaldButton());
                SUBBTN.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        try {
                            INVESTCONTROL.writeLastPerformance(TOOLS.TextBoxFiller("data/investment.txt", INVESTNAME)[0], NEWPRC.getText(),TOOLS.TextBoxFiller("data/investment.txt", INVESTNAME)[1] ,TOOLS.TextBoxFiller("data/investment.txt", INVESTNAME)[2]);
                            try {
                                // Get the date
                                DATENOW = new Date();
                                DF = new SimpleDateFormat("dd.MM");
                                STRINGDATE = DF.format(DATENOW);
                                // Set the date as a the deleted date for the investment and delete the investment
                                InvestmentController investmentcontroller = new InvestmentController();
                                investmentcontroller.deleteInvestment(TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[0], 
                                    TOOLS.TextBoxFiller("data/investment.txt", INVESTNAME)[1], 
                                    TOOLS.TextBoxFiller("data/investment.txt", INVESTNAME)[2], 
                                    TOOLS.TextBoxFiller("data/investment.txt", INVESTNAME)[3], 
                                    TOOLS.TextBoxFiller("data/investment.txt", INVESTNAME)[4],
                                    STRINGDATE
                                );
                
                                 // Reload the scene
                                CHOICE = 1;
                                MID.getChildren().clear();
                                EXTENSION = new DesignAddExtension();
                                EXTENSION.DesignAddExtension(MAINWINDOW, MID, TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[0]); 
                  
                            } catch (IOException ex) {
                                Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                MID.add(NEWPRCTXT, 0, 0);
                MID.add(NEWPRC, 1, 0);
                MID.add(SUBBTN, 1, 1);
                BCKBTN.setOnAction((ActionEvent a) -> {
                    MID.getChildren().clear();
                    String WARNING;
                    if(!BOOL2){
                        try {
                            WARNING = "Is Investment " + TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[0] + " going to be:";
                            EXTENSION.DesignAddExtension(MID,RADBTN3,RADBTN4,GROUP2,WARNING);
                        } catch (IOException ex) {
                            Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                BOTTOM.getChildren().clear();
                PauseTransition DELAY = new PauseTransition(Duration.seconds(3));
                DELAY.setOnFinished(event ->{
                    BOOLE = false;
                    try {
                        DSINV.posRecoveryInv(MAINWINDOW, TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[0]);
                    } catch (IOException ex) {
                        Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                DELAY.play();
            });
            
            PERF = new PerformanceController();
            
            PERFTXT = new Text();
            PERFTXT.setText("Performance");
            PERFTXT.setFont(MYFONT.getOswaldRegular());
            PERFTXT.setFill(Color.DARKGRAY);
            
            PERFTOTALTXT = new Text();
            PERFTOTALTXT.setText("Total");
            PERFTOTALTXT.setFont(MYFONT.getOswaldRegular());
            PERFTOTALTXT.setFill(Color.GRAY);
            
            INDIVPERFTXT = new Text();
            INDIVPERFTXT.setText("Per share");
            INDIVPERFTXT.setFont(MYFONT.getOswaldRegular());
            INDIVPERFTXT.setFill(Color.GRAY);
            
            PERCENTAGEPERFTXT = new Text();
            PERCENTAGEPERFTXT.setText("Percentage");
            PERCENTAGEPERFTXT.setFont(MYFONT.getOswaldRegular());
            PERCENTAGEPERFTXT.setFill(Color.GREY);
            
            DELTXT = new Text();
            DELTXT.setText("Date of deletion");
            DELTXT.setFont(MYFONT.getOswaldRegular());
            DELTXT.setFill(Color.GRAY);
            
            OPTCODE = new Text(TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[0]);
            OPTCODE.setFont(MYFONT.getOswaldRegular());
            OPT0 = new Text(TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[1] + " USD");
            OPT0.setFont(MYFONT.getOswaldRegular());
            OPT1 = new Text(TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[2]); 
            OPT1.setFont(MYFONT.getOswaldRegular());
            OPT2 = new Text(TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[3]); 
            OPT2.setFont(MYFONT.getOswaldRegular());
            OPT3 = new Text(TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[4]);
            OPT3.setFont(MYFONT.getOswaldRegular());
            OPT4 = new Text(TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[5]);
            OPT4.setFont(MYFONT.getOswaldRegular());
            
            if(TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[5].equals("000000")){
                OPTPERF = new Text(PERF.PerformanceCalc(TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[0],TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[1]));
                OPTPERF.setFont(MYFONT.getOswaldRegular());
                OPTTOTALPERF = new Text(PERF.getTotalPerformance(Integer.parseInt(TOOLS.TextBoxFiller("data/investment.txt", INVESTNAME)[2])));
                OPTTOTALPERF.setFont(MYFONT.getOswaldRegular());
                OPTPERCENTAGEPERF = new Text(PERF.getPercentageString());
                OPTPERCENTAGEPERF.setFont(MYFONT.getOswaldRegular());
                if(PERF.getAbsolutePerformance() < 0){
                    OPTPERF.setFill(MYFONT.getTitleColor());
                    OPTTOTALPERF.setFill(MYFONT.getTitleColor());
                    OPTPERCENTAGEPERF.setFill(MYFONT.getTitleColor());
                }else{
                    OPTPERF.setFill(Color.CHARTREUSE);
                    OPTTOTALPERF.setFill(Color.CHARTREUSE);
                    OPTPERCENTAGEPERF.setFill(Color.CHARTREUSE);
                }
            }
            
            DELETEBTN = new Button();
            DELETEBTN.setText("Delete");
            DELETEBTN.setOnAction(e ->{
                try {
                    MID.getChildren().clear();
                    String WARNING;
                    WARNING = "Is Investment " + TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[0] + " going to be:";
                    EXTENSION.DesignAddExtension(MID,RADBTN3,RADBTN4,GROUP2,WARNING);
                    BCKBTN.setOnAction((ActionEvent a) -> {
                        BOOLE = false;
                        try {
                            DSINV.continueInv(MAINWINDOW, BOOLE);
                        } catch (IOException ex) {
                            Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                  
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
                    if(POS>0){
                        try {
                            DSINV2 = new DesignInv(MAINWINDOW, 0);
                            MAINWINDOW.setScene(DSINV2.getScreen());
                            MAINWINDOW.setTitle("Investments");
                        } catch (IOException ex) {
                            Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
                        }    
                    }else{
                        if(POS==-3){
                           MAIN = new Design(MAINWINDOW);
                           MAINWINDOW.setScene(MAIN.getScreen());
                           MAINWINDOW.setTitle("Stock Organizer Software");
                        }else{
                            try {
                                DSINV2 = new DesignInv(MAINWINDOW, CHOICE);
                                ENTRANCE2 = DSINV2.getScreen();
                                MAINWINDOW.setScene(DSINV2.getScreen());
                                MAINWINDOW.setTitle("Investments");
                            } catch (IOException ex) {
                                Logger.getLogger(DesignAdd.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
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
        }
        
        if (!BOOL){
            MID.add(PRC, 1, 2 );
            MID.add(AMNT, 1, 3);
            MID.add(MYDATE, 1, 4);
            MID.add(REASON, 1, 5);
        }else{
            MID.add(OPTCODE, 3, 1);
            MID.add(OPT0, 3, 2 );
            MID.add(OPT1, 3, 3);
            MID.add(OPT2, 3, 4);
            MID.add(OPT3, 3, 5);  
            if(!TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[5].equals("000000")){
                MID.add(OPT4, 3, 6);
            }else{
                MID.add(OPTPERF, 3, 7);
                MID.add(OPTTOTALPERF, 3, 8);
                MID.add(OPTPERCENTAGEPERF, 3, 9);
                MID.add(DELETEBTN, 5, 10);
            }
        }
        
        MID.add(CODETXT, 0, 1);
        MID.add(PRCTXT, 0, 2);
        MID.add(AMNTTXT, 0, 3);
        MID.add(DATETXT, 0, 4);
        MID.add(REATXT, 0, 5);
        if (!BOOL){
            MID.add(SUBBTN, 2, 5);            
        }else{
            if(!TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[5].equals("000000")){
                if(TOOLS.CheckSoldInvestments(TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[0]) == 1){
                    PERFTOTALTXT.setText("Total performance");
                    MID.add(PERFTOTALTXT, 0, 7);
                    
                    OPTTOTALPERF = new Text();
                    OPTTOTALPERF.setText(TOOLS.getLastPerf(TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[0]) + " USD");
                    OPTTOTALPERF.setFont(MYFONT.getOswaldRegular());
                    
                    if(Double.parseDouble(TOOLS.getLastPerf(TOOLS.TextBoxFiller("data/investment.txt",INVESTNAME)[0])) >= 0){
                        OPTTOTALPERF.setFill(Color.CHARTREUSE);
                        PERFTOTALTXT.setFill(Color.CHARTREUSE);
                    }else{
                        OPTTOTALPERF.setFill(MYFONT.getTitleColor());
                        PERFTOTALTXT.setFill(MYFONT.getTitleColor());
                    }
                    MID.add(OPTTOTALPERF, 3, 7);
                }
                MID.add(DELTXT, 0, 6);
            }else{
                MID.add(PERFTXT, 2, 6);
                MID.add(INDIVPERFTXT, 0, 7);
                MID.add(PERFTOTALTXT, 0, 8);
                MID.add(PERCENTAGEPERFTXT, 0, 9);
            }
        }
        
        MID.setMinSize(200, 200);
        MID.setPadding(new Insets(10, 10, 10, 10));
            
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