package model;

public interface DataModel
{
  double[] getDataValues();
  String getLastUpdateTimeStamp();
  void saveData(double[] data);
}
