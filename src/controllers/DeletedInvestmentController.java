/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import modules.deleteInvestment;

/**
 *
 * @author caio
 */
public class DeletedInvestmentController {
    FileReader FILEREADER;   
    BufferedReader BUFFEREDREADER;
    String FILECONTENT;
    String[] FILEDATA;    
    private deleteInvestment DELETEDINVESTMENT;
    private LinkedList<deleteInvestment> DELETEDINVESTIMENTS;
    
    public DeletedInvestmentController() throws IOException{
        FILEREADER = new FileReader("data/deletedinvestment.txt");
        BUFFEREDREADER = new BufferedReader(FILEREADER);
        DELETEDINVESTIMENTS = new LinkedList<deleteInvestment>();
 
        while((FILECONTENT = BUFFEREDREADER.readLine()) != null) {
            FILEDATA = FILECONTENT.split(",");
            DELETEDINVESTMENT = new deleteInvestment(FILEDATA[0], FILEDATA[1], FILEDATA[2], FILEDATA[3], FILEDATA[4], FILEDATA[5]);
            DELETEDINVESTIMENTS.add(DELETEDINVESTMENT);
        }
        FILEREADER.close();
    }
    
    public void deleteInvestment(String code, String price, String amount, String date, String reason, String deldate) throws IOException{
        FileWriter FILEWRITER;
        
        FILEWRITER = new FileWriter("data/investment.txt", true);
        DELETEDINVESTMENT = new deleteInvestment(code, price, amount, date, reason, deldate);
        DELETEDINVESTIMENTS.add(DELETEDINVESTMENT);
        FILEWRITER.write(DELETEDINVESTMENT.getCode()+ "," + DELETEDINVESTMENT.getPrice()+ "," + DELETEDINVESTMENT.getAmount()+ "," + DELETEDINVESTMENT.getDate()+ "," + DELETEDINVESTMENT.getReason()+ "," + DELETEDINVESTMENT.getDeletionDate());
        FILEWRITER.write(System.lineSeparator());
        FILEWRITER.close();
        
        System.out.println("Investment " + DELETEDINVESTMENT.getCode() + " created.");
    }
}
