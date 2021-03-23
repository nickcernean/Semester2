package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewmodel.ListExerciseViewModel;
import viewmodel.SimpleExerciseViewModel;

public class ListExercisesViewController
{
  @FXML private TableView<SimpleExerciseViewModel> exercisesTable;
  @FXML private TableColumn<SimpleExerciseViewModel, String> numberColumn;
  @FXML private TableColumn<SimpleExerciseViewModel, String> topicColumn;
  @FXML private TableColumn<SimpleExerciseViewModel, Boolean> completedColumn;
  @FXML private Label errorLabel;

  private ListExerciseViewModel exerciseViewModel;
  private ViewHandler viewHandler;
  private Region root;

  public void init(ViewHandler viewHandler, ListExerciseViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.exerciseViewModel = viewModel;
    this.root = root;
    numberColumn.setCellValueFactory(
        cellData -> cellData.getValue().getNumberProperty());
    topicColumn.setCellValueFactory(
        cellData -> cellData.getValue().getTopicProperty());
    completedColumn.setCellValueFactory(
        cellData -> cellData.getValue().getCompletedProperty());

    exercisesTable.setItems(viewModel.getAll());

    exercisesTable.getSelectionModel().selectedItemProperty()
        .addListener((obs, oldV, newV) -> viewModel.setSelected(newV));
    errorLabel.textProperty().bind(viewModel.getErrorProperty());
  }

  public void reset()
  {
    exerciseViewModel.clear();
    exercisesTable.getSelectionModel().clearSelection();
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void addEditButton()
  {
    exerciseViewModel.addEdit();
    viewHandler.openView("manage");
  }

  @FXML private void removeButton()
  {
    boolean ok = exerciseViewModel.remove();
    if (ok)
    {
      viewHandler.openView("manage");
    }
  }
}
