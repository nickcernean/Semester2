package mediator;

import mediator.network.NetworkType;
import mediator.network.StringPackage;
import mediator.network.StudentPackage;
import model.Student;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class StudentListClient implements ClientModel
{
  public static final String HOST = "localhost";
  public static final int PORT = 9876;

  private String host;
  private int port;
  private Socket socket;
  private ObjectInputStream in;
  private ObjectOutputStream out;
  private Student studentReply;
  private String stringReply;
  private String errorString;
  private PropertyChangeHandler<String, String> property;

  public StudentListClient(String host, int port)
  {
    this.host = host;
    this.port = port;
    this.property = new PropertyChangeHandler<>(this);
    try
    {
      socket = new Socket(host, port);
      out = new ObjectOutputStream(socket.getOutputStream());
      in = new ObjectInputStream(socket.getInputStream());
      ClientReceiver receiver = new ClientReceiver(this, in);
      Thread recieverThread = new Thread(receiver);
      recieverThread.start();
    }
    catch (Exception e)
    {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }

  public StudentListClient()
  {
    this(HOST, PORT);
  }

  @Override public synchronized Student getStudentByStudyNumber(
      String studyNumber)
  {
    try
    {
      StringPackage request = new StringPackage(NetworkType.NUMBER,
          studyNumber);
      System.out.println("Sending: " + request);
      out.writeObject(request);
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
      }
      Student student = this.studentReply;
      this.studentReply = null;
      System.out.println("Received: " + student);
      return student;
    }
    catch (Exception e)
    {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }

  @Override public synchronized Student getStudentByName(String name)
  {
    try
    {
      StringPackage request = new StringPackage(NetworkType.NAME, name);
      System.out.println("Sending: " + request);
      out.writeObject(request);
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
      }
      Student student = this.studentReply;
      this.studentReply = null;
      System.out.println("Received: " + student);
      return student;
    }
    catch (Exception e)
    {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }

  @Override public synchronized void addStudent(Student student)
  {
    try
    {
      StudentPackage requestPakage = new StudentPackage(student);
      System.out.println("Sending: " + requestPakage);
      out.writeObject(requestPakage);
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
      }
      if (errorString != null)
      {
        System.out.println("Received: " + null);
        String error = errorString;
        errorString = null;
        throw new IllegalStateException(error);
      }
      String reply = this.stringReply;
      this.stringReply = null;
      System.out.println("Received: " + reply);
    }
    catch (Exception e)
    {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }

  @Override public void close()
  {
    try
    {
      in.close();
      out.close();
      socket.close();
    }
    catch (IOException e)
    {
      throw new IllegalStateException("Cannot close TCP connection", e);
    }
  }

  public synchronized void replyReceived(Student reply)
  {
    this.studentReply = reply;
    notify();
  }

  public synchronized void replyReceived(String reply)
  {
    this.stringReply = reply;
    notify();
  }

  public synchronized void replyError(String error)
  {
    this.errorString = error;
    notify();
  }

  public synchronized void broadcastReceived(String message)
  {
    System.out.println("Client: broadcast " + message);
    property.firePropertyChange("broadcast", null, message);
  }

  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }
}
