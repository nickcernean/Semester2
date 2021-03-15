package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.TaskListModel;
import view.ViewHandler;

public class AddTaskViewModel
{
  private StringProperty owner;
  private StringProperty description;
  private TaskListModel model;
  private ViewHandler viewHandler;

  public AddTaskViewModel(TaskListModel model, ViewHandler viewHandler)
  {
    this.model = model;
    this.viewHandler = viewHandler;
    owner = new SimpleStringProperty();
    description = new SimpleStringProperty();
  }

  public void addTask()
  {
    if ("".equals(owner.getValue()))
      return;
    if ("".equals(description.getValue()))
      return;
    model.addTask(owner.getValue(), description.getValue());
    owner.setValue("");
    description.setValue("");
  }

  public void goBack()
  {
    viewHandler.opentNexTaskView();
  }

  public StringProperty getOwnerProperty()
  {
    return owner;
  }

  public StringProperty getDescriptionProperty()
  {
    return description;
  }
}
