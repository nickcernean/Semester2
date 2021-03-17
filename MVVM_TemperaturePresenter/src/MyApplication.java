import external.Thermometer;
import javafx.application.Application;
import javafx.stage.Stage;
import temperature.mediator.TemperatureModel;
import temperature.mediator.TemperatureModelManager;
import temperature.view.ViewHandler;
import temperature.viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  private Thermometer thermometer;
  private Thermometer thermometer2;

  @Override public void start(Stage primaryStage)
  {
    // Model
    TemperatureModel model = new TemperatureModelManager();
    thermometer = new Thermometer(model, "T1", 16, 1);
    thermometer2 = new Thermometer(model, "T2", 20, 7);

    Thread thread1 = new Thread(thermometer);
    Thread thread2 = new Thread(thermometer2);

    thread1.start();
    thread2.start();

    ViewModelFactory factory = new ViewModelFactory(model);
    // View
    ViewHandler view = new ViewHandler(factory);
    view.start(primaryStage);
  }

  @Override public void stop()
  {
    thermometer.stop();
    thermometer2.stop();
  }

}
