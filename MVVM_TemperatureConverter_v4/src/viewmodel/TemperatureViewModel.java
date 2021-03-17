package viewmodel;

import external.RunnableClock;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.TemperatureModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperatureViewModel implements PropertyChangeListener
{
  private StringProperty inputTemp;
  private StringProperty outputTemp;
  private StringProperty time;
  private TemperatureModel model;
  private RunnableClock runnableClock;

  public TemperatureViewModel(TemperatureModel model)
  {
    this.inputTemp = new SimpleStringProperty();
    this.outputTemp = new SimpleStringProperty();
    this.time = new SimpleStringProperty();
    this.model = model;

    this.runnableClock = new RunnableClock();
    runnableClock
        .addPropertyChangeListener("TimeString", evt -> propertyChange(evt));
    Thread thread = new Thread(runnableClock);
    thread.setDaemon(true);
    thread.start();
  }

  public StringProperty getInputTemp()
  {
    return inputTemp;
  }

  public StringProperty getOutputTemp()
  {
    return outputTemp;
  }

  public StringProperty getTime()
  {
    return time;
  }

  public void toCelsius()
  {

    try
    {
      double inputValue = Double.parseDouble(inputTemp.get());
      String stringInput = String.valueOf(model.toCelsius(inputValue));
      outputTemp.set(stringInput);
      inputTemp.set(null);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      outputTemp.set("Error in input");
    }
  }

  public void toFahrenheit()
  {
    try
    {
      double inputValue = Double.parseDouble(inputTemp.get());
      String stringInput = String.valueOf(model.toFahrenheit(inputValue));
      outputTemp.set(stringInput);
     inputTemp.set(null);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      outputTemp.set("Error in input");
    }

  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      time.set(evt.getNewValue() + "");
    });
  }
}
