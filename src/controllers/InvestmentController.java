package controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import modules.Investment;

/**
 *
 * @author caio
 */
public class InvestmentController { 
    private Investment INVESTMENT;
    private LinkedList<Investment> INVESTIMENTS;
    
    public InvestmentController() throws IOException{
        FileReader TEXTFILEPATH = new FileReader("data/investment.txt");
        Scanner TEXTFILE = new Scanner(TEXTFILEPATH);
        INVESTIMENTS = new LinkedList<Investment>();
        while(TEXTFILE.hasNext()) {
            String[] DATA = TEXTFILE.nextLine().split(",");
            INVESTMENT = new Investment(DATA[0], DATA[1], DATA[2], DATA[3], DATA[4]);
            INVESTIMENTS.add(INVESTMENT);
        }
        TEXTFILE.close();
        TEXTFILEPATH.close();
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
    
    public LinkedList<String> readTickers() throws IOException{
        LinkedList<String> TICKERS = new LinkedList<String>();
        FileReader TEXTFILEPATH = new FileReader("./data/nasdaqlisted.txt");
        Scanner TEXTFILE = new Scanner(TEXTFILEPATH);
        while(TEXTFILE.hasNext()) {
            String[] DATA = TEXTFILE.nextLine().split("\\|");
            TICKERS.add(DATA[0]);
        }
        TEXTFILE.close();
        TEXTFILEPATH.close();
        
        return TICKERS;
    }
    
}
