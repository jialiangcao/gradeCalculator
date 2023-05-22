import java.util.Scanner;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    ArrayList<Assignment> minors = new ArrayList<>();
    ArrayList<Assignment> majors = new ArrayList<>();
    System.out.println("Hello student, welcome to the grade calculator!");
    double inputNum = 0;
    System.out.println("====== MINOR ASSESSMENTS ======");
    while(!(inputNum == -1)){
      System.out.print("Input one minor assignment grade, -1 to finish: ");
      inputNum = scan.nextDouble();
      if(!(inputNum == -1)){
        Assignment i = new Assignment(inputNum, false);
        minors.add(i);
      }
    }
    System.out.println("====== MAJOR ASSESSMENTS ======");
    inputNum = 0;
    while(!(inputNum == -1)){
      System.out.print("Input your major grades, -1 to finish: ");
      inputNum = scan.nextDouble();
      if(!(inputNum == -1)){
        Assignment i = new Assignment(inputNum, true);
        majors.add(i);
      }
    }
    Class grades = new Class(minors, majors);
    System.out.println("====== WEIGHTS =======");
    System.out.print("Input the percentage weight of minor assignments: ");
    double minorPercent = scan.nextDouble();
    System.out.print("Input the percentage weight of major assignments: ");
    double majorPercent = scan.nextDouble();
    System.out.println("Your average for this class is: " +grades.calculateAverage(minorPercent, majorPercent) + "%");
  }
}