public class DoorOpening extends DoorState
{
  private Thread motor;
  private boolean completed;

  public DoorOpening(Door door)
  {
    completed = false;
    motor = new Thread(() -> {
      try
      {
        Thread.sleep(5000);
        DoorOpening.this.complete(door);
      }
      catch (InterruptedException e)
      {
        System.out.println("Motor interupted (opening)");
      }
    });
    motor.start();
  }

  public synchronized void complete(Door door)
  {
    if (!completed)
    {
      System.out.println("Motor ended (opening)");
      door.setState(new DoorOpen(door));
      completed = true;
    }
  }

  @Override public synchronized void click(Door door)
  {
    if (!completed)
    {
      motor.interrupt();
      door.setState(new DoorClosing(door));
      completed = true;
    }
  }
}
