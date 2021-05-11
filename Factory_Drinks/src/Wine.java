public class Wine extends Drink
{
  private String type;
  public static final String RED = "red";
  public static final String WHITE = "white";
  public static final String ROSE = "rose";

  public Wine(String name, String type)
  {
    super(name,getDescription(type));
  }

  private static String getDescription(String type)
  {
    type = type.equals(RED) ?
        "In glas, slightly chilled" :
        type.equals(WHITE) ?
            "In glass,cold" :
            type.equals(ROSE) ? "In glass,cold" : "NOT A WINE";
    return type;
  }
}
