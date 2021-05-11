public abstract class Drink
{
  private String name;
  private String description;

  public Drink(String name, String descripton)
  {
    this.name = name;
    setDescription(descripton);
  }

  public String getName()
  {
    return name;
  }

  public String getDescription()
  {
    return description;
  }

  public  void setDescription(String description)
  {
    this.description = description;
  }

  @Override public String toString()
  {
    return "Drink: " + name + " " + description;
  }
}
