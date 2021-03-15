package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.barchart.BarChartController;
import view.piechart.PieChartController;
import view.textchart.TextController;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class ViewHandler
{
//  private Stage primaryStage;
  private ViewModelFactory viewModelFactory;

  public ViewHandler(Stage primaryStage, ViewModelFactory viewModelFactory)
  {
//    this.primaryStage = primaryStage;
    this.viewModelFactory = viewModelFactory;
  }

  public void start() throws Exception
  {
    openView("PieChart");
    openView("Text");
    openView("Bar");
  }

  public void openView(String fxml) throws IOException
  {
    Scene scene = null;
    FXMLLoader loader = new FXMLLoader();
    Parent root = null;
    Stage localStage = new Stage();
    if ("PieChart".equals(fxml))
    {
      loader.setLocation(getClass().getResource("piechart/PieChartView.fxml"));
      root = loader.load();
      PieChartController view = loader.getController();
      view.init(viewModelFactory.getPieChartViewModel());
      localStage.setTitle("Pie Chart");
    }
    else if ("Text".equals(fxml))
    {
      loader.setLocation(getClass().getResource("textchart/TextView.fxml"));
      root = loader.load();
      TextController view = loader.getController();
      view.init(viewModelFactory.getTextViewModel());
      localStage.setTitle("Text Chart");
    }
    else if ("Bar".equals(fxml))
    {
      loader.setLocation(getClass().getResource("barchart/BarChartView.fxml"));
      root = loader.load();
      BarChartController view = loader.getController();
      view.init(viewModelFactory.getBarChartViewModel());
      localStage.setTitle("Bar Chart");
    }

    scene = new Scene(root);
    localStage.setScene(scene);
    localStage.show();

  }
}
