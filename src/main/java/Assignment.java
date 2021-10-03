import java.util.List;

// is a
public class Assignment {

  private String assignmentName;
  private double assignmentGrade;
  private int studentId;

  // firstName, lastName, assignmentName, assignmentGrade
  public Assignment(int id, String assignmentName, double assignmentGrade) {
    this.studentId = id;
    this.assignmentName = assignmentName;
    this.assignmentGrade = assignmentGrade;
  }

  public String getAssignmentName() {
    return assignmentName;
  }

  public double getAssignmentGrade() {
    return assignmentGrade;
  }

  public int getStudentId() {
    return studentId;
  }
}
