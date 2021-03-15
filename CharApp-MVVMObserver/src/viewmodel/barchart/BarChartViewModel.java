package viewmodel.barchart;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import model.DataModel;

import java.beans.PropertyChangeEvent;

public class BarChartViewModel
{
  private DoubleProperty x;
  private DoubleProperty y;
  private DoubleProperty z;
  private StringProperty timeStamp;
  private DataModel model;

  public BarChartViewModel(DataModel model)
  {
    this.model = model;
    x = new SimpleDoubleProperty();
    y = new SimpleDoubleProperty();
    z = new SimpleDoubleProperty();
    timeStamp = new SimpleStringProperty();
    model.addListener("DataUpdate", this::updateData);
    model.addListener("TimeUpdate", this::updateTime);
  }

  private void updateTime(PropertyChangeEvent evt)
  {
    Platform.runLater(
        () -> timeStamp.setValue("Last updated:" + evt.getNewValue()));
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

  public ObservableValue getX()
  {
    return x;
  }

  public ObservableValue getY()
  {
    return y;
  }

  public ObservableValue getZ()
  {
    return z;
  }

  public StringProperty getTimeStamp()
  {
    return timeStamp;
  }
}
