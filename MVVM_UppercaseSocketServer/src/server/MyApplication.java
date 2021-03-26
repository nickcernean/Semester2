package server;

import javafx.application.Application;
import javafx.stage.Stage;
import server.mediator.UppercaseConnector;
import server.model.Model;
import server.model.ModelManager;
import server.view.ViewHandler;
import server.viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {
    Model model = new ModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(primaryStage);
    Thread thread=new Thread(new UppercaseConnector(model));
    thread.setDaemon(true);
    thread.start();
  }
}
