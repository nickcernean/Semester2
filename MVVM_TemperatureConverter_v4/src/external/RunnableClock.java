package external;

import javafx.application.Platform;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RunnableClock implements Runnable, PropertyChangeSubject
{
  private DateTimeFormatter timeFormatter;

  private PropertyChangeSupport support;

  public RunnableClock()
  {

    this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    this.support = new PropertyChangeSupport(this);
  }

  @Override public void run()
  {
    while (true)
    {
      LocalTime time = LocalTime.now();
      String timeString = time.format(timeFormatter);
      sleep();
      Platform.runLater(
          () -> support.firePropertyChange("TimeString", null, timeString));
      new Thread(()->support.firePropertyChange("TimeString",null,timeString)).start();

      System.out.println(timeString);}
  }

  private void sleep()
  {
    try
    {

      Thread.sleep(1000);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addPropertyChangeListener(String eventName,
      PropertyChangeListener listener)
  {
    if (eventName == null || "".equals(eventName))
    {
      addPropertyChangeListener(listener);
    }
    else
    {
      support.addPropertyChangeListener(eventName, listener);
    }

  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String eventName,
      PropertyChangeListener listener)
  {
    if (eventName == null || "".equals(eventName))
    {
      removePropertyChangeListener(listener);
    }
    else
    {
      support.removePropertyChangeListener(eventName, listener);
    }
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }
}
