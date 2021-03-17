import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BlindBirdWatcher implements PropertyChangeListener
{
  private Bird bird;

  public BlindBirdWatcher()
  {
    bird = new Bird();

  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

    System.out.println(evt.getNewValue());
    System.out.println("Blind Bird Watcher: " + "Would you look at that!");

  }
}
