package Controller;

import Client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class ChatViewController {
    private Client client;
    private Viewhandler viewhandler;

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<String,String> friendName;
    @FXML
    private TableColumn<String,Integer> messageNum;
    @FXML
    private TextArea enterText;
    @FXML
    private TextArea messageText;
    @FXML
    private Button sendButton;


    public void init(Client client,Viewhandler viewhandler){
        this.client=client;
        this.viewhandler=viewhandler;



    }


    public void sendMessage(){
        String name = tableView.getSelectionModel().getSelectedItem().toString();
    }


    public void getAllMessages(){

    }


    public void getFriends() throws SQLException, RemoteException {
        //friendName.setCellValueFactory(new PropertyValueFactory<>("username"));
        client.getAllFriends(client.getClientName());
    }










}
