package mediator;

import mediator.network.NetworkPackage;
import mediator.network.NetworkType;
import mediator.network.StringPackage;
import mediator.network.StudentPackage;

import java.io.ObjectInputStream;

public class ClientReceiver implements Runnable
{
  private StudentListClient client;
  private ObjectInputStream in;

  public ClientReceiver(StudentListClient client, ObjectInputStream in)
  {
    this.client = client;
    this.in = in;
  }

  @Override public void run()
  {
    boolean running = true;
    while (running)
    {
      try
      {
        NetworkPackage replyMessage = (NetworkPackage) in.readObject();
        if (replyMessage.getType() == NetworkType.STUDENT) // search reply
        {
           if (replyMessage instanceof StudentPackage)
          {
            client.replyReceived(
                ((StudentPackage) replyMessage).getStudent());
          }
        }
        else if (replyMessage.getType() == NetworkType.BROADCAST) // broadcast
        {
          String reply = ((StringPackage) replyMessage).getValue();
          client.broadcastReceived(reply);
        }
        else // add reply
        {
          String reply = ((StringPackage) replyMessage).getValue();
          if (replyMessage.getType() == NetworkType.NAME)
          {
            client.replyReceived(reply);
          }
          else if (replyMessage.getType() == NetworkType.ERROR)
          {
            client.replyError(reply);
          }
        }
      }
      catch (Exception e)
      {
        running = false;
        System.out.println("Error: " + e.getMessage());
      }
    }
  }

}
