package controllers;

import Scene.DesignInv;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.Scanner;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ToolsUse {
    Integer POS,INDEX;
    File REFERENCE;
    String MINILINE, MINILINE2, CHECK;
    String[] STRING,SORT, STRING2;
    boolean DELNOT;
    
    DesignInv DSINV;
    
    public Integer FileMeasure(String FILEME, Integer CHOICE) throws FileNotFoundException, IOException{
         LineNumberReader LNR;
        FileReader FIREADER;
        POS = 0;
        REFERENCE = new File(FILEME);
        FIREADER = new FileReader(FILEME);
        LNR = new LineNumberReader(FIREADER); 
        Scanner READER = new Scanner(REFERENCE);
        while (LNR.readLine() != null){
            while (READER.hasNextLine()) {
                MINILINE2 = READER.nextLine();
                STRING2 = MINILINE2.split(",");
                if(CHOICE == 1){
                    if (STRING2[5].equals("000000")){
                        POS++;
                    }
                }else{
                    if (!STRING2[5].equals("000000")){
                        POS++;
                    }
                }
            }   
    	}
        LNR.close();        
        System.out.println(POS);
        return POS;
    }
    
    public void BoxFiller(String FILEME, ComboBox SELECTOR,String SOFAR, Integer CHOICE) throws IOException{
        POS = FileMeasure(FILEME,CHOICE);
            SORT = new String[POS];
            REFERENCE = new File (FILEME);
            INDEX = 0;
            Scanner READER = new Scanner (REFERENCE);
             while (READER.hasNextLine()) {
                 MINILINE = READER.nextLine();
                 STRING = MINILINE.split(",");
                 if(CHOICE == 1){
                     if(STRING[5].equals("000000")){
                         SORT[INDEX] = STRING[0];
                         INDEX = INDEX +1;
                     }
                 }else{
                     if(!STRING[5].equals("000000")){
                         SORT[INDEX] = STRING[0];
                         INDEX = INDEX +1;
                     }
                 }   
             }
             Arrays.sort(SORT);
             for (INDEX=0; INDEX< POS; INDEX++){ 
                SELECTOR.getItems().add(SORT[INDEX]);  
            }
    }
    
    public String[] TextBoxFiller(String FILEME, String INVESTNAME) throws IOException{
        REFERENCE = new File (FILEME);
        Scanner READER = new Scanner (REFERENCE);
        while (READER.hasNextLine()) {
            MINILINE = READER.nextLine();
            STRING = MINILINE.split(",");  
            if (STRING[0].equals(INVESTNAME)){
                break;
            }
        } 
        return STRING;
    }
    
    public void DeletedWindow(Stage MAINWINDOW) throws IOException{
         INDEX = 0;
         DSINV = new DesignInv(MAINWINDOW, INDEX);   
         MAINWINDOW.setScene(DSINV.getScreen());
     }

     public void BackDeletedWindow(Stage MAINWINDOW) throws IOException{
         INDEX = 1;
         DSINV = new DesignInv(MAINWINDOW, INDEX);   
         MAINWINDOW.setScene(DSINV.getScreen());
     }
    
}
