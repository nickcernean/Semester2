package view;

import external.ObservableClock;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.TemperatureModel;
import utility.observer.subject.PropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperatureVIewController1 implements PropertyChangeListener
{
  @FXML private TextField textInput;

  @FXML private Label labelTimer;

  @FXML private Label labelOutput;

  private TemperatureModel model;
  private Region root;
  private ViewHandler viewHandler;
  private ObservableClock observableClock;


  public TemperatureVIewController1()
  {
    this.observableClock = new ObservableClock();
    observableClock
        .addPropertyChangeListener("TimeString", evt ->propertyChange(ev));

  }

  public void init(ViewHandler viewHandler, TemperatureModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    Thread thread = new Thread(observableClock);
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
    String newValue=(String)evt.getNewValue();
    Platform
        .runLater(() -> labelTimer.setText(newValue));
  }
}