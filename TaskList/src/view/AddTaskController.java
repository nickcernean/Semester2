package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import viewmodel.AddTaskViewModel;
import viewmodel.TaskListViewModel;
import viewmodel.ViewModelProvider;

public class AddTaskController
{
  private AddTaskViewModel addTaskViewModel;
  @FXML private TextField ownerTextField;
  @FXML private TextField descriptionTextField;

  public void init(AddTaskViewModel taskViewModel)
  {
    this.addTaskViewModel = taskViewModel;
    ownerTextField.textProperty()
        .bindBidirectional(addTaskViewModel.getOwnerProperty());
    descriptionTextField.textProperty()
        .bindBidirectional(addTaskViewModel.getDescriptionProperty());

  }

  public void onAddTask(ActionEvent event)
  {
    addTaskViewModel.addTask();
  }

  public void onBack(ActionEvent event)
  {
    addTaskViewModel.goBack();
  }
}
