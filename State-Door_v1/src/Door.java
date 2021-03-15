public class Door
{
  private DoorState state;

  public Door()
  {
    this.state = new DoorClosed();
  }

  public void click()
  {
    state.click(this);
    state.status();
  }

  public void setState(DoorState state)
  {
    this.state = state;
    state.status();
  }

  public String status()
  {
    return state.status();
  }

}
