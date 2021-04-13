package server;

import java.util.ArrayList;

public class TaskList
{
  private ArrayList<Task> tasks;

  public TaskList()
  {
    this.tasks = new ArrayList<>();
  }

  public void addTask(Task task)
  {
    tasks.add(task);
  }

  public Task getAndRemoveNext()
  {
    if (tasks.size() > 0)
    {
      return tasks.remove(0);
    }
    return null;
  }

  public int size()
  {
    return tasks.size();
  }

  @Override public String toString()
  {
    return "TaskList: " + tasks;
  }
}
