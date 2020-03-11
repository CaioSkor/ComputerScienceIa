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
import modules.Investment;

/**
 *
 * @author caio
 */
public class InvestmentController {
    private Investment investment;
    private LinkedList<Investment> investments;
    //DesignAdd AddInv;
    
    public InvestmentController() throws IOException{
        FileReader fileReader = new FileReader("./data/investment.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String fileContent;
        investments = new LinkedList<Investment>();
  
        while((fileContent = bufferedReader.readLine()) != null) {
        
            String[] fileData = fileContent.split(",");
          
            investment = new Investment(fileData[0], fileData[1], fileData[2], fileData[3], fileData[4]);
     
            investments.add(investment);
        }
        
        fileReader.close();
    }
    
   /* public void windowInvesmtent() throws IOException{
        AddInv = new DesignAdd(MAINWINDOW);
        AddInv.getTop().getChildren().clear();
        AddInv.getTop().ad
    
    }*/
    
    
    
    // CRUD - Create investment
    public void createInvestment(String code, String price, String amount, String date, String reason) throws IOException{
        FileWriter fileWriter = new FileWriter("./data/investment.txt", true);
        investment = new Investment(code, price, amount, date, reason);
        investments.add(investment);
        fileWriter.write(investment.getCode()+ "," + investment.getPrice()+ "," + investment.getAmount()+ "," + investment.getDate()+ "," + investment.getReason());
        fileWriter.write(System.lineSeparator());
        fileWriter.close();
        
        System.out.println("Investment " + investment.getCode() + " created.");
    }
    
}
