package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DataModelManager implements DataModel
{
  private double x;
  private double y;
  private double z;
  private String lastUpdate;
  private Random random = new Random();

  @Override public double[] getDataValues()
  {
    return new double[] {x, y, z};
  }

  @Override public String getLastUpdateTimeStamp()
  {
    return lastUpdate;
  }

  @Override public void saveData(double[] data)
  {
    x = data[0];
    y = data[1];
    z = data[2];
    calTimeStamp();
  }

  public void recalculateData()
  {
    int first = random.nextInt(100) + 1;
    int second = random.nextInt(100) + 1;
    int bottom = Math.min(first, second);
    int top = Math.max(first, second);
    x = bottom;
    y = top - bottom;
    z = 100 - top;
    calTimeStamp();
  }

  public void calTimeStamp()
  {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    Date now = new Date();
    String stringDate = simpleDateFormat.format(now);
    System.out.println(stringDate);
    lastUpdate = stringDate;
  }

}
