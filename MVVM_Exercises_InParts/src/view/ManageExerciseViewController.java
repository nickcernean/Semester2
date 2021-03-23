package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import utility.IntStringConverter;
import viewmodel.ManageExerciseViewModel;

public class ManageExerciseViewController
{
  @FXML private TextField sessionField;
  @FXML private TextField numberField;
  @FXML private TextField topicField;
  @FXML private Label headerLabel;
  @FXML private Label errorLabel;
  @FXML private RadioButton completedRadioButton;
  @FXML private Button submitButton;
  private Region root;
  private ManageExerciseViewModel viewModel;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ManageExerciseViewModel viewModel,
      Region root)
  {
    this.viewModel = viewModel;
    this.viewHandler = viewHandler;
    this.root = root;
    headerLabel.textProperty().bind(viewModel.getHeaderProperty());
    Bindings.bindBidirectional(sessionField.textProperty(),
        viewModel.getSessionProperty(), new IntStringConverter());
    Bindings.bindBidirectional(numberField.textProperty(),
        viewModel.getNumberProperty(), new IntStringConverter());
    completedRadioButton.selectedProperty()
        .bindBidirectional(viewModel.getCompletedProperty());
    topicField.textProperty().bindBidirectional(viewModel.getTopicProperty());
    errorLabel.textProperty().bind(viewModel.getErrorProperty());
    viewModel.getEditableProperty().addListener(
        (obs, oldV, newV) -> completedRadioButton.setDisable(!newV));
    topicField.editableProperty().bind(viewModel.getEditableProperty());
    numberField.editableProperty().bind(viewModel.getEditableProperty());
    sessionField.editableProperty().bind(viewModel.getEditableProperty());
    reset();
  }

  public void reset()
  {
    viewModel.reset();
  }

  @FXML private void onEnter(ActionEvent event)
  {
    if (event.getTarget() == sessionField)
    {
      numberField.requestFocus();
    }
    else if (event.getTarget() == numberField)
    {
      topicField.requestFocus();
    }
    else if (event.getTarget() == topicField)
    {
      submitButton.requestFocus();
    }

  }

  @FXML private void submitButton()
  {
    boolean ok = viewModel.accept();
    if (ok)
    {
      viewHandler.openView("list");
    }
  }

  @FXML private void cancelButton()
  {
    viewHandler.openView("list");
  }

  public Region getRoot()
  {
    return root;
  }
}
