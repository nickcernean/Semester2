package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler
{
  private ViewModelFactory modelFactory;
  private Stage primaryStage;
  private Scene currentScene;
  private ListExercisesViewController listExercisesViewController;
  private ManageExerciseViewController manageExerciseViewController;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.modelFactory = viewModelFactory;
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    this.currentScene = new Scene(new Region());
    openView("list");
  }

  public void openView(String s)
  {
    Region root = null;
    switch (s)
    {
      case "list":
        root = loadListExercise("ListExercisesView.fxml");
        break;
      case "manage":
        root = loadManageExercise("ManageExerciseView.fxml");
        break;
    }
    currentScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  private Region loadListExercise(String fxml)
  {
    if (listExercisesViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        Region root = loader.load();
        listExercisesViewController = loader.getController();
        listExercisesViewController
            .init(this, modelFactory.getListExerciseViewModel(), root);

      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      listExercisesViewController.reset();
    }
    return listExercisesViewController.getRoot();
  }

  private Region loadManageExercise(String fxml)
  {
    if (manageExerciseViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        Region root = loader.load();
        manageExerciseViewController = loader.getController();
        manageExerciseViewController
            .init(this, modelFactory.getManageExerciseViewModel(), root);

      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      manageExerciseViewController.reset();
    }
    return manageExerciseViewController.getRoot();
  }
}
