package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Task;
import model.TaskListModel;
import view.ViewHandler;

import java.beans.PropertyChangeEvent;

public class TaskListViewModel
{
  private ObservableList<Task> taskList;
  private ViewHandler viewHandler;

  public TaskListViewModel(TaskListModel model, ViewHandler viewHandler)
  {
    this.viewHandler = viewHandler;
    taskList = FXCollections.observableArrayList();
    model.addListener("TaskAdded", this::taskAdded);
    model.addListener("TaskRemoved", this::taskRemoved);
  }

  private void taskRemoved(PropertyChangeEvent event)
  {
    taskList.remove((Task) event.getNewValue());
  }

  private void taskAdded(PropertyChangeEvent event)
  {
    taskList.add((Task) event.getNewValue());
  }

  public ObservableList<Task> getTaskList()
  {
    return taskList;
  }

  public void openAddView()
  {
    viewHandler.openAddView();
  }
  public void openNextTaskView()
  {
    viewHandler.opentNexTaskView();
  }
}
