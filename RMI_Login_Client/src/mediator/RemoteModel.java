package mediator;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteModel extends Remote
{
  void addUser(String userName, String password) throws RemoteException;
}
