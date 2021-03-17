package temperature.mediator;

import temperature.model.Temperature;
import temperature.model.TemperatureList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TemperatureModelManager implements TemperatureModel
{
  private TemperatureList temperatureList;
  private PropertyChangeSupport property;

  public TemperatureModelManager()
  {
    temperatureList = new TemperatureList();
    property = new PropertyChangeSupport(this);
  }

  @Override public synchronized void addTemperature(String id, double value)
  {
    Temperature temperature = new Temperature(id, value);
    Temperature old = getLastInsertedTemperature(id);
    this.temperatureList.addTemperature(temperature);
    if (old != null && old.getValue() != temperature.getValue())
    {
      System.out.println("--> new=" + temperature + " (old=" + old + ")");
      property.firePropertyChange("Update",old,temperature);
    }
    else if (old == null)
    {
      System.out.println("--> new=" + temperature + " (old=" + old + ")");
      property.firePropertyChange("Update",null,temperature);
    }
  }

  @Override public synchronized Temperature getLastInsertedTemperature()
  {
    return temperatureList.getLastTemperature(null);
  }

  @Override public synchronized Temperature getLastInsertedTemperature(
      String id)
  {
    return temperatureList.getLastTemperature(id);
  }

  @Override public void addPropertyChangeListener(String eventName,
      PropertyChangeListener listener)
  {
    if (eventName == null)
    {
      property.addPropertyChangeListener(listener);
    }
    else
    {
      property.addPropertyChangeListener(eventName, listener);
    }

  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String eventName,
      PropertyChangeListener listener)
  {
    if (eventName == null)
    {
      property.removePropertyChangeListener(listener);
    }
    else
    {
      property.removePropertyChangeListener(eventName, listener);
    }

  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }

  // and maybe other methods...
}
