package Scene;

import com.intrinio.invoker.ApiException;
import controllers.InvestmentController;
import controllers.PerformanceController;
import controllers.ToolsUse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 *
 * @author Caio Skornicki
 */
public class DesignPerf {
    HBox Bottom, OVERRAL, SHARES, PROFIT;
    VBox BOTTOM2, BOTTOM3;
    GridPane TOP,MID;
    BorderPane layout;
    Scene ENTRANCE;
    Button backBTN, RESETBTN, RESETBTN2, ADDINV;
    Design MAIN;
    Text mainTXT, verb, gainTXT, lossTXT, perfTXT, TITLE, STARTPORT, GAINSTOCKSTXT, GAINOVERRALTXT, GAINSTOCKS, GAINSTOCKSPERC, GAINOVERRAL, GAINOVERRALPERC, REALGAINTXT, REALGAIN, REALGAINPERC;
    TextFlow Sentence;
    FontMeUp MYFONT;
    double performance;
    
    private ToolsUse TOOLS;
    private InvestmentController INVESTCONTROL;
    private PerformanceController PERF;
    private DesignPerfExtension EXTENSION;
    private DesignAdd DSADD;
    
    DesignPerf(Stage MAINWINDOW) throws IOException, ApiException {
        PERF = new PerformanceController();
        TOOLS = new ToolsUse();
        EXTENSION = new DesignPerfExtension();
        INVESTCONTROL = new InvestmentController();
        /* Performance Management */
        performance = -10;
        String performanceString = Double.toString(performance);
        
        /* Text Management */
        MYFONT = new FontMeUp();
        
        perfTXT = new Text();
        perfTXT.setText(performanceString);
        perfTXT.setFont(MYFONT.getOswaldRegular());
        
        mainTXT = new Text();
        mainTXT.setText("Your portfolio "); 
        mainTXT.setFont(MYFONT.getOswaldRegular());
        
        verb = new Text();
        verb.setText(" is ");
        verb.setFont(MYFONT.getOswaldRegular());
        
        gainTXT = new Text();
        gainTXT.setText("gain");
        gainTXT.setFont(MYFONT.getOswaldRegular());
        gainTXT.setFill(Color.GREENYELLOW);
        
        lossTXT = new Text();
        lossTXT.setText("loss");
        lossTXT.setFont(MYFONT.getOswaldRegular());
        lossTXT.setFill(MYFONT.getTitleColor());
        
        TITLE = new Text();
        TITLE.setText("Portfolio Performance");
        TITLE.setFont(MYFONT.getOswaldBold());
        TITLE.setFill(MYFONT.getTitleColor());
        
        STARTPORT = new Text();
        STARTPORT.setText("A portfolio has not yet being initialized. In order to start it, click the button");
        STARTPORT.setFont(MYFONT.getOswaldRegular());
        STARTPORT.setFill(Color.GRAY);
        
        Sentence = new TextFlow();
        Sentence.setLayoutX(50);
        Sentence.setLayoutY(40);
        
        if(performance>=0){
            Sentence.getChildren().addAll(mainTXT, gainTXT, verb, perfTXT);
        }else{
            Sentence.getChildren().addAll(mainTXT, lossTXT, verb, perfTXT);
        }
 
        /* Button management  */
        boolean BOOL = false;
        DSADD = new DesignAdd(MAINWINDOW,BOOL,-1,"", BOOL);
        
        ADDINV = new Button("Add Investment");
        ADDINV.setFont(MYFONT.getOswaldButton());
        ADDINV.setOnAction(event -> {
            MAINWINDOW.setScene(DSADD.getScreen());
            MAINWINDOW.setTitle("Add Investment");
        });
        
        
        backBTN = new Button("BACK");
        backBTN.setFont(MYFONT.getOswaldButton());
        backBTN.setOnAction((ActionEvent e) -> {
            MAIN = new Design(MAINWINDOW);
            MAINWINDOW.setScene(MAIN.getScreen());
            MAINWINDOW.setTitle("Stock Organizer Software");
        });
        
        RESETBTN2 = new Button();
        RESETBTN2.setText("RESET ALL PORTFOLIO");
        RESETBTN2.setFont(MYFONT.getOswaldButton());
        RESETBTN2.setOnAction((ActionEvent e) -> {
            try {
                INVESTCONTROL.deleteAll();
            } catch (IOException ex) {
                Logger.getLogger(DesignPerf.class.getName()).log(Level.SEVERE, null, ex);
            }
            MID.getChildren().clear();
            String MESSAGE = "Portfolio reseted with success";
            EXTENSION.DesignPerfExtension(MID, MESSAGE);
        });
        
        RESETBTN = new Button();
        RESETBTN.setText("RESET ALL PORTFOLIO");
        RESETBTN.setFont(MYFONT.getOswaldButton());
        RESETBTN.setOnAction((ActionEvent e) -> {
            MID.getChildren().clear();
            String MESSAGE = "This will reset all investments submitted and deleted. Are you sure?";
            EXTENSION.DesignPerfExtension(MID, MESSAGE, RESETBTN2);
            BOTTOM2.getChildren().clear();
        });
       
        if(TOOLS.portfolioStart() > 0){
            GAINSTOCKSTXT = new Text();
            GAINSTOCKSTXT.setText("Balance current stocks:");
            GAINSTOCKSTXT.setFont(MYFONT.getOswaldRegular());
            GAINSTOCKSTXT.setFill(Color.GRAY);
            
            GAINSTOCKS = new Text();
            GAINSTOCKS.setFont(MYFONT.getOswaldRegular());
            
            GAINSTOCKSPERC = new Text();
            GAINSTOCKSPERC.setFont(MYFONT.getOswaldRegular());
            
            GAINOVERRALTXT = new Text();
            GAINOVERRALTXT.setText("Balance current and sold stocks:");
            GAINOVERRALTXT.setFont(MYFONT.getOswaldRegular());
            GAINOVERRALTXT.setFill(Color.GRAY);
            
            GAINOVERRAL = new Text();
            GAINOVERRAL.setFont(MYFONT.getOswaldRegular());
            
            GAINOVERRALPERC = new Text();
            GAINOVERRALPERC.setFont(MYFONT.getOswaldRegular());
            
            REALGAINTXT = new Text();
            REALGAINTXT.setFont(MYFONT.getOswaldRegular());
            REALGAINTXT.setFill(Color.GRAY);
            
            REALGAIN = new Text();
            REALGAIN.setFont(MYFONT.getOswaldRegular());
            
            if(PERF.getTotalPerformanceShare() > 0){
                String MESSAGE1 = String.valueOf(PERF.getTotalPerformanceShare());
                GAINSTOCKS.setText("+ "+MESSAGE1 + " USD   ");
                GAINSTOCKS.setFill(Color.CHARTREUSE);
                String MESSAGE2 = String.valueOf(PERF.getTotalPerformancePerc());
                GAINSTOCKSPERC.setText("+ "+MESSAGE2 + " %   ");
                GAINSTOCKSPERC.setFill(Color.CHARTREUSE);
            }else{
                String MESSAGE1 = String.valueOf(PERF.getTotalPerformanceShare());
                GAINSTOCKS.setText(MESSAGE1 + " USD   ");
                GAINSTOCKS.setFill(MYFONT.getTitleColor());
                String MESSAGE2 = String.valueOf(PERF.getTotalPerformancePerc());
                GAINSTOCKSPERC.setText(MESSAGE2 + " %   ");
                GAINSTOCKSPERC.setFill(MYFONT.getTitleColor());
                
            }
            
            if(PERF.getTotalPerformanceAll() > 0){
                String MESSAGE3 = String.valueOf(PERF.getTotalPerformanceAll());
                GAINOVERRAL.setText("+ "+MESSAGE3 + " USD   ");
                GAINOVERRAL.setFill(Color.CHARTREUSE);
                String MESSAGE4 = String.valueOf(PERF.getTotalGainPercentage());
                GAINOVERRALPERC.setText("+ "+MESSAGE4 + " %   ");
                GAINOVERRALPERC.setFill(Color.CHARTREUSE);
                
            }else{
                String MESSAGE3 = String.valueOf(PERF.getTotalPerformanceAll());
                GAINOVERRAL.setText(MESSAGE3 + " USD   ");
                GAINOVERRAL.setFill(MYFONT.getTitleColor());
                String MESSAGE4 = String.valueOf(PERF.getTotalGainPercentage());
                GAINOVERRALPERC.setText(MESSAGE4 + " %   ");
                GAINOVERRALPERC.setFill(MYFONT.getTitleColor());
                
            }
            
            REALGAINTXT.setText("Resume sold stocks");
            if(PERF.getTotalProfit() > 0){
                String MESSAGE5 = String.valueOf(PERF.getTotalProfit());
                REALGAIN.setText("+ "+MESSAGE5 + " USD   ");
                REALGAIN.setFill(Color.CHARTREUSE);
            }else{
                String MESSAGE5 = String.valueOf(PERF.getTotalProfit());
                REALGAIN.setText(MESSAGE5 + " USD   ");
                REALGAIN.setFill(MYFONT.getTitleColor());
            }
            SHARES = new HBox();
            SHARES.getChildren().add(GAINSTOCKS);
            SHARES.getChildren().add(GAINSTOCKSPERC);
            SHARES.setPadding(new Insets(10,10,10,15));
            SHARES.setAlignment(Pos.CENTER);
            
            OVERRAL = new HBox();
            OVERRAL.getChildren().add(GAINOVERRAL);
            OVERRAL.getChildren().add(GAINOVERRALPERC);
            OVERRAL.setPadding(new Insets(10,10,10,15));
            OVERRAL.setAlignment(Pos.CENTER);
            
            PROFIT = new HBox();
            PROFIT.getChildren().add(REALGAIN);
            PROFIT.setPadding(new Insets(10,10,10,15));
            PROFIT.setAlignment(Pos.CENTER);
        }
        
        /* Pane management */
        MID = new GridPane();
        MID.setHgap(40);
       // MID.add(Sentence, 0, 0);
        MID.setAlignment(Pos.CENTER);     
        
        TOP = new GridPane();
        TOP.setHgap(37);
        TOP.setVgap(10);
        TOP.setAlignment(Pos.TOP_LEFT);
        TOP.add(TITLE, 1, 2);
      
        BOTTOM2 = new VBox(20);
        BOTTOM2.setPadding(new Insets(10, 10, 10, 15));
        BOTTOM2.setAlignment(Pos.BOTTOM_RIGHT);
        
        BOTTOM3 = new VBox(20);
        BOTTOM3.getChildren().add(backBTN);
        BOTTOM3.setPadding(new Insets(10, 10, 10, 15));
        
        Bottom = new HBox(410);
        Bottom.setPadding(new Insets(10, 20, 10, 15));
        Bottom.getChildren().add(BOTTOM3);
        Bottom.getChildren().add(BOTTOM2);
        
        
        if(TOOLS.portfolioStart() > 0){
            BOTTOM2.getChildren().add(RESETBTN);
            MID.add(GAINSTOCKSTXT, 0, 0);
            MID.add(SHARES, 0, 1);
            MID.add(GAINOVERRALTXT, 0, 2);
            MID.add(OVERRAL, 0, 3);
            MID.add(REALGAINTXT, 0, 4);
            MID.add(PROFIT, 0, 5);
        }else{
            MID.add(STARTPORT, 0, 0);
            MID.add(ADDINV, 0, 1);
        }
        layout = new BorderPane();
        layout.setTop(TOP);
        layout.setCenter(MID);
        layout.setBottom(Bottom);
        
        ENTRANCE = new Scene(layout, 700, 500);
        ENTRANCE.getStylesheets().add("CAIOSTYLE.css");      
        
    }

    public Scene getScreen(){
        return ENTRANCE;
    }
    
    public GridPane getMID(){
        return MID;
    }
}


