import java.util.concurrent.atomic.AtomicInteger;

public class Student {
  // insures if 2 ids are created at exactly the same time, they will not be identical
  static AtomicInteger nextId = new AtomicInteger();
  private int id;
  private String firstName;
  private String lastName;

  public Student(String firstName, String lastName) {
    this.id = nextId.incrementAndGet();
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

}
