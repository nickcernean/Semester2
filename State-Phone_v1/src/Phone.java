public class Phone
{
  private AlertState alertState;

  public Phone()
  {
    this.alertState = new SilentState();
  }

  public void clickSoundButton()
  {
    alertState.click(this);
  }

  public void setState(AlertState state)
  {

    this.alertState = state;
  }

  public void receive(String message)
  {
    System.out.println(alertState.alert() + " " + message);
  }
}
