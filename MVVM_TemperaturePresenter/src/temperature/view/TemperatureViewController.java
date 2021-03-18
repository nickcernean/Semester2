package temperature.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import temperature.mediator.TemperatureModel;
import temperature.model.Temperature;
import temperature.viewmodel.TemperatureViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperatureViewController
{
  @FXML private Label outputLabel;
  @FXML private TextField filterField;
  @FXML private Label filterLabel;

  private ViewHandler viewHandler;
  private Region root;
  private String thermometerId;
  private TemperatureViewModel viewModel;

  public void init(ViewHandler viewHandler, TemperatureViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    thermometerId = null;
    outputLabel.textProperty().bind(viewModel.getFilterValueProperty());
    filterLabel.textProperty().bind(viewModel.getTemperatureProperty());
    filterField.textProperty().bindBidirectional(viewModel.getThermometerIdProperty());
  }

  public void reset()
  {
    ////
  }

  public Region getRoot()
  {
    return root;
  }

   private void updateButtonPressed()
  {
    viewModel.getValue();
  }

  @FXML private void onFilter()
  {
    viewModel.updateThermometerId();
    updateButtonPressed();
  }

}
