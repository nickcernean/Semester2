public class DoorClosing extends DoorState
{
  private boolean complete;
  private Thread motor;

  public DoorClosing(Door door)
  {
    complete = false;
    motor = new Thread(() -> {
      try
      {
        Thread.sleep(5000);
        complete(door);
      }
      catch (InterruptedException e)
      {
        System.out.println("Motor interupted (closing)");
      }
    });
    motor.start();
  }

 @Override public synchronized void complete(Door door)
  {
    if (!complete)
    {
      door.setState(new DoorClosed());
      complete = true;
      System.out.println(" Motor ended (closed)");
    }
  }

  @Override public synchronized void click(Door door)
  {
    if (!complete)
    {
      motor.interrupt();
      door.setState(new DoorOpening(door));
      complete = true;
    }
  }

}
