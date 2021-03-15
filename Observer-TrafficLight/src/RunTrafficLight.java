public class RunTrafficLight
{
  public static void main(String[] args)
  {
    TrafficLight trafficLight = new TrafficLight();
    Car car = new Car();
    Car2 car2 = new Car2(trafficLight);
    Car3 car3 = new Car3(trafficLight);
    Car4 car4=new Car4(trafficLight);
    trafficLight.addPropertyChangeListener("LightChanged",car);
    try
    {
      trafficLight.start();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }

  }
}
