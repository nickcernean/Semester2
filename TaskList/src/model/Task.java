package model;

public class Task
{
  private String owner;
  private String description;
  private String timeStamp;

  public Task(String owner, String description, String timeStamp)
  {
    this.owner = owner;
    this.description = description;
    this.timeStamp = timeStamp;
  }

  public String getDescription()
  {
    return description;
  }

  public String getOwner()
  {
    return owner;
  }

  public String getTimeStamp()
  {
    return timeStamp;
  }

}
