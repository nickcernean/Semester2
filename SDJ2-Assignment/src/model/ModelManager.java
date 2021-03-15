package model;

public interface ModelManager
{
  int getPower();
  float getTemperature0();
  float getTemperature1();
  float getTemperature2();
  void turnUp();
  void turnDown();
  float getCriticalHigh();
  float getCriticalLow();
}
