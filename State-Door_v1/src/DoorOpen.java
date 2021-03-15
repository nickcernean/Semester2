public class DoorOpen extends DoorState
{
  private Thread timer;
  private boolean completed;

  public DoorOpen(Door door)
  {
    completed = false;
    timer = new Thread(() -> {
      try
      {
        Thread.sleep(10000);
        DoorOpen.this.complete(door);
      }
      catch (InterruptedException e)
      {
        System.out.println("The timer completed (closing)");
      }
    });
    timer.start();
  }

  public synchronized void timerComplete(Door door)
  {
    if (!completed)
    {
      System.out.println("Timer ended (closing)");
      door.setState(new DoorClosing(door));
      completed = true;
    }
  }

  @Override public synchronized void click(Door door)
  {
    if (!completed)
    {
      timer.interrupt();
      door.setState(new DoorStayOpen());
      completed = true;
    }
  }

}
