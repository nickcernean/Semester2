package viewmodel;

import model.TaskListModel;
import view.ViewHandler;

public class ViewModelProvider
{

  private TaskListViewModel taskListViewModel;
  private TaskListModel model;
  private AddTaskViewModel addTaskViewModel;

  public ViewModelProvider(TaskListModel model)
  {
    this.model = model;

  }

  public void instatiateViewModels(ViewHandler viewHandler)
  {
    this.taskListViewModel = new TaskListViewModel(model, viewHandler);
    this.addTaskViewModel = new AddTaskViewModel(model, viewHandler);
  }

  public TaskListViewModel getTaskListViewModel()
  {
    return taskListViewModel;
  }

  public AddTaskViewModel getAddTaskViewModel()
  {
    return addTaskViewModel;
  }
}
