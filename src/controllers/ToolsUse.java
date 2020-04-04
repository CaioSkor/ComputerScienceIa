package controllers;

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

public class ToolsUse {
    Integer POS,INDEX;
    File REFERENCE;
    String MINILINE, MINILINE2, CHECK;
    String[] STRING,SORT, STRING2;
    boolean DELNOT;
    
    public Integer FileMeasure(String FILEME) throws FileNotFoundException, IOException{
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
                if (STRING2[5].equals("000000")){
                    POS++;
                }
            }   
    	}
        LNR.close();        
        System.out.println(POS);
        return POS;
    } 
    
    public void BoxFiller(String FILEME, ComboBox SELECTOR,String SOFAR) throws IOException{
        POS = FileMeasure(FILEME);
        if(POS != 0){
            SORT = new String[POS];
            REFERENCE = new File (FILEME);
            INDEX = 0;
            Scanner READER = new Scanner (REFERENCE);
            while (READER.hasNextLine()) {
                MINILINE = READER.nextLine();
                STRING = MINILINE.split(",");
                if(STRING[5].equals("000000")){
                    SORT[INDEX] = STRING[0];
                    INDEX = INDEX +1;
                }
            }
            Arrays.sort(SORT);
            for (INDEX=0; INDEX< POS; INDEX++){ 
                SELECTOR.getItems().add(SORT[INDEX]);  
            }
        }
    }
    
    public String[] TextBoxFiller(String FILEME, String INVESTNAME) throws IOException{
        FileMeasure(FILEME);
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
    
}
