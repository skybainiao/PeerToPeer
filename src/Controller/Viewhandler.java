package Controller;

import Client.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Viewhandler {


    private Stage mainStage;
    private Client client;

    public Viewhandler(Client client){
        this.client=client;
        mainStage = new Stage();
    }

    private Scene chat;
    public void openChatView(){
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("ChatViewController.fxml"));
            Parent root=loader.load();
            ChatViewController chatViewController=loader.getController();
            chatViewController.init(client,this);
            mainStage.setTitle("Peer to peer");
            chat=new Scene(root);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        mainStage.setScene(chat);
        mainStage.show();
    }


    private Scene getIn;
    public void openGetInView(){
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("GetInController.fxml"));
            Parent root=loader.load();
            GetInController getInController=loader.getController();
            getInController.init(client,this);
            mainStage.setTitle("GetIn");
            getIn=new Scene(root);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        mainStage.setScene(getIn);
        mainStage.show();
    }




}
