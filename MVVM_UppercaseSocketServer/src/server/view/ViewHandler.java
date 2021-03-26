package server.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import server.viewmodel.ViewModelFactory;

import java.io.IOException;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;
  private ViewModelFactory viewModelFactory;

  private LogViewController logViewController;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    this.currentScene = new Scene(new Region());
    openView("log");
  }

  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "log":
        root = loadLogView("LogView.fxml");
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

  private Region loadLogView(String fxml)
  {
    if (logViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        Region root = loader.load();
        logViewController = loader.getController();
        logViewController.init(this, viewModelFactory.getLogViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      logViewController.reset();
    }
    return logViewController.getRoot();
  }
}
