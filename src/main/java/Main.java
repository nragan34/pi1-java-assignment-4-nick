import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

  /////////////////////////////////////
  //// Welcome Message
  public static String welcome = "\nWelcome to the Student Report Card Generator";
  public static String lineSeparator = "--------------------------------------------";

  /////////////////////////////////////
  //// Instantiate Classes
  FileHandler fileHandler = new FileHandler();
  CLI commandLineInput = new CLI();


  /////////////////////////////////////
  //// Student Name List
  List<String> studentNameList = new ArrayList<>();

  /////////////////////////////////////
  //// Student Object List
  List<Student> studentObjectList = new ArrayList<>();

  /////////////////////////////////////
  //// Assignment Object List
  List<Assignment> assignmentList = new ArrayList<>();


  public static void main(String... args) {
    System.out.println(welcome);
    System.out.println(lineSeparator);

    try {
      // create Usable Object to run this app
      Main run = new Main();
      // run app
      run.app();
      // terminate application
      System.out.println("Goodbye!");
    } catch (Exception e) {
      // catch exception
      e.printStackTrace();
    }
  }

  ////////////////////////////
  // Populate Student Array With Student Names In File
  public void app() {

    boolean generateReportCards = true;

    while (generateReportCards) {
      // get file path
      String filePath = commandLineInput.userInputString(
          "\nEnter the student input file location: ");

      ////////////////////////////
      // read file and populate studentNameList or Run again
      if (fileHandler.readStudentNameFile(filePath) != null) {
        // reading student name file and returning to studentName list
        studentNameList = fileHandler.readStudentNameFile(filePath);
        // printing list of students
        printStudentNameList();
      } else {
        app();
      }

      ////////////////////////////
      // create studentObjectList from studentNameList
      createStudentObjects();

      ////////////////////////////
      // Create assignment objects for students
      createAssignmentObjects();
      while (true) {
        String createAnotherAssignment = createAnotherAssignment();
        if (createAnotherAssignment.equalsIgnoreCase("y")) {
          createAssignmentObjects();
        } else if (createAnotherAssignment.equalsIgnoreCase("n")) {
          break;
        } else {
          System.out.println("Invalid input! Please enter one of the following -> (y/n): ");
        }
      }
      break;
    }

    // C:\Users\ragan\Desktop\students.txt
    ////////////////////////////
    // Create report
    createStudentReport();
    // create student object gradeList with assignment grades
    createStudentObjectLetterGradeList();
    // Set letter grade for the average grade
    setLetterGrade();
    // Get report file directory
    String directoryPath = getReportFileDirectory();
    writeReportToFile(directoryPath);
  }

  ////////////////////////////
  // Create Student Objects And Add To StudentList
  public void createStudentObjects() {
    studentNameList
        .stream()
        .forEach(e -> {
          String firstName = e.split(" ")[0];
          String lastName = e.split(" ")[1];
          studentObjectList.add(new Student(firstName, lastName));
        });

  }

  ////////////////////////////
  // Print students from array
  public void printStudentNameList() {
    System.out.println("");
    studentNameList
        .stream()
        .forEach(e -> {
          System.out.println("Creating student " + e);
        });
  }

  ////////////////////////////
  // Create Student Objects And Add To StudentList
  public void createAssignmentObjects() {
    String assignmentName = commandLineInput.userInputString(
        "\nEnter the name of an Assignment: ");

    studentObjectList
        .stream()
        .forEach(student -> {

          // get grade for each assignment
          double studentGrade = commandLineInput.userInputNumber(
              "Enter the grade for " + student.getFirstName() + " " + student.getLastName()
                  + ": ");

          // create an assignment object
          Assignment assignmentObject = new Assignment(student.getId(), assignmentName,
              studentGrade);

          // add student assignment to student assignmentList
          student.getAssignmentList().add(assignmentObject);

        });
  }

  ////////////////////////////
  // Prompt user if they would like to create another assignment
  public String createAnotherAssignment() {
    String userInput = commandLineInput.userInputString(
        "\nAnother Assignment? (y/n): ");
    return userInput;
  }

  ////////////////////////////
  // Create a HashMap <Student, [grades]
  public HashMap<Student, List<Double>> createStudentReport() {

    // store grades as list
    HashMap<Student, List<Double>> studentGradesMap = new HashMap<>();
    studentObjectList
        .stream()
        .forEach(student -> {
          List<Double> gradesList = new ArrayList<>();
          // Get assignment list in student object
          List<Assignment> assignmentGradeList = student.getAssignmentList();
          // Loop through assignment grades and add to gradesList
          for (int i = 0; i < assignmentGradeList.size(); i++) {
            student.getGradeList().add(assignmentGradeList.get(i).getAssignmentGrade());
          }
          // Create Hashmap
          studentGradesMap.put(student, gradesList);
        });
    // return Hashmap <Student, List of grades>
    return studentGradesMap;
  }


  public void createStudentObjectLetterGradeList() {

    studentObjectList
        .stream()
        .forEach(student -> {

          student.calculateAverage(student.getGradeList().size());

        });

  }


  public void setLetterGrade() {
    studentObjectList
        .stream()
        .forEach(student -> {
          student.calculateLetterGrade();
        });
  }

  public String getReportFileDirectory() {
    // get file path
    String directoryPath = commandLineInput.userInputString(
        "\nEnter output Directory: ");
    return directoryPath;
  }

  public void writeReportToFile(String fileDirectory) {
    studentObjectList
        .stream()
        .forEach(student -> {
          // output one line at a time
          fileHandler.writeStudentReports(fileDirectory, student);
        });
  }


}
// C:\Users\ragan\Desktop\students.txt