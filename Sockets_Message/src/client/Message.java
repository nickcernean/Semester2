package client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message
{
  private String id;
  private String messageBody;
  private LocalDateTime dateTime;

  public Message(String id, String message)
  {
    this.dateTime = LocalDateTime.now();
    this.id = id;
    this.messageBody = message;
  }

  public Message(String message)
  {
    this("0", message);

  }

  public Message update()
  {
    this.dateTime = LocalDateTime.now();
    return this;
  }

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getBody()
  {
    return messageBody;
  }

  public LocalDateTime getDateTime()
  {
    return dateTime;
  }

  public String getDateTime(String format)
  {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return dateTime.format(formatter);
  }

  @Override public String toString()
  {
    DateTimeFormatter formatter = DateTimeFormatter
        .ofPattern("d/MM/yyyy HH:mm:ss");
    return id = " + id,  messageBody '" + messageBody + ", dateTime=" + dateTime
        .format(formatter);
  }
}
