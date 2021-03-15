import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TrafficLight implements PropertyChangeSubject
{
  private String[] lights = {"GREEN", "YELLOW", "RED", "YELLOW"};
  private int count = 2;
  private String currentLight;
  private PropertyChangeSupport support;

  public TrafficLight()
  {
    currentLight = lights[count];
    this.support = new PropertyChangeSupport(this);
  }

  public void start() throws InterruptedException
  {
    String previous = "";
    for (int i = 0; i < 10; i++)
    {
      Thread.sleep(1000);
      count = (++count) % 4;
      previous = currentLight;
      currentLight = lights[count];
      System.out.println("\nLight is" + currentLight);
      support.firePropertyChange("LightChanged", previous, currentLight);
      support.firePropertyChange(currentLight, previous, currentLight);
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

