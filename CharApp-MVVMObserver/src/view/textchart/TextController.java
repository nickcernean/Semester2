package view.textchart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import viewmodel.textrepresentation.TextViewModel;

public class TextController
{
  @FXML Label eventLabel;
  @FXML TextField xtextField;
  @FXML TextField ytextField;
  @FXML TextField ztextField;
  private TextViewModel viewModel;

  public TextController()
  {
  }

  public void init(TextViewModel viewModel)
  {
    this.viewModel = viewModel;
    eventLabel.textProperty().bind(viewModel.labelStringProperty());
    xtextField.textProperty().bindBidirectional(viewModel.xStringProperty());
    ytextField.textProperty().bindBidirectional(viewModel.yStringProperty());
    ztextField.textProperty().bindBidirectional(viewModel.zStringProperty());
  }



  public void onSaveButton(ActionEvent event)
  {
    try
    {
      viewModel.saveChanges();
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
     }
  }
}
