import javafx.application.Application;
import javafx.stage.Stage;
import mediator.StudentListClientConnector;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  private Model model;
  private StudentListClientConnector server;

  @Override public void start(Stage primaryStage)
  {
    model = new ModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);

    view.start(primaryStage);

    // starting server
    server = new StudentListClientConnector(model);
    Thread serverThread = new Thread(server);
    serverThread.start();
  }

  @Override public void stop()
  {
    model.close();                        //closing observer threads
    server.close();
  }
}
