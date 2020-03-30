package controllers;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FontDesign {
    public Font FontDesignRegular;
    public Font FontDesignBold;
    public Font FontDesignRegularButton;
    public Color FontDesignCOLOR;
   
    public Font FontDesignBold(){
        Font.loadFont(getClass().getResourceAsStream("/fonts/Oswald-Bold.ttf"), 25);
        return null;
    }    
    public Font FontDesignRegular(){
        Font.loadFont(getClass().getResourceAsStream("/fonts/Oswald-Regular.ttf"), 20);
        return null;
    }    
    public Font FontDesignRegularButton(){
        Font.loadFont(getClass().getResourceAsStream("/fonts/Oswald-Regular.ttf"), 15);    
        return null;
    }  
    
    public Color FontDesignCOLOR(){
        Color.web("#cc0000");
        return null;
    }
}
