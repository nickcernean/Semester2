package model;

import utility.observer.subject.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class TaskList implements UnnamedPropertyChangeSubject
{
  private ArrayList<Task> tasks;
  private PropertyChangeSupport property;

  public TaskList()
  {
    this.tasks = new ArrayList<>();
    this.property = new PropertyChangeSupport(this);
  }

  public synchronized void add(Task task)
  {
    tasks.add(task);
    property.firePropertyChange("ADD", null, tasks.add(task));
  }

  public synchronized Task getAndRemoveNextTask()
  {
    if (tasks.size() > 0)
    {
      property.firePropertyChange("REMOVE", tasks.get(0), tasks.remove(0));
      return tasks.remove(0);
    }
    return null;
  }

  public synchronized int size()
  {
    property.firePropertyChange("SIZE", tasks.size(), tasks.size());
    return tasks.size();
  }

  public synchronized String toString()
  {
    return "Tasks: " + tasks;
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }
}
