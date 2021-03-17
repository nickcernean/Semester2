public class Test
{
  public static void main(String[] args)
  {
    Bird bird = new Bird();
    BirdWatcher birdWatcher = new BirdWatcher();
    BlindBirdWatcher blindBirdWatcher = new BlindBirdWatcher();
    DeafBirdWatcher deafBirdWatcher = new DeafBirdWatcher();

    bird.addPropertyChangeListener("Whistle", birdWatcher);
    bird.addPropertyChangeListener("Flashes", birdWatcher);
    bird.addPropertyChangeListener("Flashes", deafBirdWatcher);
    bird.addPropertyChangeListener("Whistle", blindBirdWatcher);


    while (true)
    {
      bird.flashesWings();
      bird.peacockWhistles();
      try
      {
        Thread.sleep(5000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }

    }
  }
}
