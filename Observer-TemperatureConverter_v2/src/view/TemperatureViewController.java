package view;

import external.RunnableClock;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.TemperatureModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperatureViewController implements PropertyChangeListener
{
  @FXML private TextField textInput;

  @FXML private Label labelTimer;

  @FXML private Label labelOutput;

  private TemperatureModel model;
  private Region root;
  private ViewHandler viewHandler;
  private RunnableClock runnableClock;

  public TemperatureViewController()
  {
    this.runnableClock = new RunnableClock();
    runnableClock.addPropertyChangeListener("TimeString",evt -> propertyChange(evt));
  }

  public void init(ViewHandler viewHandler, TemperatureModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    Thread thread = new Thread(runnableClock);
    thread.setDaemon(true);
    thread.start();

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
    try
    {
      double value = Double.parseDouble(textInput.getText());
      double result = model.toCelsius(value);
      labelOutput.setText(
          textInput.getText() + " fahrenheit = " + result + " celsius");
      reset();
    }
    catch (Exception e)
    {
      labelOutput.setText("Error in input");
    }
  }

  @FXML private void toFahrenheit()
  {
    try
    {
      double value = Double.parseDouble(textInput.getText());
      double result = model.toFahrenheit(value);
      labelOutput.setText(
          textInput.getText() + " celsius = " + result + " fahrenheit");
      reset();
    }
    catch (Exception e)
    {
      labelOutput.setText("Error in input");
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    String timevalue = (String) evt.getNewValue();
    Platform.runLater(() -> labelTimer.setText(timevalue));
  }
}