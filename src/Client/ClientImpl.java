package Client;

import Server.Server;
import Shared.Message;
import javafx.application.Platform;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientImpl implements Client{
    private Server server;
    private String name;


    public  ClientImpl(String name) throws IOException, NotBoundException {
        UnicastRemoteObject.exportObject(this, 0);
        Registry registry = LocateRegistry.getRegistry("localhost", 6666);
        server = (Server) registry.lookup("Server");

        this.name=name;
    }

    @Override
    public void sendMessage(String receiveName, String text) throws SQLException, RemoteException {
        server.sendMessage(name,receiveName,text);
    }

    @Override
    public ArrayList<String> getAllMessage(String receiveName) throws SQLException, RemoteException {
        return server.getAllMessage(name,receiveName);
    }

    public String getClientName() throws RemoteException {
        return name;
    }

    public void setClientName(String clientName) throws RemoteException{
        this.name=clientName;
    }

    @Override
    public ArrayList<String> getAllUsers() throws SQLException, RemoteException {
        return server.getAllUsers();
    }

    @Override
    public int getNum(String username) throws SQLException, RemoteException {
        return server.getNum(username);
    }

    @Override
    public void addUser(String username) throws SQLException, RemoteException {
        server.addUser(username);
        System.out.println("Client");


    }

    @Override
    public ArrayList<String> getAllFriends(String username) throws SQLException, RemoteException {
        return server.getAllFriends(username);
    }

    @Override
    public void increase(String username) throws SQLException, RemoteException {
        server.increase(username);
    }

}
