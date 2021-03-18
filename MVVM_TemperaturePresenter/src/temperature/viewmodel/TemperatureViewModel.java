package temperature.viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import temperature.mediator.TemperatureModel;
import temperature.model.Temperature;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperatureViewModel implements PropertyChangeListener
{
  private StringProperty temperature;
  private StringProperty thermometerId;
  private StringProperty filterValue;
  private TemperatureModel model;

  public TemperatureViewModel(TemperatureModel model)
  {
    this.model = model;
    this.temperature = new SimpleStringProperty();
    this.thermometerId = new SimpleStringProperty();
    this.filterValue = new SimpleStringProperty();
    model.addPropertyChangeListener("Update", evt -> propertyChange(evt));
  }

  public StringProperty getTemperatureProperty()
  {
    return temperature;
  }

  public StringProperty getThermometerIdProperty()
  {
    return thermometerId;
  }

  public StringProperty getFilterValueProperty()
  {
    return filterValue;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> getValue());
  }

  public void getValue()
  {
    Temperature t = model.getLastInsertedTemperature(temperature.get());
    if (t != null)
    {
      filterValue.set(t.toString());
    }
    else
    {
      filterValue.set("No data");
    }
  }

  public void updateThermometerId()
  {
    String input = thermometerId.get();
    if (input == null || input.isEmpty() || input.equalsIgnoreCase("All"))
    {
      input = null;
    }
    temperature.set(input);
    if (temperature == null)
    {
      filterValue.set("All");
    }
    else
    {
      filterValue.set(temperature.get());
    }
    thermometerId.set(null);
  }
}
