import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DeafBirdWatcher implements PropertyChangeListener
{
  private Bird bird;

  public DeafBirdWatcher()
  {
    bird = new Bird();

  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

    System.out.println(evt.getNewValue());
    System.out.println("Deaf Bird Watcher: " + "Would you hear that!");

  }
}
