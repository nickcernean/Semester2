import java.beans.PropertyChangeListener;

public interface PropertyChangeSubject
{
  public void addProperrtyChangeListener(String eventName,
      PropertyChangeListener listener);
  public void addPropertyChangeListener(PropertyChangeListener listener);
  public void removeProperrtyChangeListener(String eventName,
      PropertyChangeListener listener);
  public void removePropertyChangeListener(PropertyChangeListener listener);

}
