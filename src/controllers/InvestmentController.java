package controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import modules.Investment;

/**
 *
 * @author caio
 */
public class InvestmentController {
    FileReader FILEREADER;   
    BufferedReader BUFFEREDREADER;
    String FILECONTENT;
    String[] FILEDATA;    
    private Investment INVESTMENT;
    private LinkedList<Investment> INVESTIMENTS;
    
    public InvestmentController() throws IOException{
        FILEREADER = new FileReader("data/investment.txt");
        BUFFEREDREADER = new BufferedReader(FILEREADER);
        INVESTIMENTS = new LinkedList<Investment>();
 
        while((FILECONTENT = BUFFEREDREADER.readLine()) != null) {
            FILEDATA = FILECONTENT.split(",");
            INVESTMENT = new Investment(FILEDATA[0], FILEDATA[1], FILEDATA[2], FILEDATA[3], FILEDATA[4]);
            INVESTIMENTS.add(INVESTMENT);
        }
        FILEREADER.close();
    }
    
    // CRUD - Create INVESTMENT
    public void createInvestment(String code, String price, String amount, String date, String reason) throws IOException{
        FileWriter FILEWRITER;
        
        FILEWRITER = new FileWriter("data/investment.txt", true);
        INVESTMENT = new Investment(code, price, amount, date, reason);
        INVESTIMENTS.add(INVESTMENT);
        FILEWRITER.write(INVESTMENT.getCode()+ "," + INVESTMENT.getPrice()+ "," + INVESTMENT.getAmount()+ "," + INVESTMENT.getDate()+ "," + INVESTMENT.getReason());
        FILEWRITER.write(System.lineSeparator());
        FILEWRITER.close();
        
        System.out.println("Investment " + INVESTMENT.getCode() + " created.");
    }
    
}
