package server;

import server.mediator.UppercaseConnector;
import server.model.Model;
import server.model.ModelManager;
import server.view.SimpleConsoleView;

public class UppercaseMain
{
  public static void main(String args[])
  {
    Model model = new ModelManager();
    
    // simple console view
    SimpleConsoleView view = new SimpleConsoleView(model);
    
    // starting server
    UppercaseConnector server = new UppercaseConnector(model);
    Thread serverThread = new Thread(server);
    serverThread.start();
  }
}