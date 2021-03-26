package client.model;

import client.mediator.ServerModel;
import client.mediator.UppercaseClient;

import java.io.IOException;

public class ModelManager implements Model
{
  private ServerModel serverModel;

  public ModelManager() throws IOException
  {
    this.serverModel = new UppercaseClient();
    serverModel.connect();
  }

  @Override public String convert(String source) throws IOException
  {

  return  serverModel.convert(source);
  }

  @Override public void addLog(String log)
  {

  }
}
