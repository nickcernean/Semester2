public class ZooVisitator
{
  private VisitSeaBear visitSeaBear;
  private String personType;

  public ZooVisitator(String personType)
  {
    this.visitSeaBear = new SeaBearGuard();
    this.personType = personType;
  }
  void view()
  {
    visitSeaBear.view(personType);
  }

  void feed()
  {
    visitSeaBear.feed(personType);
  }

  void pet()
  {
    visitSeaBear.pet(personType);
  }
}

