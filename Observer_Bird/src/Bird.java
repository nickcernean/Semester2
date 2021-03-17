import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Bird implements PropertyChangeSubject
{

  private PropertyChangeSupport support;

  public Bird()
  {
    support = new PropertyChangeSupport(this);
  }

  public void peacockWhistles()
  {
     support.firePropertyChange("Whistle", null, "Peacock whistles");
  }

  public void flashesWings()
  {
     support
        .firePropertyChange("Flashes", null, "Peacock flashes wings");
  }

  @Override public void addPropertyChangeListener(String eventName,
      PropertyChangeListener listener)
  {
    if (eventName == null || "".equals(eventName))
    {
      support.addPropertyChangeListener(listener);
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
      support.removePropertyChangeListener(listener);
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
