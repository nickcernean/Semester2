public class SeaBear implements VisitSeaBear
{
  public SeaBear()
  {

  }

  @Override public synchronized void view(String personType)
  {
    System.out.println("Bear is being viewed by "+ personType);
  }

  @Override public synchronized void feed(String personType)
  {
    System.out.println("Bear is fed by "+ personType);
  }

  @Override public synchronized void pet(String personType)
  {
    System.out.println("Bear is pet by "+personType);
  }
}
