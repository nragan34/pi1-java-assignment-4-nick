import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI {

  ////////////////////////////
  // Get user input as a string
  public String userInputString(String msg) {
    BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
    System.out.print(msg);
    try {
      String getUserInput = userInput.readLine();
      return getUserInput;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  ////////////////////////////
  // Get user input as a double
  public double userInputNumber(String msg) {
    BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
    System.out.print(msg);
    while (true) {
      try {
        String getUserInput = userInput.readLine();
        double getUserInputDouble = Double.parseDouble(getUserInput);
        if (getUserInputDouble >= 0 && getUserInputDouble <= 100) {
          return getUserInputDouble;
        }
        System.out.println("\nError! Your grade must be between (0-100). Try again! ");
        System.out.print(msg);
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("\nError! Your grade must be between (0-100). Try again! \n");
        System.out.print(msg);
      } catch (NumberFormatException e) {
        System.out.println("\nError! Your grade must be between (0-100). Try again! ");
        System.out.print(msg);
      }
    }
  }


}
