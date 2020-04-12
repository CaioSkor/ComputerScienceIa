package Scene;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FontMeUp {
    private final Color TITLECOLOR;
    private final Font OSWALDBOLD;
    private final Font OSWALDREGULAR;
    private final Font OSWALDBUTTON;
    
    public FontMeUp(){
        TITLECOLOR = Color.web("#cc0000");
        OSWALDBOLD = Font.loadFont(getClass().getResourceAsStream("/fonts/Oswald-Bold.ttf"), 25);
        OSWALDREGULAR = Font.loadFont(getClass().getResourceAsStream("/fonts/Oswald-Regular.ttf"), 20);
        OSWALDBUTTON = Font.loadFont(getClass().getResourceAsStream("/fonts/Oswald-Regular.ttf"), 15);
    }
    
    public Color getTitleColor(){
        return TITLECOLOR;
    }
    
    public Font getOswaldBold(){
        return OSWALDBOLD;
    }
    
    public Font getOswaldRegular(){
        return OSWALDREGULAR;
    }
    
    public Font getOswaldButton(){
        return OSWALDBUTTON;
    }
}
