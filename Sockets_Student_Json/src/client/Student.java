package client;

public class Student
{
  private String name;

  private int studentNumber;

  public Student(int studentNumber, String name)
  {
    this.name = name;
    this.studentNumber = studentNumber;

  }

  public String getName()
  {
    return name;
  }

  public int getStudentNumber()
  {
    return studentNumber;
  }

  @Override public String toString()
  {
    return "Student: "+ name +", "+
        + studentNumber ;
  }
}
