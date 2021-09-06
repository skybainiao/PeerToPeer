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
            client.setClientName(username.getText());
            Platform.runLater(()->{
                try {
                    client.addUser(client.getClientName());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
            viewhandler.openChatView();
        }
    }




}
