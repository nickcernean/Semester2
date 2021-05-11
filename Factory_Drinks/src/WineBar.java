public class WineBar extends Bar
{
  private final String[] drinkTypes = {"Moet", "Cricova", "Milestii Mici"};

  @Override protected Drink makeDrink(String name)
  {
    switch (name)
    {
      case "redWine":
        return new Wine(drinkTypes[3], Wine.RED);
      case "whiteWine":
        return new Wine(drinkTypes[4], Wine.WHITE);
      case "roseWine":
        return new Wine(drinkTypes[5], Wine.ROSE);
    }
    return null;
  }

  @Override public String[] getDrinkTypes()
  {
    return drinkTypes;
  }
}
