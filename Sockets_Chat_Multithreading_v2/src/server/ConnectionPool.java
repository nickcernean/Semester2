package server;

import shared.Message;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool
{
  private List<ServerSocketHandler> conns = new ArrayList<>();

  public void addConenction(ServerSocketHandler csh)
  {
    conns.add(csh);
  }

  public void broadcast(Message msg)
  {
    for (ServerSocketHandler conn : conns)
    {
      if (!conn.getClientName().equals(msg.getUser()))
      {
        conn.sendMessageToClient(msg);
      }
    }
  }

  public void removeConnection(ServerSocketHandler serverSocketHandler)
  {
    conns.remove(serverSocketHandler);
  }
}
