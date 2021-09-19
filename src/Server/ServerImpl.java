package Server;


import Shared.Message;

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


    public ArrayList<String> getAllMessage(String senderName, String receiveName) throws SQLException,RemoteException{
        ResultSet rs = jdbc.getMessage(senderName,receiveName);
        ArrayList<String> messages = new ArrayList<>();

        try {
            while (rs.next()){
                String text = rs.getString("chatMessages");
                messages.add(text);
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
        jdbc.addUser(username);
    }


    public ArrayList<String> getAllUsers() throws SQLException,RemoteException {
        ResultSet rs = jdbc.getAllUsers();
        ArrayList<String> strings = new ArrayList<>();

        try {
            while (rs.next()){
                String username = rs.getString("username");

                strings.add(username);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return strings;
    }


    public void increase(String username) throws SQLException,RemoteException{
        jdbc.increase(username);
    }



    public int getNum(String username) throws SQLException,RemoteException {
        ResultSet rs = jdbc.getUnreadNum(username);
        int num = 0;

        try {
            while (rs.next()){
                int unread = rs.getInt("receiveMessageNum");

                num = unread;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }





}
