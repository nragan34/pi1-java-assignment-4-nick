

public class Assignment extends Student {

  private String assignmentName;
  private double assignmentGrade;
  private int id;

  // firstName, lastName, assignmentName, assignmentGrade
  public Assignment(int id, String firstName, String lastName, String assignmentName, double assignmentGrade) {
    super(firstName, lastName);
    this.id = id;
    this.assignmentName = assignmentName;
    this.assignmentGrade = assignmentGrade;
  }

  public String getAssignmentName() {
    return assignmentName;
  }

  public double getAssignmentGrade() {
    return assignmentGrade;
  }

  @Override
  public int getId() {
    return id;
  }

}
