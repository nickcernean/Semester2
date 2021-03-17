import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BirdWatcher implements PropertyChangeListener
{
  private Bird bird;

  public BirdWatcher()
  {
    bird = new Bird();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

    System.out.println(evt.getNewValue());
    System.out.println(
        "Bird Watcher: " + "Would you look at that! and Would you hear that!");

  }
}
