package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Task;
import viewmodel.TaskListViewModel;

public class TaskListViewController
{

  @FXML private TableView<Task> tableView;
  @FXML private TableColumn<String, Task> ownerColumn;
  @FXML private TableColumn<String, Task> descColumn;
  @FXML private TableColumn<String, Task> dateColumn;
  private TaskListViewModel model;

  public void init(TaskListViewModel model)
  {
    this.model = model;
    tableView.setItems(model.getTaskList());
    ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
    descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("timeStamp"));

  }

  public void openAddView(javafx.event.ActionEvent event)
  {
    model.openAddView();
  }

  public void openGetNextTaskView(javafx.event.ActionEvent event)
  {
    model.openNextTaskView();
  }
}
