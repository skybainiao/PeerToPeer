package Controller;

import Client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Collection;

public class ChatViewController {
    private Client client;
    private Viewhandler viewhandler;

    @FXML
    private ListView listView;
    @FXML
    private TextArea enterText;
    @FXML
    private TextArea messageText;
    @FXML
    private Button sendButton;
    @FXML
    private Label unreadMessage;
    @FXML
    private Button selected;


    public void init(Client client,Viewhandler viewhandler) throws SQLException, RemoteException {
        this.client=client;
        this.viewhandler=viewhandler;

        getFriends();
        getUnread();

    }


    public void sendMessage() throws SQLException, RemoteException {
        String name = listView.getSelectionModel().getSelectedItem().toString();
        client.sendMessage(name, client.getClientName()+" :  "+enterText.getText());
        client.increase(name);
        getAllMessages();
        enterText.setText("");
    }


    public void getAllMessages() throws SQLException, RemoteException {
        messageText.setText("");
        String friend = listView.getSelectionModel().getSelectedItem().toString();
        String str = "";
        for (int i = 0; i < client.getAllMessage(friend).size(); i++) {
            String temp = client.getAllMessage(friend).get(i);
            str += temp +"\n";
            messageText.setText(str);
        }
    }


    public void getFriends() throws SQLException, RemoteException {
        ObservableList<String> friends = FXCollections.observableArrayList();
        friends.addAll(client.getAllFriends(client.getClientName()));
        listView.setItems(friends);
    }

    public void getUnread() throws RemoteException, SQLException {
        unreadMessage.setText("Unread Message :  "+client.getNum(client.getClientName()));
    }










}
