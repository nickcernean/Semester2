public class Main
{
  public static void main(String[] args)
  {
    Bridge bridge = new Bridge();

    Thread[] readers = new Thread[5];
    for (int i = 0; i < readers.length; i++)
    {
      BlueCar reader = new BlueCar(bridge);
      readers[i] = new Thread(reader, "BlueCar " + i);
      readers[i].start();
    }

    Thread[] writers = new Thread[2];
    for (int i = 0; i < writers.length; i++)

    {
      RedCar writer = new RedCar(bridge);
      writers[i] = new Thread(writer, "RedCar " + i);
      writers[i].start();
    }
  }
}
