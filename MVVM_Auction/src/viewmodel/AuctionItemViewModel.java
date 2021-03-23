package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import model.AuctionModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AuctionItemViewModel implements PropertyChangeListener
{
  private StringProperty item;
  private StringProperty time;
  private IntegerProperty bid;
  private StringProperty bidder;
  private IntegerProperty currentBid;
  private StringProperty currentBidder;
  private StringProperty error;
  private BooleanProperty end;
  private StringProperty currentBidTitle;
  private AuctionModel model;

  public AuctionItemViewModel(AuctionModel model)
  {
    this.model = model;
    this.item = new SimpleStringProperty();
    this.time = new SimpleStringProperty();
    this.bid = new SimpleIntegerProperty();
    this.bidder = new SimpleStringProperty();
    this.currentBid = new SimpleIntegerProperty();
    this.currentBidder = new SimpleStringProperty();
    this.error = new SimpleStringProperty();
    this.end = new SimpleBooleanProperty();
    this.currentBidTitle = new SimpleStringProperty();

    model.addListener("bid",evt -> propertyChange(evt));
    model.addListener("time",evt -> propertyChange(evt));
    model.addListener("end",evt -> propertyChange(evt));
  }

  public void clear()
  {

  }

  public void bid()
  {
    try
    {
      model.placeBid(bid.get(), bidder.get());
    }
    catch (Exception e)
    {
      error.set(e.getMessage());
    }

  }

  public StringProperty getItemProperty()
  {
    return item;
  }

  public IntegerProperty getBidProperty()
  {
    return bid;
  }

  public StringProperty getBidderProperty()
  {
    return bidder;
  }

  public IntegerProperty getCurrentBidProperty()
  {
    return currentBid;
  }

  public StringProperty getCurrentBidderProperty()
  {
    return currentBidder;
  }

  public StringProperty getErrorProperty()
  {
    return error;
  }

  public StringProperty getTimeProperty()
  {
    return time;
  }

  public BooleanProperty getEndProperty()
  {
    return end;
  }

  public StringProperty getCurrentBidTitleProperty()
  {
    return currentBidTitle;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      time.set(evt.getNewValue() + "");
    });
    Platform.runLater(() -> {
      bidder.set(evt.getNewValue() + "");
    });
//    Platform.runLater(() -> {
//      currentBid.set((Integer) evt.getNewValue());
//    });
  }
}
