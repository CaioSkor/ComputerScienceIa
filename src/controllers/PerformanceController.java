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
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author caio
 */
public class PerformanceController {
    Scanner SCANNER;
    String URLSTRING, MINILINE, LINE, LASTOPEN; 
    URL URL;
    JSONObject OBJECT;
    URLConnection CON;
    InputStream INPUT;
    
    public String getlastOpen(String code) throws IOException {
        URLSTRING = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + code + "&apikey=W4Y24M2DSAHOK1O7";
        URL = new URL(URLSTRING);
        
        CON = URL.openConnection();
        INPUT = CON.getInputStream();
        
        SCANNER = new Scanner(URL.openStream());
        
        LINE = "";
        MINILINE = "";
        while (SCANNER.hasNext()) {
            MINILINE = SCANNER.nextLine();
            System.out.println(LINE);
            LINE = LINE + MINILINE.trim();
        }
        SCANNER.close();
        System.out.println(LINE);

        JSONObject jObject = new JSONObject(LINE);
        LASTOPEN = jObject.getJSONObject("Global Quote").getString("08. previous close");
        System.out.println(LASTOPEN);
        
        return LASTOPEN;
    }
}
