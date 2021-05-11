public class ZodiacAndChineseStarSignFactory extends StarSignFactory
{
  @Override public StarSign createStarSign(String name)
  {
    return new ZodiacSign(name);
  }
}
