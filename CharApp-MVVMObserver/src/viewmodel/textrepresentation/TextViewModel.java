package viewmodel.textrepresentation;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.DataModel;

import java.beans.PropertyChangeEvent;

public class TextViewModel
{
  private StringProperty x;
  private StringProperty y;
  private StringProperty z;
  private StringProperty updateTimeStamp;
  private DataModel model;

  public TextViewModel(DataModel model)
  {
    this.model = model;
    x = new SimpleStringProperty();
    y = new SimpleStringProperty();
    z = new SimpleStringProperty();
    updateTimeStamp = new SimpleStringProperty();
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
      x.setValue(String.valueOf(vals[0]));
      y.setValue(String.valueOf(vals[1]));
      z.setValue(String.valueOf(vals[2]));
    });
  }

  public void updateTextFields()
  {
    double[] vals = model.getDataValues();
    x.setValue("" + vals[0]);
    y.setValue("" + vals[1]);
    z.setValue("" + vals[2]);
    updateTimeStamp.setValue("Last updated:" + model.getLastUpdateTimeStamp());
  }

  public StringProperty xStringProperty()
  {
    return x;
  }

  public StringProperty yStringProperty()
  {
    return y;
  }

  public StringProperty zStringProperty()
  {
    return z;
  }

  public StringProperty labelStringProperty()
  {
    return updateTimeStamp;
  }

  public void saveChanges() throws Exception
  {
    double xAsDouble = Double.parseDouble(x.getValue());
    if (xAsDouble < 0)
    {
      throw new RuntimeException("X cannot be less than zero");
    }
    model.saveData(new double[] {Double.parseDouble(x.getValue()),
        Double.parseDouble(y.getValue()), Double.parseDouble(z.getValue())});
  }
}
