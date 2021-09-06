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


    public void ClientImpl(String name) throws IOException, NotBoundException {
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
    public ArrayList<Message> getAllMessage(String receiveName) throws SQLException, RemoteException {
        return server.getAllMessage(name,receiveName);
    }

    public String getClientName() {
        return name;
    }

    public void setClientName(String clientName){
        this.name=clientName;
    }

    @Override
    public void addUser(String username) throws SQLException, RemoteException {
        Platform.runLater(()->{
            try {
                server.addUser(username);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

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
