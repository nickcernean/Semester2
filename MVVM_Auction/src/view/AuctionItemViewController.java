package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.AuctionItemViewModel;

public class AuctionItemViewController
{
  @FXML private Label itemLabel;
  @FXML private Label timeLabel;
  @FXML private Label currentBidTitle;
  @FXML private Label currentBidLabel;
  @FXML private Label currentBidderLabel;
  @FXML private TextField bidField;
  @FXML private Label errorLabel;
  private Region root;
  private AuctionItemViewModel viewModel;
  private ViewHandler viewHandler;
  public void init(ViewHandler viewHandler, AuctionItemViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    //To DO the bindings
    itemLabel.textProperty().bind(viewModel.getItemProperty());
    timeLabel.textProperty().bind(viewModel.getTimeProperty());

    currentBidLabel.textProperty()
        .bind(viewModel.getCurrentBidProperty().asString());

    currentBidderLabel.textProperty()
        .bind(viewModel.getCurrentBidderProperty());

//    currentBidLabel.textProperty().bind(new StringIntegerConverter(1).toString(viewModel.getCurrentBidProperty()));

    bidField.textProperty().bindBidirectional(viewModel.getBidderProperty());
    errorLabel.textProperty().bind(viewModel.getErrorProperty());
    currentBidTitle.textProperty().bind(viewModel.getCurrentBidTitleProperty());
  }

  public void reset()
  {

  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void bidOnAction()
  {
    viewModel.bid();
  }
}
