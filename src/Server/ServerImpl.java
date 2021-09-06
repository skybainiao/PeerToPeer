package Server;


import Shared.Message;
import javafx.application.Platform;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ServerImpl implements Server {
    private JDBC jdbc;

    public ServerImpl() throws Exception{
        Registry registry= LocateRegistry.createRegistry(6666);
        registry.bind("Server",this);
        UnicastRemoteObject.exportObject(this,6666);
        this.jdbc = new JDBC();
    }


    public void sendMessage(String senderName,String receiveName,String text) throws SQLException,RemoteException {
        jdbc.sendMessage(senderName,receiveName,text);
    }


    public ArrayList<Message> getAllMessage(String senderName,String receiveName) throws SQLException,RemoteException{
        ResultSet rs = jdbc.getMessage(senderName,receiveName);
        ArrayList<Message> messages = new ArrayList<>();

        try {
            while (rs.next()){
                String text = rs.getString("chatMessages");

                Message message = new Message(text);
                messages.add(message);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return messages;
    }


    public ArrayList<String> getAllFriends(String username) throws SQLException,RemoteException{
        ResultSet rs = jdbc.getFriends(username);
        ArrayList<String> strings = new ArrayList<>();

        try {
            while (rs.next()){
                String friendName = rs.getString("friendName");

                strings.add(friendName);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return strings;

    }


    public void addUser(String username) throws SQLException,RemoteException{
        Platform.runLater(()->{
            try {
                jdbc.addUser(username);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Server");
    }


    public void increase(String username) throws SQLException,RemoteException{
        jdbc.increase(username);
    }





}
