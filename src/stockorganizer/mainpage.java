package stockorganizer;

import Scene.Design;
import javafx.application.Application;
import javafx.stage.Stage;

public class mainpage extends Application {
    private Stage MAINWINDOW;
    private Design PAYSAGE;
    
    @Override
    public void start(Stage primaryStage) throws Exception  {
        MAINWINDOW = new Stage();
        PAYSAGE = new Design(MAINWINDOW);
        MAINWINDOW.setTitle("Stock Organizer Software");
        MAINWINDOW.setResizable(false);
        MAINWINDOW.setScene(PAYSAGE.getScreen());
        MAINWINDOW.show();     
    }
    public static void main(String[] args){
        launch(args);
    }  
}