import java.util.HashMap;
import java.util.Map;

public abstract class StarSignFactory
{
  private Map<String, StarSign> signMap = new HashMap<String, StarSign>();

  protected abstract StarSign createStarSign(String name);

  public StarSign getStarSign(String name)
  {
  return  signMap.get(name);
  }

}
