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
    private Investment DELETEDINVESTMENT;
    private LinkedList<Investment> INVESTMENTS;
    private LinkedList<Investment> DELETEDINVESTMENTS;
    
    public InvestmentController() throws IOException{
        FILEREADER = new FileReader("data/investment.txt");
        BUFFEREDREADER = new BufferedReader(FILEREADER);
        INVESTMENTS = new LinkedList<Investment>();
 
        while((FILECONTENT = BUFFEREDREADER.readLine()) != null) {
            FILEDATA = FILECONTENT.split(",");
            INVESTMENT = new Investment(FILEDATA[0], FILEDATA[1], FILEDATA[2], FILEDATA[3], FILEDATA[4], FILEDATA[5]);
            INVESTMENTS.add(INVESTMENT);
        }
        FILEREADER.close();
    }
    
    // CRUD - Create INVESTMENT
    public void createInvestment(String code, String price, String amount, String date, String reason, String deletionDate) throws IOException{
        FileWriter FILEWRITER;
        
        FILEWRITER = new FileWriter("data/investment.txt", true);
        INVESTMENT = new Investment(code, price, amount, date, reason, deletionDate);
        INVESTMENTS.add(INVESTMENT);
        FILEWRITER.write(INVESTMENT.getCode()+ "," + INVESTMENT.getPrice()+ "," + INVESTMENT.getAmount()+ "," + INVESTMENT.getDate()+ "," + INVESTMENT.getReason() + "," + INVESTMENT.getDeletionDate());
        FILEWRITER.write(System.lineSeparator());
        FILEWRITER.close();
        
        System.out.println("Investment " + INVESTMENT.getCode() + " created.");
    }
    
    // CRUD - Delete INVESTMENT
    public void deleteInvestment(String code, String price, String amount, String date, String reason, String deletionDate) throws IOException{
        FileWriter fileWriter = new FileWriter("data/investment.txt");
        fileWriter.flush();
        for(int i = 0; i < INVESTMENTS.size(); i++) {
            if( INVESTMENTS.get(i).getCode().equals(code) && INVESTMENTS.get(i).getPrice().equals(price) && INVESTMENTS.get(i).getAmount().equals(amount) && INVESTMENTS.get(i).getDate().equals(date) && INVESTMENTS.get(i).getReason().equals(reason)){
                INVESTMENT = new Investment(code, price, amount, date, reason, deletionDate); // Set deletion date
                INVESTMENTS.set(i, INVESTMENT); // Set the investment as deleted in the linked list
            }else{
                String fileContent = INVESTMENTS.get(i).getCode() + "," + INVESTMENTS.get(i).getPrice() + "," + INVESTMENTS.get(i).getAmount() + "," + INVESTMENTS.get(i).getDate() + "," + INVESTMENTS.get(i).getReason()+ "," + INVESTMENTS.get(i).getDeletionDate();
                fileWriter.write(fileContent);
                fileWriter.write(System.lineSeparator());
            }
        }
        
        fileWriter.close();
        System.out.println("Investment" + code + " deleted.");
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
