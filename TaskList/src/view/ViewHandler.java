package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import viewmodel.ViewModelProvider;

import java.io.IOException;

public class ViewHandler
{
  private ViewModelProvider viewModelProvider;
  private Stage mainStage;

  public ViewHandler(Stage stage, ViewModelProvider viewModelProvider)
  {
    this.mainStage = stage;
    this.viewModelProvider = viewModelProvider;
    viewModelProvider.instatiateViewModels(this);
  }

  public void start()
  {
    openTaskListView();
  }

  public void opentNexTaskView()
  {
    openTaskListView();
  }

  public void openTaskListView()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("TaskListView.fxml"));
    Parent root = null;
    try
    {
      root = loader.load();

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    TaskListViewController viewController = loader.getController();
    viewController.init(viewModelProvider.getTaskListViewModel());
    mainStage.setTitle("View tasks");
    Scene scene = new Scene(root);
    mainStage.setScene(scene);
    mainStage.show();
  }

  public void openAddView()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("AddTask.fxml"));
    Parent root = null;
    try
    {
      root = loader.load();

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    AddTaskController viewController = loader.getController();
    viewController.init(viewModelProvider.getAddTaskViewModel());
    mainStage.setTitle("Add tasks");
    Scene scene = new Scene(root);
    mainStage.setScene(scene);
    mainStage.show();
  }
}
