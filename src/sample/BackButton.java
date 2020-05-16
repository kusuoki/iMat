package sample;

import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class BackButton {
    private static BackButton backButton;
    private static List<Parent> parents=new ArrayList<>();
    public static Stage stage;
    private BackButton(){}

    public static BackButton getBackButton() {
        if (backButton==null){
            backButton=new BackButton();
        }
        return backButton;
    }
    public void addToBackList(Parent parent){
        parents.add(parent);
    }
    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void goBack(){
        if (parents.size()>0){
        Parent root = parents.get(parents.size()-1);
        parents.remove(parents.size()-1);
        stage.getScene().setRoot(root);
    }
}}
