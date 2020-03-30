package Scene;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FontMeUp {
    Color TITLECOLOR;
    Font OSWALDBOLD,OSWALDREGULAR,OSWALDBUTTON;
    
    public FontMeUp(){
        TITLECOLOR = Color.web("#cc0000");
        OSWALDBOLD = Font.loadFont(getClass().getResourceAsStream("/fonts/Oswald-Bold.ttf"), 25);
        OSWALDREGULAR = Font.loadFont(getClass().getResourceAsStream("/fonts/Oswald-Regular.ttf"), 20);
        OSWALDBUTTON = Font.loadFont(getClass().getResourceAsStream("/fonts/Oswald-Regular.ttf"), 15);
    }
}
