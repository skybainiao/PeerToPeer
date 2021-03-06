package Client;

import Shared.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Client extends Remote {
    void sendMessage(String receiveName,String text) throws SQLException, RemoteException;
    ArrayList<String> getAllMessage(String receiveName) throws SQLException,RemoteException;
    String getClientName() throws RemoteException;
    void addUser(String username) throws SQLException, RemoteException;
    ArrayList<String> getAllFriends(String username) throws SQLException, RemoteException;
    void increase(String username) throws SQLException, RemoteException;
    void setClientName(String clientName) throws RemoteException;
    ArrayList<String> getAllUsers() throws SQLException,RemoteException;
    int getNum(String username) throws SQLException,RemoteException;

}
