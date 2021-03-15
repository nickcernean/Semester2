import model.TaskList;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TaskListCommunicationThreadHandler implements Runnable
{
  private DataInputStream in;
  private DataOutputStream out;
  private String ip;
  private TaskList taskList;

  public TaskListCommunicationThreadHandler(Socket socket, TaskList taskList)
      throws IOException
  {
    this.taskList = taskList;
    this.in = new DataInputStream(socket.getInputStream());
    this.out = new DataOutputStream(socket.getOutputStream());
  }

  @Override public void run()
  {
    String task = null;
    long tasktime = 0;
    String data = null;
    try
    {
      data = in.readUTF();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    while (true)
    {
      try
      {
        data = in.readUTF();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }

      if (data != null)//isBlank() producing NULLPointer
      {
        switch (data)
        {
          case "ADD":
            try
            {
              task = in.readUTF();
              tasktime = in.readLong();
              out.writeUTF("ADD");

            }
            catch (IOException e)
            {
              e.printStackTrace();
            }
            break;
          case "GET":
            try
            {
              if (taskList.size() == 0)
              {
                out.writeUTF("EXIT");
              }
              out.writeUTF(taskList.getAndRemoveNextTask().getText());
              out.writeLong(taskList.getAndRemoveNextTask().getEstimatedTime());
            }
            catch (IOException e)
            {
              e.printStackTrace();
            }

            break;
          case "SIZE":
            try
            {
              out.writeInt(taskList.size());
            }
            catch (IOException e)
            {
              e.printStackTrace();
            }
            break;
        }
      }

    }

  }
}
