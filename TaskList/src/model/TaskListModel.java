package model;

import java.beans.PropertyChangeListener;

public interface TaskListModel
{
  void addTask(String owner, String description);
  Task getNextTask();
  void addListener(String eventName, PropertyChangeListener listener);
}
