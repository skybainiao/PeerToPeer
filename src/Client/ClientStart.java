package Client;

import Controller.Viewhandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientStart extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Client client = new ClientImpl();
        Viewhandler viewhandler = new Viewhandler(client);
        viewhandler.openGetInView();

    }
}
