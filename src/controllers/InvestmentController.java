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
    FileReader FILEREADER;   
    BufferedReader BUFFEREDREADER;
    String FILECONTENT;
    String[] FILEDATA;    
    private Investment INVESTMENT;
    private LinkedList<Investment> INVESTIMENTS;
    //private LinkedList<Investment> DELETEDINVESTIMENTS;
    
    public InvestmentController() throws IOException{
        FILEREADER = new FileReader("data/investment.txt");
        BUFFEREDREADER = new BufferedReader(FILEREADER);
        INVESTIMENTS = new LinkedList<Investment>();
 
        while((FILECONTENT = BUFFEREDREADER.readLine()) != null) {
            FILEDATA = FILECONTENT.split(",");
            INVESTMENT = new Investment(FILEDATA[0], FILEDATA[1], FILEDATA[2], FILEDATA[3], FILEDATA[4], FILEDATA[5]);
            INVESTIMENTS.add(INVESTMENT);
        }
        FILEREADER.close();
    }
    
    // CRUD - Create INVESTMENT
    public void createInvestment(String code, String price, String amount, String date, String reason, String deletionDate) throws IOException{
        FileWriter FILEWRITER;
        
        FILEWRITER = new FileWriter("data/investment.txt", true);

        INVESTMENT = new Investment(code, price, amount, date, reason, deletionDate);
        INVESTIMENTS.add(INVESTMENT);
        FILEWRITER.write(INVESTMENT.getCode()+ "," + INVESTMENT.getPrice()+ "," + INVESTMENT.getAmount()+ "," + INVESTMENT.getDate()+ "," + INVESTMENT.getReason() + "," + INVESTMENT.getDeletionDate());
        FILEWRITER.write(System.lineSeparator());
        FILEWRITER.close();
        
        System.out.println("Investment " + INVESTMENT.getCode() + " created.");
    }
    
    // CRUD - Delete INVESTMENT
    public void deleteInvestment(String code, String price, String amount, String date, String reason, String deletionDate) throws IOException{
        FileWriter fileWriter = new FileWriter("data/investment.txt");
        fileWriter.flush();
        for(int i = 0; i < INVESTIMENTS.size(); i++) {
            if( INVESTIMENTS.get(i).getCode().equals(code) && INVESTIMENTS.get(i).getPrice().equals(price) && INVESTIMENTS.get(i).getAmount().equals(amount) && INVESTIMENTS.get(i).getDate().equals(date) && INVESTIMENTS.get(i).getReason().equals(reason)){
                INVESTIMENTS.get(i).setDeletionDate(deletionDate);
                //INVESTIMENTS.set(i, null);
            }
            String fileContent = INVESTIMENTS.get(i).getCode() + "," + INVESTIMENTS.get(i).getPrice() + "," + INVESTIMENTS.get(i).getAmount() + "," + INVESTIMENTS.get(i).getDate() + "," + INVESTIMENTS.get(i).getReason() + "," + INVESTIMENTS.get(i).getDeletionDate();
            fileWriter.write(fileContent);
            fileWriter.write(System.lineSeparator());
            
        }     
        
        fileWriter.close();

        System.out.println("Investment " + code + " deleted.");
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
