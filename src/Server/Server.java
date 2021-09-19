package Server;

import Shared.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Server extends Remote {

    void sendMessage(String senderName,String receiveName,String text) throws SQLException, RemoteException;
    ArrayList<String> getAllMessage(String senderName, String receiveName) throws SQLException,RemoteException;
    void addUser(String username) throws SQLException, RemoteException;
    ArrayList<String> getAllFriends(String username) throws SQLException, RemoteException;
    void increase(String username) throws SQLException, RemoteException;
    ArrayList<String> getAllUsers() throws SQLException,RemoteException;
    int getNum(String username) throws SQLException,RemoteException;

}
