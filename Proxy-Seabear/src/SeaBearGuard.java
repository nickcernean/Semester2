public class SeaBearGuard implements VisitSeaBear
{
  private SeaBear seaBear;

  public SeaBearGuard()
  {
    this.seaBear = new SeaBear();
  }

  @Override public synchronized void view(String personType)
  {
    seaBear.view(personType);
  }

  @Override public synchronized void feed(String personType)
  {
    if (personType.equals("zookeeper"))
    {
      seaBear.feed(personType);
    }
    else throw new IllegalStateException("The zookeeper is allowed feed the Bear");
  }

  @Override public synchronized void pet(String personType)
  {
    if (personType.equals("children"))
    {
      System.out.println("OK");
      seaBear.pet(personType);
    }
    else throw new IllegalStateException("Only child's are allowed to pet the Bear");
  }

}
