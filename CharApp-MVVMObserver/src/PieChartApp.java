import javafx.application.Application;
import javafx.stage.Stage;
import model.DataModelManager;
import model.ModelFactory;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.util.Random;

public class PieChartApp extends Application
{

  @Override public void start(Stage stage) throws Exception
  {
    ModelFactory mf = new ModelFactory();
    ViewModelFactory viewModelFactory = new ViewModelFactory(mf);
    ViewHandler viewHandler = new ViewHandler(stage, viewModelFactory);
    viewHandler.start();
    runAutoUpdate((DataModelManager) mf.getDataModel());
  }

  private void runAutoUpdate(DataModelManager m)
  {
    Thread thread = new Thread(() -> {
      Random r = new Random();
      while (true)
      {
        m.recalculateData();
        try
        {
          Thread.sleep(r.nextInt(500) + 1000);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    });
    thread.setDaemon(true);
    thread.start();
  }
}
