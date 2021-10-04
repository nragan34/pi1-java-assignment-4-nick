import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// list of assignments in this class
// (has a) is for member objects
// (is a) is used when you extend an object
// model
public class Student {

  // insures if 2 ids are created at exactly the same time, they will not be identical
  static AtomicInteger nextId = new AtomicInteger();
  private int id;
  private String firstName;
  private String lastName;
  private List<Assignment> assignmentList;
  private List<Double> gradeList;
  private String letterGrade;
  private double average;

  public Student(String firstName, String lastName) {
    this.id = nextId.incrementAndGet();
    this.firstName = firstName;
    this.lastName = lastName;
    this.assignmentList = new ArrayList<>();
    this.letterGrade = null;
    this.average = 0;
    this.gradeList = new ArrayList<>();
  }


  public void calculateAverage(int count) {

    double totalPoints = count * 100;
    double sum = 0;
    for (double i : gradeList) {
      sum += i;
    }
    this.average = (sum * 100) / totalPoints;
  }

  public void calculateLetterGrade() {
    double grade = this.average;
    if (grade >= 90) {
      this.letterGrade = "A";
    } else if (grade >= 80) {
      this.letterGrade = "B";
    } else if (grade >= 70) {
      this.letterGrade = "C";
    } else if (grade >= 60) {
      this.letterGrade = "D";
    } else {
      this.letterGrade = "F";
    }
  }

  public List<Double> getGradeList() {
    return gradeList;
  }

  public void setGradeList(List<Double> gradeList) {
    this.gradeList = gradeList;
  }

  public String getLetterGrade() {
    return letterGrade;
  }

  public double getAverage() {
    return average;
  }

  public void setAssignmentList(List<Assignment> assignmentList) {
    this.assignmentList = assignmentList;
  }

  public void setLetterGrade(String letterGrade) {
    this.letterGrade = letterGrade;
  }

  public void setaverage(double average) {
    this.average = average;
  }

  public Student(int id, String firstName, String lastName, List<Assignment> assignmentList) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.assignmentList = assignmentList;
  }

  public static AtomicInteger getNextId() {
    return nextId;
  }

  public static void setNextId(AtomicInteger nextId) {
    Student.nextId = nextId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<Assignment> getAssignmentList() {
    return assignmentList;
  }

  public String getAssignmentName() {
    return assignmentList.get(0).getAssignmentName();
  }

  public double getAssignmentGrade() {
    return assignmentList.get(0).getAssignmentGrade();
  }
}
