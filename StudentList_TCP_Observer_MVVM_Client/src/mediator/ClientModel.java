package mediator;

import model.Student;
import utility.observer.subject.LocalSubject;

public interface ClientModel extends LocalSubject<String, String>
{
  public Student getStudentByStudyNumber(String studyNumber);
  public Student getStudentByName(String name);
  public void addStudent(Student student);

  public void close();
}
