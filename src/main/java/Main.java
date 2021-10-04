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

  /////////////////////////////////////
  //// Report Object List
  HashMap<Integer, List<Report>> studentAssignmentList = new HashMap<>();


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
    HashMap<Student, List<Double>> studentGradeMap = createStudentReport();
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

    System.out.println("Student Object List" + studentObjectList);
    studentObjectList
        .stream()
        .forEach(student -> {

          // get grade for each assignment
          double studentGrade = commandLineInput.userInputNumber(
              "Enter the grade for " + student.getFirstName() + " " + student.getLastName()
                  + ": ");

          System.out.println("Printing Assignment Grade: " + studentGrade);
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
            System.out.println("Assignment grade list: " + assignmentGradeList.get(i));
            student.getGradeList().add(assignmentGradeList.get(i).getAssignmentGrade());
          }
          // Create Hashmap
          studentGradesMap.put(student, gradesList);
        });
    // return Hashmap <Student, List of grades>
    System.out.println("We are in the createStudentReport.... -> ");
    studentObjectList.stream().forEach(s -> {System.out.println(s.getGradeList().get(0));});
    return studentGradesMap;
  }


  public void createStudentObjectLetterGradeList() {

    studentObjectList
        .stream()
        .forEach(student -> {
          // pass through size of list as count
          // using count to get total points      totalPoints = 100 * count
          student.calculateaverage(student.getGradeList().size());
//          System.out.println("Get student grades........... ");
//          System.out.println(student.getGradeList());
        });
//    studentObjectList.stream().forEach(s -> {
//      System.out.println("Printing average............ ");
//      System.out.println(s.getaverage());
//    });
  }

//  public double calculateaverage() {
//
////    double gradesEarned = gradeAverage += gradeAverage;
////    double totalScore = assignmentCount * 100;
////    return this.gradeAverage = gradesEarned / totalScore;
//  }



  public void setLetterGrade() {
    studentObjectList
        .stream()
        .forEach(student -> {
          student.calculateAverage();
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




//          for (Map.Entry<Student, List<Double>> entry : studentListHashMap.entrySet()) {
//            Student key = entry.getKey();
//            List<Double> value = entry.getValue();
//            System.out.println("Printing values..... -> ");
//            System.out.println(value);
//            // ... // ..
//            System.out.println();
//          }



//  public List<Assignment> createAssignmentListPerStudent(Student student) {
//    List<Assignment> listOfAssignments = new ArrayList<>();
//    if (requestNameOfAssignment(student) != null) {
//      listOfAssignments.add(requestNameOfAssignment(student));
//      return listOfAssignments;
//    } else {
//      createAssignmentObjects();
//    }
//  }
//
//  public Assignment requestNameOfAssignment(Student student) {
//    // get name of assignment
//    String assignmentName = commandLineInput.userInputString(
//        "\nEnter the name of an Assignment: ");
//
//    // create assignment
//    Assignment createdAssignment = new Assignment(student.getId(), assignmentName, commandLineInput.userInputNumber(
//        "Enter the grade for " + student.getFirstName() + " " + student.getLastName() + ": "));
//
//
//    return createdAssignment;
//  }

//  ////////////////////////////
//  // Match Student Object
//  public void findStudentObjectMatches() {
//    System.out.println("student report card");
//    studentObjectList
//        .stream()
//        .forEach(e -> {
//          System.out.println("finding student object matches.... ");
//          System.out.println(e.getId());
//        });
//  }

//  ////////////////////////////
//  // Create report cards objects
//  public void cleanReportCardData() {
//    System.out.println("student report card");
//    assignmentList
//        .stream()
//        .forEach(e -> {
//          System.out.println(e.getStudentId());
//          System.out.println("----------------");
//          System.out.println("----------------");
//        });
//  }

//    String outputDirectory = commandLineInput.userInputString(
//        "\nEnter output Directory: ");
////////////////////////////
// Create report cards from assignmentList
//  public void getStudentsAssignments() {
//    System.out.println("student report card");
//    List<Integer> collectedStudentId = studentObjectList
//        .stream().map(e -> e.getId()).collect(Collectors.toList());
//    assignmentList
//        .stream()
//        .filter(e -> collectedStudentId.contains(e.getStudentId()))
//        .forEach(System.out::println);
//  }

// C:\Users\ragan\Desktop\students.txt

// Write Grades to a report card

// get output file location

// loop through assignment object and find all grades associated with student by id

// create a list of those

// loop through that list

// create new file for each

////////////////////////////
// Prompt user if they would like to create another assignment


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