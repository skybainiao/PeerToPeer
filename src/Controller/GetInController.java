package Controller;

import Client.Client;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class GetInController {

    private Viewhandler viewhandler;
    private Client client;

    @FXML
    private TextField username;
    @FXML
    private Button getInButton;

    public void init(Client client,Viewhandler viewhandler){
        this.client=client;
        this.viewhandler=viewhandler;
    }


    public void getIn() throws SQLException, RemoteException {
        if (username.getText()!=null){
            for (int i = 0; i < client.getAllUsers().size(); i++) {
                if (client.getAllUsers().contains(username.getText())){
                    System.out.println("Already exist");
                    client.setClientName(username.getText());
                    viewhandler.openChatView();
                }
                else {
                    client.setClientName(username.getText());
                    client.increase(client.getClientName());
                    viewhandler.openChatView();
                }
                break;
            }
        }
    }




}
