package view.barchart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import viewmodel.barchart.BarChartViewModel;

public class BarChartController
{
  @FXML Label timeStampLabel;
  @FXML BarChart<Double, String> barChart;
  private BarChartViewModel barChartViewModel;
  private XYChart.Data<String, Double> x = new XYChart.Data<>("X", 0.0);
  private XYChart.Data<String, Double> y = new XYChart.Data<>("Y", 0.0);
  private XYChart.Data<String, Double> z = new XYChart.Data<>("Z", 0.0);

  public void init(BarChartViewModel model)
  {
    this.barChartViewModel = model;
    x.YValueProperty().bind(barChartViewModel.getX());
    y.YValueProperty().bind(barChartViewModel.getY());
    z.YValueProperty().bind(barChartViewModel.getZ());
    XYChart.Series xSeries=new XYChart.Series();
    xSeries.setName("X");
    xSeries.getData().add(x);

    XYChart.Series ySeries=new XYChart.Series();
    ySeries.setName("Y");
    ySeries.getData().add(y);

    XYChart.Series zSeries=new XYChart.Series();
    zSeries.setName("Z");
    zSeries.getData().add(z);

    barChart.getData().add(xSeries);
    barChart.getData().add(ySeries);
    barChart.getData().add(zSeries);
  }

  public void onUpdateAction(ActionEvent event)
  {
    barChartViewModel.updateValues();
  }

}
