import javafx.application.Application;
import javafx.stage.Stage;
import mediator.RemoteModel;
import mediator.RemoteModelManager;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class MyApplication extends Application
{
  private Model model;
  private RemoteModel server;

  @Override public void start(Stage primaryStage)
  {
    model = new ModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);

    view.start(primaryStage);

    // starting server
    try
    {
      server = new RemoteModelManager(model);
    }
    catch (RemoteException | MalformedURLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void stop()
  {
    model.close();                        //closing observer threads
    ((RemoteModelManager)server).close(); //closing server
  }
}
