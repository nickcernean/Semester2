package server.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import server.viewmodel.LogViewModel;

public class LogViewController
{
  public TextField inputField;
  @FXML private ListView<String> logList;
  private Region root;
  private LogViewModel logViewModel;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, LogViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.logViewModel = viewModel;
    this.root = root;
    logList.setItems(viewModel.getLogs());
    inputField.textProperty()
        .bindBidirectional(viewModel.getMessageProperty());
  }

  public void reset()
  {
    ////
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML public void onEnter(ActionEvent event)
  {

  }
}
