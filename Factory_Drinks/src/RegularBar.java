public class RegularBar extends Bar
{
  private final String[] drinkTypes = {"Carlsberg", "Tuborg", "Heineken", "Moet",
      "Cricova", "Milestii Mici"};

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
      case "dadBeer":
        return new Beer(drinkTypes[0]);
      case "sonBeer":
        return new Beer(drinkTypes[1]);
      case "momBeer":
        return new Beer(drinkTypes[2]);
    }
    return null;
  }

  @Override public String[] getDrinkTypes()
  {
    return drinkTypes;
  }
}
