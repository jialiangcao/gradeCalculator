import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    ArrayList<Assignment> minors = new ArrayList<>();
    ArrayList<Assignment> majors = new ArrayList<>();
    ArrayList<Subject> subjects = new ArrayList<>();
    System.out.println("Hello student, welcome to the grade calculator!");
    System.out.print("Name: ");
    String name = scan.nextLine();
    int input = 0;

    while(!(input == 3)){
      System.out.println("\n1. All Class Averages\n2. Add Class\n3. Quit");
      System.out.print("Input: ");
      input = scan.nextInt();
      if(input == 1 && subjects.size() > 0){
        System.out.println();
        for(Subject subject: subjects){
          System.out.println(subject.getSubjectName() + ": " + subject.getGrade() + "%");
        }
      }else if(input == 2){
        minors.clear();
        majors.clear();
        System.out.print("Class Name: ");
        String subjectName = scan.nextLine();
        subjectName = scan.nextLine();
        Subject subject = new Subject(subjectName);
        double inputNum = 0;


        System.out.println("\n====== MINOR ASSESSMENTS ======");
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
        Calculation grades = new Calculation(minors, majors);


        System.out.println("====== WEIGHTS =======");
        System.out.print("Input the percentage weight of minor assignments: ");
        double minorPercent = scan.nextDouble();
        System.out.print("Input the percentage weight of major assignments: ");
        double majorPercent = scan.nextDouble();
        double grade = grades.calculateAverage(minorPercent, majorPercent);
        subject.setGrade(grade);
        subjects.add(subject);
        System.out.println("\nYour average for this class is: " + grade + "%");
        }else if(input == 3){
          try { // ADD TEXT FILE
          File Obj = new File(name + "Grades.txt");
          if (!Obj.exists()) {
          Obj.createNewFile();
            System.out.println("File created: " + Obj.getName());
          } else {
            System.out.println("File already exists.");
          }
          FileWriter myObj = new FileWriter(Obj);
          for(Subject subject: subjects){
            myObj.write(subject.getSubjectName() + ": " + subject.getGrade() +   "%\n");
          }
          myObj.close();
          }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
          System.out.println("Saved to Text File");
          break;
      }else{
        System.out.println("Invalid Input");
      }
    }
  }
}