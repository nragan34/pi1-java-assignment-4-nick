import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
      // Create assignment objects
      createAssignmentObjects();

    }

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
        .forEach(e -> {
          assignmentList.add(
              new Assignment(e.getId(), e.getFirstName(), e.getLastName(), assignmentName,
                  commandLineInput.userInputNumber(
                      "Enter the grade for " + e.getFirstName() + " " + e.getLastName() + ": ")));
        });
    createAnotherAssignment();
  }

  ////////////////////////////
  // Prompt user if they would like to create another assignment
  public void createAnotherAssignment() {
    String createAnotherAssignment = commandLineInput.userInputString(
        "\nAnother Assignment? (y/n): ");
    if (createAnotherAssignment.equalsIgnoreCase("y")) {
      createAssignmentObjects();
    } else if (createAnotherAssignment.equalsIgnoreCase("n")) {
      // create reports
      createReportCards();
    } else {
      System.out.print("Invalid entry. Please select one of the following: (y/n): ");
      createAnotherAssignment();
    }
  }

  ////////////////////////////
  // Create report cards from assignmentList
  public void cleanReportCardData() {
    System.out.println("student report card");
    assignmentList
        .stream()
        .forEach(e -> {
          System.out.println(e);
          System.out.println("----------------");
          System.out.println("----------------");
        });
  }

  ////////////////////////////
  // Create report cards from assignmentList
  public void createReportCards() {
//    String outputDirectory = commandLineInput.userInputString(
//        "\nEnter output Directory: ");
    System.out.println("student report card");
    HashMap studentMap = new HashMap();
    List<Integer> collectedStudentId = studentObjectList
        .stream().map(e -> e.getId()).collect(Collectors.toList());
    assignmentList
        .stream()
        .filter(e -> collectedStudentId.contains(e.getId()))
        .forEach(System.out::println);
  }


  // C:\Users\ragan\Desktop\students.txt

  // Write Grades to a report card

  // get output file location

  // loop through assignment object and find all grades associated with student by id

  // create a list of those

  // loop through that list

  // create new file for each

  ////////////////////////////
  // Prompt user if they would like to create another assignment


}

//    List<Student> studentList = new ArrayList<>();
//
//    Student student1 = new Student("firstName1", "lastName1");
//    Student student2 = new Student("firstName2", "lastName2");
//    Student student3 = new Student("firstName3", "lastName3");
//
//    studentList.add(student1);
//    studentList.add(student2);
//    studentList.add(student3);
//
//    Assignment assignment1 = new Assignment(student1.id(), student1.getFirstName(), student1.getLastName(), "a1", 10);
//    Assignment assignment2 = new Assignment(student2.id(), student2.getFirstName(), student2.getLastName(), "a1", 10);
//    Assignment assignment3 = new Assignment(student3.id(), student3.getFirstName(), student3.getLastName(), "a1", 10);
//
//    System.out.println(student1.id());
//    System.out.println(assignment1.id());
//
//    System.out.println(student2.id());
//    System.out.println(assignment2.id());
//
//    System.out.println(student3.id());
//    System.out.println(assignment3.id());
//
//    studentList.forEach(e -> System.out.println(e.getFirstName()));