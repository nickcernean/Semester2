import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Car implements PropertyChangeListener
{
  private String name = "Car 1";

  public Car()
  {

  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    String oldValue = (String) evt.getOldValue();
    String newValue = (String) evt.getNewValue();
    if (newValue.equals("GREEN"))
    {
      System.out.println("Car 1 drives");
    }
    else if (newValue.equals("YELLOW"))
    {
      if ("RED".equals(oldValue))
      {
        System.out.println("Car 1 turns engine on");
      }
      else
      {
        System.out.println("Car 1 slows down");
      }
    }
    else if ("RED".equals(newValue))
    {
      System.out.println("Car 1 stops");
    }

  }
}
