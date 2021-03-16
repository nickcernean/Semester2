package external;
import java.beans.PropertyChangeListener;

public interface PropertyChangeSubject
{
  public void addPropertyChangeListener(String eventName,PropertyChangeListener listener);
  public void addPropertyChangeListener(PropertyChangeListener listener);
  public void removePropertyChangeListener(String eventName,PropertyChangeListener listener);
  public void removePropertyChangeListener(PropertyChangeListener listener);

}
