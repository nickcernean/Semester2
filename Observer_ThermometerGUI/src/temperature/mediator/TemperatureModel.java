package temperature.mediator;

import observer.PropertyChangeSubject;
import temperature.model.Temperature;

import java.beans.PropertyChangeListener;

public interface TemperatureModel extends PropertyChangeSubject
{
  void addTemperature(String id, double temperature);
  Temperature getLastInsertedTemperature();
  Temperature getLastInsertedTemperature(String id);
  void addPropertyChangeListener(String eventName,
      PropertyChangeListener listener);
  void removePropertyChangeListener(String eventName,
      PropertyChangeListener listener);
}
