package controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GetNames {
    BufferedReader BUFFEREDREADER;
    FileReader FIREADER;
    String FILECONTENT;
    String[] FILEDATA;      
    String[] INVNAMES;
    ToolsUse TONG;
    Integer POS;
    
    public String[] GetNames(String FILEME) throws FileNotFoundException, IOException{
        TONG = new ToolsUse();
        POS = 0;
        FIREADER = new FileReader(FILEME);
        BUFFEREDREADER = new BufferedReader(FIREADER);        
        INVNAMES = new String[TONG.FileMeasure("data/investment.txt")];
        
        while((FILECONTENT = BUFFEREDREADER.readLine()) != null) {
            FILEDATA = FILECONTENT.split(",");            
            INVNAMES[POS]= FILEDATA[0];
            POS++;
    	}
        FIREADER.close();        
        return INVNAMES;
    }    
}
