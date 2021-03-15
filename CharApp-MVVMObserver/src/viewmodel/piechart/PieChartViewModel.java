package viewmodel.piechart;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.DataModel;

import java.beans.PropertyChangeEvent;

public class PieChartViewModel
{
  private DoubleProperty x;
  private DoubleProperty y;
  private DoubleProperty z;
  private StringProperty updateTimeStamp;
  private DataModel model;

  public PieChartViewModel(DataModel model)
  {
    this.model = model;
    x = new SimpleDoubleProperty();
    y = new SimpleDoubleProperty();
    z = new SimpleDoubleProperty();
    updateTimeStamp = new SimpleStringProperty("Last update: ");
    model.addListener("DataUpdate", this::updateData);
    model.addListener("TimeUpdate", this::updateTime);

  }

  private void updateTime(PropertyChangeEvent evt)
  {
    Platform.runLater(
        () -> updateTimeStamp.setValue("Last updated:" + evt.getNewValue()));
  }

  private void updateData(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      double[] vals = (double[]) evt.getNewValue();
      x.setValue(vals[0]);
      y.setValue(vals[1]);
      z.setValue(vals[2]);
    });
  }

  public DoubleProperty xProperty()
  {
    return x;
  }

  public DoubleProperty yProperty()
  {
    return y;
  }

  public DoubleProperty zProperty()
  {
    return z;
  }

  public StringProperty updateTimeStampProperty()
  {
    return updateTimeStamp;
  }
}

