import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
      System.out.println("This line in your file needs a space between the first and last name... " + line);
      return null;
    }
  }

  ///////////////////////////////////////////////////
  /////////////////////////// Check to see if line has a space between firstName and lastName
//  public String checkLineBetweenNames(String line) {
//    int count = 0;
//    for (int i = 0; i < line.length(); i++) {
//      char c = line.charAt(i);
//      if (c == ' ') {
//        count++;
//      }
//    }
//    if (count != 1) {
//      System.out.println(
//          "\nPlease re-format your students.txt file.\nEach line needs to contain a first and last name separated by a space! Try again.");
//      return null;
//    }
//    return line;
//  }

  ///////////////////////////////////////////////////
  /////////////////////////// File Writer
  public void writeStudentReports(String filePath) {
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(filePath));
      String line = "";
      // loop through  and create a file for each
      while (line != null) {
        // need to create files for each student and write a report card
        //line = generateReportCards();
        writer.write(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (writer != null) {
        try {
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}