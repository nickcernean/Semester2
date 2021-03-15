import external.ObservableClock;
import javafx.application.Application;
import javafx.stage.Stage;
import model.TemperatureModel;
import model.TemperatureModelManager;
import view.TemperatureVIewController1;
import view.ViewHandler;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {
    // Model
    TemperatureModel model = new TemperatureModelManager();
    // View
    ViewHandler view = new ViewHandler(model);
    view.start(primaryStage);

  }
}
