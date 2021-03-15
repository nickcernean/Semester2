package model;

import java.beans.PropertyChangeListener;

public interface DataModel
{
  double[] getDataValues();
  String getLastUpdateTimeStamp();
  void saveData(double[] data);
  void addListener(String eventName, PropertyChangeListener listener);
}
