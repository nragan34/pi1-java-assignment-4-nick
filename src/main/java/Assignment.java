import java.util.List;

public class Assignment extends Student {

  private String assignmentName;
  private double assignmentGrade;
  private int id;

  // firstName, lastName, assignmentName, assignmentGrade
  public Assignment(int id, String firstName, String lastName, String assignmentName,
      double assignmentGrade) {
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

  public double gradeAverage(double grade, double assignmentCount) {
    double gradesEarned = grade += grade;
    double totalScore = assignmentCount * 100;
    return gradesEarned / totalScore;
  }

  public String letterGrade(double grade) {
    String letterGrade;
    if (grade >= 90) {
      return letterGrade = "A";
    } else if (grade >= 80 && grade < 90) {
      return letterGrade = "B";
    } else if (grade >= 70 && grade < 80) {
      return letterGrade = "C";
    } else if (grade >= 60 && grade < 70) {
      return letterGrade = "D";
    } else {
      return letterGrade = "F";
    }
  }

  public List<Assignment> findMatchingAssignments(int id) {
    return null;
  }


}
