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
        FXMLLoader f = new FXMLLoader();
        f.setLocation(getClass().getResource("mainpage.fxml"));
        Parent root =f.load();
        MainPageController mainPagec = f.getController();

        FXMLLoader betalLoader = new FXMLLoader();
        betalLoader.setLocation(getClass().getResource("betalsida.fxml"));
        Parent betal = betalLoader.load();
        BetalsidaController betalC = betalLoader.getController();

        FXMLLoader tidigareKopLoader = new FXMLLoader();
        tidigareKopLoader.setLocation(getClass().getResource("tidigarekop.fxml"));
        Parent tidigareKop  =tidigareKopLoader.load();
        TidigareKopController tidigareKopC = tidigareKopLoader.getController();

        FXMLLoader kundServiceLoader = new FXMLLoader();
        kundServiceLoader.setLocation(getClass().getResource("kundservice.fxml"));
        Parent kundservice = kundServiceLoader.load();
         //kundserviceC = betalLoader.getController();

        FXMLLoader listorLoader = new FXMLLoader();
        listorLoader.setLocation(getClass().getResource("listor.fxml"));
        Parent listor  =listorLoader.load();
       // ListorController listorC = f.getController();

        FXMLLoader kontoLoader = new FXMLLoader();
        kontoLoader.setLocation(getClass().getResource("kontoinstallning.fxml"));
        Parent konto = kontoLoader.load();




        primaryStage.setTitle("iMat");
        primaryStage.setScene(new Scene(root, 1377, 750));
        //Fundera på att fixa fullscreen
        //primaryStage.setMaximized(true);
        primaryStage.show();
        mainPagec.setStage(primaryStage,betal,konto,kundservice,tidigareKop,listor);

    }


    public static void main(String[] args) {
        launch(args);
    }

    

}
