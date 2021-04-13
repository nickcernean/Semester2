package server;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

public class RmiTaskServer implements RemoteTaskList
{
  private TaskList taskList;
  private PropertyChangeHandler<Task, Task> property;

  public RmiTaskServer() throws RemoteException, MalformedURLException
  {
    this.taskList = new TaskList();
    this.property = new PropertyChangeHandler<>(this, true);
    startRegistry();
    startServer();
    System.out.println("Server started...");
  }

  private void startRegistry() throws RemoteException
  {
    try
    {
      Registry reg = LocateRegistry.createRegistry(1099);
      System.out.println("Register started...");
    }
    catch (ExportException e)
    {
      System.out.println("Registry already started..." + e.getMessage());
    }

  }

  private void startServer() throws RemoteException, MalformedURLException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Naming.rebind("Case", this);
  }

  @Override public void add(Task task) throws RemoteException
  {
    taskList.addTask(task);
    System.out.println(task.toString());
    property.firePropertyChange("ADD",null,task);
  }

  @Override public Task get() throws RemoteException
  {
    return taskList.getAndRemoveNext();
  }

  @Override public int size() throws RemoteException
  {
    return taskList.size();
  }

  @Override public boolean addListener(GeneralListener<Task, Task> listener,
      String... propertyNames) throws RemoteException
  {
    return property.addListener(listener,propertyNames);
  }

  @Override public boolean removeListener(GeneralListener<Task, Task> listener,
      String... propertyNames) throws RemoteException
  {
    return property.removeListener(listener,propertyNames);
  }
}
