package shared;

import client.Student;

import java.io.Serializable;

public class Message implements Serializable
{
  private String messageBody;
  private Student student;

  public Message(Student student, String messageBody)
  {
    this.student = student;
    this.messageBody = messageBody;
  }

  public String getMessageBody()
  {
    return messageBody;
  }

  public Student getStudent()
  {
    return student;
  }
  @Override public String toString()
  {
    return student + ": " + messageBody;
  }
}
