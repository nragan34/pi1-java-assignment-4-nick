import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {


  /////////////////////////////////////
  //// Find and read contents of a file
  public List<String> readStudentNameFile(String filePath) {
    BufferedReader myReader = null;
    // store formatted names to be returned
    List<String> studentName = new ArrayList<>();
    try {
      // read user input
      myReader = new BufferedReader(new FileReader(filePath));
      String line = "";
      int i = 0;
      while (line != null) {
        line = myReader.readLine();
        // if line has value and is not blank
        if (line != null && !line.isBlank()) {
          // format names from file and make sure they have a space between the first and last name
          String formatLineSpacing = formatLineSpacing(line);
          if (formatStringName(formatLineSpacing) != null) {
            String formatNames = formatStringName(formatLineSpacing);
            studentName.add(formatNames);
          } else {
            return null;
          }
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("\nInput file not found");
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    } finally {
      if (myReader != null) {
        try {
          myReader.close();
        } catch (IOException e) {
          e.printStackTrace();
          return null;
        }
      }
    }
    return studentName;
  }


  ///////////////////////////////////////////////////
  /////////////////////////// Remove extra spaces between firstName and lastName. Remove white space at beginning and end of name
  public String formatLineSpacing(String line) {
    String formattedLineSpacing = line.replaceAll("\\s{2,}", " ").trim();
    return formattedLineSpacing;
  }

  ///////////////////////////////////////////////////
  /////////////////////////// Format first character in String to Uppercase and structure string - "FirstName LastName"
  public String formatStringName(String line) {
    try {
      String[] splitString = line.split(" ");
      String firstName =
          splitString[0].substring(0, 1).toUpperCase() + splitString[0].substring(1);
      String lastName =
          splitString[1].substring(0, 1).toUpperCase() + splitString[1].substring(1);
      return firstName + " " + lastName;
    } catch (StringIndexOutOfBoundsException e) {
      System.out.println("Error please format this line again... " + line);
      return null;
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println(
          "This line in your file needs a space between the first and last name... " + line);
      return null;
    }
  }


  ///////////////////////////////////////////////////
  /////////////////////////// File Writer
  public void writeStudentReports(String fileDirectoryPath, Student student) {
    PrintWriter output = null;
    try {
      // open file
      File fileDirectoryPathVariable = new File(fileDirectoryPath);
      fileDirectoryPathVariable.mkdirs();
      String fileName = String.format("%s_%s.txt", student.getFirstName(), student.getLastName());
      output = new PrintWriter(
          new BufferedWriter(
              new FileWriter(new File(fileDirectoryPath, fileName))));

      String firstName = student.getFirstName();
      String lastName = student.getLastName();
      String letterGrade = student.getLetterGrade();
      double average = student.getAverage();
      List<Assignment> assignmentList = student.getAssignmentList();

      String formatted = String.format("%s %s \n\nAverage: %.2f \nLetter Grade: %s \n\n", firstName,
          lastName, average, letterGrade);
      // write a string and object to the file
      output.println(formatted);

      // loop through assignment list
      for (Assignment assignment : student.getAssignmentList()) {
        String assignmentName = assignment.getAssignmentName().toString();
        double assignmentGrade = assignment.getAssignmentGrade();

        String formattedAssignment = String.format("%s: %.2f%%", assignmentName, assignmentGrade);

        // write a string and object to the file
        output.println(formattedAssignment);
      }


    } catch (IOException e) {
      System.out.println(e);
    } finally {
      if (output != null) {
        output.close();
      }
    }

  }

}