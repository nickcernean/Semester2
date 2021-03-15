import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Car2
{
  public Car2(PropertyChangeSubject subject)
  {
    PropertyChangeListener listener= new PropertyChangeListener()
    {
      @Override public void propertyChange(PropertyChangeEvent evt)
      {
        reactToChange(evt);
      }
    };
    subject.addPropertyChangeListener(listener);
  }

  public void reactToChange(PropertyChangeEvent evt)
  {
    String oldValue = (String) evt.getOldValue();
    String newValue = (String) evt.getNewValue();
    if (newValue.equals("GREEN"))
    {
      System.out.println("Car 2 drives");
    }
    else if (newValue.equals("YELLOW"))
    {
      if ("RED".equals(oldValue))
      {
        System.out.println("Car 2 turns engine on");
      }
      else
      {
        System.out.println("Car 2 slows down");
      }
    }
    else if ("RED".equals(newValue))
    {
      System.out.println("Car 2 stops");
    }

  }
}