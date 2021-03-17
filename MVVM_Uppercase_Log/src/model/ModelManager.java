package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
  private Converter converter;
  private PropertyChangeSupport changeSupport;

  public ModelManager()
  {
    this.converter = new Converter();
    this.changeSupport = new PropertyChangeSupport(this);
  }

  @Override public String convert(String source)
  {
    String reply = converter.toUpperCase(source);
    addLog("Converting: " + source);
    return reply;
  }

  @Override public void addLog(String log)
  {
    String logValue = converter.getLogSize() + ": " + log;
    converter.addLog(logValue);
    changeSupport.firePropertyChange(logValue, null, logValue);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {

    changeSupport.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    changeSupport.removePropertyChangeListener(listener);
  }
}
