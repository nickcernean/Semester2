package view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import viewmodel.LogViewModel;

public class LogViewController
{
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
  }

  public void reset()
  {
    logViewModel.clear();
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void onBack()
  {
    viewHandler.openView("convert");
  }
}
