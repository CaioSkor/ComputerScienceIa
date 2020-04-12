/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author caio
 */
public class PerformanceController {
    private Scanner SCANNER;
    private String URLSTRING, MINILINE, LINE, PERFORMANCESTRING; 
    private URL URL;
    private JSONObject OBJECT;
    private URLConnection CON;
    private InputStream INPUT;
    private double PERFORMANCE, CURRENTPRICE;
    
    public void getCurrentPrice(String code) throws IOException {
        URLSTRING = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + code + "&apikey=W4Y24M2DSAHOK1O7";
        URL = new URL(URLSTRING);
        
        CON = URL.openConnection();
        INPUT = CON.getInputStream();
        
        SCANNER = new Scanner(URL.openStream());
        
        LINE = "";
        MINILINE = "";
        while (SCANNER.hasNext()) {
            MINILINE = SCANNER.nextLine();
            System.out.println(MINILINE);
            LINE = LINE + MINILINE.trim();
        }
        SCANNER.close();
        System.out.println(LINE);
        
        JSONObject jObject = new JSONObject(LINE);
        CURRENTPRICE = jObject.getJSONObject("Global Quote").getFloat("05. price");
        System.out.println(CURRENTPRICE);
        
    }
    
    public String PerformanceCalc(String code, String price) throws IOException{
    getCurrentPrice(code);
    DecimalFormat df = new DecimalFormat("#.##");
    CURRENTPRICE = Double.valueOf(df.format(CURRENTPRICE));
    
    PERFORMANCE = CURRENTPRICE - Double.parseDouble(price);
    PERFORMANCE = Double.valueOf(df.format(PERFORMANCE));
    if(PERFORMANCE > 0){
        PERFORMANCESTRING = "+ " + String.valueOf(PERFORMANCE) + " USD";
    }else{
        PERFORMANCESTRING = String.valueOf(PERFORMANCE) + " USD";
    }
    
    return PERFORMANCESTRING;
    } 
    
    public double getPerformance(){
        return PERFORMANCE;
    }
     
    
}
