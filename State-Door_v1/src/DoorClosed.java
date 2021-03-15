public class DoorClosed extends DoorState
{
  public void click(Door door)
  {
    door.setState(new DoorOpening(door));
  }



}
