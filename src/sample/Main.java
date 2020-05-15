package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("betalsida.fxml"));
        primaryStage.setTitle("iMat");
        primaryStage.setScene(new Scene(root, 1277, 750));
        //Fundera på att fixa fullscreen
        //primaryStage.setMaximized(true);
        primaryStage.show();
        
    }


    public static void main(String[] args) {
        launch(args);
    }

    

}
