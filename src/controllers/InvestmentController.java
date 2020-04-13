package controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Scanner;
import modules.Investment;

/**
 *
 * @author caio
 */
public class InvestmentController {
    private final FileReader FILEREADER, FILEREADER2;   
    private final BufferedReader BUFFEREDREADER, BUFFEREDREADER2;
    private String FILECONTENT, FILECONTENT2;
    private String[] FILEDATA, FILEDATA2;    
    private final LinkedList<Investment> INVESTIMENTS;
    private Integer MEDAMOUNT, REPETITION;
    private Integer[] amounts;
    double[] prices;
    double MEDPRICE;
    private String MEDPRICESTRING, MEDAMOUNTSTRING;
    private LinkedList<Investment> REPEATEDINVESTMENTS;
    
    private Investment INVESTMENT;
    private PerformanceController PERFORMANCE;
    
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
        
        FILEREADER2 = new FileReader("data/repeatedInvestments.txt");
        BUFFEREDREADER2 = new BufferedReader(FILEREADER2);
        REPEATEDINVESTMENTS = new LinkedList<Investment>();
        while((FILECONTENT2 = BUFFEREDREADER2.readLine()) != null) {
            FILEDATA2 = FILECONTENT2.split(",");
            INVESTMENT = new Investment(FILEDATA2[0], FILEDATA2[1], FILEDATA2[2], FILEDATA2[3], FILEDATA2[4], FILEDATA2[5]);
            REPEATEDINVESTMENTS.add(INVESTMENT);
        }
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
    
        public void createRepeatedInvestment(String code, String price, String amount, String date, String reason, String deletionDate) throws IOException{
        FileWriter FILEWRITER;
        
        FILEWRITER = new FileWriter("data/repeatedInvestments.txt", true);

        INVESTMENT = new Investment(code, price, amount, date, reason, deletionDate);
        REPEATEDINVESTMENTS.add(INVESTMENT);
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
    
    public void updateInvestment(String code, String price, String amount, String date, String reason, String newPrice, String newAmnt, String newDate, String newReason, String deldate) throws IOException{
        DecimalFormat df = new DecimalFormat("#.##");
        Integer SURE = 0;
        for(int i = 0; i < REPEATEDINVESTMENTS.size(); i++){
            if(REPEATEDINVESTMENTS.get(i).getCode().equals(code)){
                SURE++;
            }
        }
        
        if(SURE>0){
            createRepeatedInvestment(code, newPrice, newAmnt, newDate, newReason, deldate);
        }else{
            createRepeatedInvestment(code, price, amount, date, reason, deldate);
            createRepeatedInvestment(code, newPrice, newAmnt, newDate, newReason, deldate);
        }
        FileWriter fileWriter = new FileWriter("data/investment.txt");
        fileWriter.flush();
        
        REPETITION = 0;
        MEDAMOUNT = 0;
        MEDPRICE = 0;
        for(int i = 0; i < REPEATEDINVESTMENTS.size(); i++){
            if(REPEATEDINVESTMENTS.get(i).getCode().equals(code)){
                REPETITION++;
            }       
        }
        amounts = new Integer[REPETITION];
        prices = new double[REPETITION];
        Integer INDEX = 0;
        for(int i = 0; i < REPEATEDINVESTMENTS.size(); i++){
            if(REPEATEDINVESTMENTS.get(i).getCode().equals(code)){
                amounts[INDEX] = Integer.parseInt(REPEATEDINVESTMENTS.get(i).getAmount());
                prices[INDEX] = Double.parseDouble(REPEATEDINVESTMENTS.get(i).getPrice());
                INDEX++;
            }
        }
        
        double TOTAL = 0;
        for(int i = 0; i < INDEX; i++){
            TOTAL = TOTAL + amounts[i]*prices[i];
            MEDAMOUNT = MEDAMOUNT + amounts[i];
        }
        MEDPRICE = TOTAL / MEDAMOUNT;
        MEDPRICE = Double.valueOf(df.format(MEDPRICE));
        MEDPRICESTRING = String.valueOf(MEDPRICE);
        MEDAMOUNTSTRING = String.valueOf(MEDAMOUNT);
        
        for(int i = 0; i < INVESTIMENTS.size(); i++) {
            if( INVESTIMENTS.get(i).getCode().equals(code) && INVESTIMENTS.get(i).getPrice().equals(price) && INVESTIMENTS.get(i).getAmount().equals(amount) && INVESTIMENTS.get(i).getDate().equals(date) && INVESTIMENTS.get(i).getReason().equals(reason) && INVESTIMENTS.get(i).getDeletionDate().equals(deldate)){
                INVESTIMENTS.get(i).setPrice(MEDPRICESTRING);
                INVESTIMENTS.get(i).setAmount(MEDAMOUNTSTRING);
                INVESTIMENTS.get(i).setDate(newDate);
                INVESTIMENTS.get(i).setReason(newReason);
                INVESTIMENTS.get(i).setDeletionDate(deldate);
            }
            fileWriter.write(INVESTIMENTS.get(i).getCode()+ "," + INVESTIMENTS.get(i).getPrice()+ "," +INVESTIMENTS.get(i).getAmount()+ "," +INVESTIMENTS.get(i).getDate()+ ","+ INVESTIMENTS.get(i).getReason()+ "," + INVESTIMENTS.get(i).getDeletionDate());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
        
        System.out.println("Investment " + code + " updated.");
    }
    
    public void writeLastPerformance(String code, String sellprice, String price, String amount) throws IOException{
        FileWriter FILEWRITER;
        FILEWRITER = new FileWriter("data/lastperformance.txt", true);
        
        double PERF;
        PERF = (Double.parseDouble(sellprice) - Double.parseDouble(price))* Integer.parseInt(amount);
        String fileContent;
        fileContent = code + "," + String.valueOf(PERF);
        
        FILEWRITER.write(fileContent);
        FILEWRITER.write(System.lineSeparator());
        FILEWRITER.close();
        
        System.out.println("Investment last performance registred");
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