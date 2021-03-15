import java.beans.PropertyChangeEvent;

public class Car4
{
  public Car4(PropertyChangeSubject subject)
  {
    subject.addPropertyChangeListener("GREEN", evt -> reactToGreen(evt));
    subject.addPropertyChangeListener("YELLOW", evt -> reactToRED(evt));
    subject.addPropertyChangeListener("RED", this::reactToYellow);
  }

  public void reactToRED(PropertyChangeEvent event)
  {
    System.out.println("Car stops");

  }

  public void reactToYellow(PropertyChangeEvent event)
  {
    if ("RED".equals(event.getOldValue()))

    {
      System.out.println("Car 4 engine on ");
    }
    else
      System.out.println("Car 4 slows down");
  }

  public void reactToGreen(PropertyChangeEvent event)
  {
    System.out.println("Car 4 drives");
  }
}
