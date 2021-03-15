package shared;

import java.io.Serializable;

public class Request implements Serializable
{
  private int id;
  private String message;

  public Request(int id, String message)
  {
    this.id = id;
    this.message = message;
  }

  public int getID()
  {
    return id;
  }

  public String getMessage()
  {
    return message;
  }
}
