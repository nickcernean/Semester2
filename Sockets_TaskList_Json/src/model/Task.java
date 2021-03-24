package model;

public class Task
{
  private String text;
  private long estimatedTime;

  public Task(String text, long estimatedTime)
  {
    this.text = text;
    this.estimatedTime = estimatedTime;
  }

  public synchronized String getText()
  {
    return text;
  }

  public synchronized long  getEstimatedTime()
  {
    return estimatedTime;
  }

  public synchronized void setEstimatedTime(long estimatedTime)
  {
    this.estimatedTime = estimatedTime;
  }

   public synchronized String toString()
  {
    return text +": "+ estimatedTime;
  }
}
