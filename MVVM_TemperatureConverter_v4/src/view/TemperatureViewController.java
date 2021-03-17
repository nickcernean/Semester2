package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.TemperatureViewModel;

public class TemperatureViewController
{
  @FXML private TextField textInput;

  @FXML private Label labelTimer;

  @FXML private Label labelOutput;

  private TemperatureViewModel viewModel;
  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, TemperatureViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    labelOutput.textProperty().bind(viewModel.getOutputTemp());
    labelTimer.textProperty().bind(viewModel.getTime());
    textInput.textProperty().bindBidirectional(viewModel.getInputTemp());
  }

  public void reset()
  {
    textInput.setText("");
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void toCelsius()
  {
    viewModel.toCelsius();
  }

  @FXML private void toFahrenheit()
  {
    viewModel.toFahrenheit();
  }

}