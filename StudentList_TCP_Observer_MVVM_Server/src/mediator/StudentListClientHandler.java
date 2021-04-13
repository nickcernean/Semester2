package mediator;

import mediator.network.NetworkPackage;
import mediator.network.NetworkType;
import mediator.network.StringPackage;
import mediator.network.StudentPackage;
import model.Model;
import model.Student;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class StudentListClientHandler
    implements Runnable, LocalListener<Student, Student>
{
  private Socket socket;
  private ObjectInputStream in;
  private ObjectOutputStream out;
  private Model model;
  private boolean running;
  private String ip;

  public StudentListClientHandler(Socket socket, Model model) throws IOException
  {
    this.model = model;
    this.model.addListener(this);
    this.socket = socket;
    this.ip = socket.getInetAddress().getHostName();
    this.out = new ObjectOutputStream(socket.getOutputStream());
    this.in = new ObjectInputStream(socket.getInputStream());
    System.out.println(
        "Connected to client: " + socket.getInetAddress().getHostName());
  }

  @Override public void run()
  {
    running = true;

    NetworkPackage requestPackage = null;
    NetworkPackage replyPackage = null;
    Student student = null;
    while (running)
    {
      try
      {
        requestPackage = (NetworkPackage) in.readObject();
        System.out.println("Client> " + requestPackage);
        switch (requestPackage.getType())
        {
          case NAME:
            StringPackage strnp = (StringPackage) requestPackage;
            student = model.getStudentByName(strnp.getValue());
            replyPackage = new StudentPackage(student);
            break;
          case NUMBER:
            StringPackage strsp = (StringPackage) requestPackage;
            student = model.getStudentByStudyNumber(strsp.getValue());
            replyPackage = new StudentPackage(student);
            break;
          case STUDENT:
            try
            {
              StudentPackage stp = (StudentPackage) requestPackage;
              model.addStudent(stp.getStudent());
              replyPackage = new StringPackage(NetworkType.NAME,
                  stp.getStudent().getName());
            }
            catch (Exception e)
            {
              replyPackage = new StringPackage(NetworkType.ERROR,
                  e.getMessage());
            }
            break;
          case ERROR:
            replyPackage = requestPackage;
            break;
        }
        System.out.println("Server> " + replyPackage);
        out.writeObject(replyPackage);
      }
      catch (Exception e)
      {
        System.out.println("Error for client " + ip + ": " + e.getMessage());
        stop();
      }
    }
    System.out.println("Closing connection to client " + ip);
    stop();
  }

  public void stop()
  {
    running = false;
    try
    {
      in.close();
      out.close();
      socket.close();
    }
    catch (IOException e)
    {
      //
    }
  }

  @Override public void propertyChange(ObserverEvent<Student, Student> event)
  {
    try
    {
      String message = "" + event.getValue2();
      NetworkPackage broadcastPackage = new StringPackage(NetworkType.BROADCAST,
          message);
      out.writeObject(broadcastPackage);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.out.println("Error: " + e.getMessage());
    }
  }
}
