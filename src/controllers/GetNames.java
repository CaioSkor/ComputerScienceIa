package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class GetNames {
    BufferedReader BUFFEREDREADER;
    FileReader FIREADER;
    String FILECONTENT;
    String[] FILEDATA;      
    String[] INVNAMES;
    File REFERENCE;
    ToolsUse TONG;
    Integer POS;
    
    public String[] GetNames(String FILEME) throws FileNotFoundException, IOException{
        TONG = new ToolsUse();
        POS = 0;
      //  REFERENCE = new File(FILEME);
        FIREADER = new FileReader(FILEME);
       // Scanner READER = new Scanner(REFERENCE);
        BUFFEREDREADER = new BufferedReader(FIREADER);        
        INVNAMES = new String[TONG.FileMeasure("data/investment.txt")];
        
        while((FILECONTENT = BUFFEREDREADER.readLine()) != null) {
            FILEDATA = FILECONTENT.split(","); 
            if(FILEDATA[5].equals("000000")){
                INVNAMES[POS]= FILEDATA[0];
                POS++;
            }
    	}
        FIREADER.close();        
        return INVNAMES;
    }    
}
