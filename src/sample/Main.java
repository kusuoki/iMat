package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*Här laddas Fxml filerna in som sedan kommer bytas sinse mellan.
        * Filerna byts genom att scenens root förändras till en ny root. För att göra detta
        * krävs att Varje controller får en referens både till "Stage" instansen samt
        * till de Parents som de kommer byta till */
        FXMLLoader f = new FXMLLoader();
        f.setLocation(getClass().getResource("mainpage.fxml"));
        Parent root =f.load();
        MainPageController mainPagec = f.getController();

        FXMLLoader betalLoader = new FXMLLoader();
        betalLoader.setLocation(getClass().getResource("betalsida.fxml"));
        Parent betal=betalLoader.load(); //ader.load();
        BetalsidaController betalC = betalLoader.getController();

        FXMLLoader kundServiceLoader = new FXMLLoader();
        kundServiceLoader.setLocation(getClass().getResource("kundservice.fxml"));
        Parent kundservice = kundServiceLoader.load();
         KundserviceController kundC = kundServiceLoader.getController();
/*
        FXMLLoader listorLoader = new FXMLLoader();
        listorLoader.setLocation(getClass().getResource("listor.fxml"));
        Parent listor  =listorLoader.load();
        ListorController listorC = listorLoader.getController();*/

        FXMLLoader kontoLoader = new FXMLLoader();
        kontoLoader.setLocation(getClass().getResource("kontoinstallning.fxml"));
        Parent konto = kontoLoader.load();
        KontoinstallningController kontoC = kontoLoader.getController();




        primaryStage.setTitle("iMat");
        primaryStage.setScene(new Scene(root, 1377, 750));
        primaryStage.getIcons().add(new Image("sample/resources/Icons/burger.png"));
        //Fundera på att fixa fullscreen
        //primaryStage.setMaximized(true);
        primaryStage.show();
        BackButton.getBackButton().setStage(primaryStage);
        mainPagec.setStage(primaryStage,betal,konto,kundservice);
        betalC.setStage(primaryStage,root,kontoC);

        kontoC.setStage(primaryStage,root,kundservice,betal,mainPagec,betalC);

        kundC.setStage(primaryStage,root,konto,betal,mainPagec);

    }


    public static void main(String[] args) {
        launch(args);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                Model.getInstance().shutDown();
            }
        }));
    }


}
