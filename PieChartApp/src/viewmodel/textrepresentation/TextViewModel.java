package viewmodel.textrepresentation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.DataModel;

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
