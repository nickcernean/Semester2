package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskListModelImpl implements TaskListModel
{
  List<Task> taskList;
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
      this);

  public TaskListModelImpl()
  {
    taskList = new ArrayList<>();
  }

  @Override public void addTask(String owner, String description)
  {
    Task e = new Task(owner, description, calcTimeStamp());
    taskList.add(e);
    propertyChangeSupport.firePropertyChange("TaskAdded", null, e);

  }

  private String calcTimeStamp()
  {
    SimpleDateFormat stf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date now = new Date();
    return stf.format(now);
  }

  @Override public Task getNextTask()
  {
    Task remove = taskList.remove(0);
    propertyChangeSupport.firePropertyChange("TaskRemoved", null, remove);
    return remove;
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(eventName, listener);
  }
}
