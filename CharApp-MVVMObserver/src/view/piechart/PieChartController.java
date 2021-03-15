package view.piechart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import viewmodel.piechart.PieChartViewModel;

public class PieChartController
{
  @FXML Label eventLabel;
  @FXML PieChart pieChart;
  private PieChartViewModel viewModel;

  private PieChart.Data x = new PieChart.Data("X", 0);
  private PieChart.Data y = new PieChart.Data("Y", 0);
  private PieChart.Data z = new PieChart.Data("Z", 0);

  public PieChartController()
  {
  }

  public void init(PieChartViewModel pieChartViewModel)
  {
    this.viewModel = pieChartViewModel;

    x.pieValueProperty().bind(viewModel.xProperty());
    y.pieValueProperty().bind(viewModel.yProperty());
    z.pieValueProperty().bind(viewModel.zProperty());
    eventLabel.textProperty().bind(viewModel.updateTimeStampProperty());
    ObservableList<PieChart.Data> datas = FXCollections
        .observableArrayList(x, y, z);
    pieChart.setData(datas);
  }

}
