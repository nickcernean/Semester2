import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewHandler;

public class ThermometerApp extends Application
{

  @Override public void start(Stage stage) throws Exception
  {
    TaskListModel model=new TaskListModelImpl();
    ViewModelProvider viewModelProvider=new ViewModelProvider(model);
    ViewHandler viewHandler=new ViewHandler(stage,viewModelProvider);
    viewHandler.start();
  }
}
