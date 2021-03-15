import java.beans.PropertyChangeEvent;

public class Car3
{
  public Car3(PropertyChangeSubject subject)
  {
    subject.addPropertyChangeListener(evt -> reactToChange(evt));
  }

  public void reactToChange(PropertyChangeEvent evt)
  {
    String oldValue = (String) evt.getOldValue();
    String newValue = (String) evt.getNewValue();
    if (newValue.equals("GREEN"))
    {
      System.out.println("Car 3 drives");
    }
    else if (newValue.equals("YELLOW"))
    {
      if ("RED".equals(oldValue))
      {
        System.out.println("Car 3 turns engine on");
      }
      else
      {
        System.out.println("Car 3 slows down");
      }
    }
    else if ("RED".equals(newValue))
    {
      System.out.println("Car 3 stops");
    }

  }
}