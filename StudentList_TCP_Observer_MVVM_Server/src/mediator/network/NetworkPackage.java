package mediator.network;

import java.io.Serializable;

public class NetworkPackage implements Serializable
{
  private NetworkType type;

  public NetworkPackage(NetworkType type)
  {
    this.type = type;
  }

  public NetworkType getType()
  {
    return type;
  }
}
