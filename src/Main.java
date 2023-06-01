import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    ArrayList<Assignment> minors = new ArrayList<>();
    ArrayList<Assignment> majors = new ArrayList<>();
    ArrayList<Subject> subjects = new ArrayList<>();
    int minorCount = 0;
    int majorCount = 0;
    double overAllAverage = 0.0;

    System.out.println("Hello student, welcome to the grade calculator!");
    System.out.print("Name: ");
    String name = scan.nextLine();

    int subjectCount = -1;
    try {
      File f = new File(name + "Grades.txt");
      BufferedReader reader = new BufferedReader(new FileReader(name + "Grades.txt"));
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.isEmpty()) {
          continue;
        }
        String subjectName = line.substring(0, line.indexOf(":"));
        if (subjectName.contains("# Minor Assessments") || subjectName.contains("# Major Assessments")) {
          int count = Integer.parseInt(line.substring(line.indexOf(":") + 2));
          if (line.contains("# Minor Assessments")) {
            subjects.get(subjectCount).setMinorCount(count);
          } else if (line.contains("# Major Assessments")) {
            subjects.get(subjectCount).setMajorCount(count);
          }
        } else if (subjectName.contains("Minor Assessment Grades") || subjectName.contains("Major Assessment Grades")) {
          String values = line.substring(line.indexOf("[") + 1, line.length() - 1);
          boolean cont = true;
          while(cont) {
            double value = 0;
            if (values.indexOf(",") > -1) {
              value = Double.parseDouble(values.substring(0, values.indexOf(",")));
              values = values.substring(values.indexOf(",") + 2);
            } else {
              value = Double.parseDouble(values);
              cont = false;
            }
            Assignment assignment = new Assignment(value, false);
            if(subjectName.contains("Minor Assessment Grades")){
              subjects.get(subjectCount).addMinor(assignment);
            }else if(subjectName.contains("Major Assessment Grades")){
              subjects.get(subjectCount).addMajor(assignment);
            }
          }
        } else if(!(subjectName.contains("Overall Average"))){

          String numberStr = line.substring(line.indexOf(":") + 2, line.length() - 1);
          double grade = Double.parseDouble(numberStr);
          subjects.add(new Subject(subjectName));
          subjectCount++;
          subjects.get(subjectCount).setGrade(grade);

        }
      }
     // subjects.remove(subjects.size());
      System.out.println("Loaded File!");
      reader.close();
    } catch (IOException e) {

    }

    int input = 0;
    while (!(input == 4)) {
      System.out.println("\n1. All Class Averages\n2. Add Class\n3. Edit Class\n4. Quit");
      System.out.print("Input: ");
      input = scan.nextInt();
      if (input == 1 && subjects.size() > 0) {
        System.out.println();
        double total = 0;
        for (Subject subject : subjects) {
          System.out.println(subject.getSubjectName() + ": " + subject.getGrade() + "%");
          System.out.println(
              "# Minor Assessments: " + subject.getMinorCount() + "\n# Major Assessments: " + subject.getMajorCount());
          System.out.println("Minor Assessment Grades: " + subject.getMinorValues());
          System.out.println("Major Assessment Grades: " + subject.getMajorValues());
          total += subject.getGrade();
        }
        System.out.println("\nOverall Average: " + Math.round((total / subjects.size())*100.0)/100.0 + "%");
      } else if (input == 2) {
        minors.clear();
        majors.clear();
        System.out.print("Class Name: ");
        String subjectName = scan.nextLine();
        subjectName = scan.nextLine();
        Subject subject = new Subject(subjectName);
        double inputNum = 0;

        System.out.println("\n====== MINOR ASSESSMENTS ======");
        while (!(inputNum == -1)) {
          System.out.print("Input one minor assignment grade, -1 to finish: ");
          inputNum = scan.nextDouble();
          if (!(inputNum == -1)) {
            Assignment i = new Assignment(inputNum, false);
            minors.add(i);
            subject.increaseMinorCount();
          }
        }
        for (int i = 0; i < minors.size(); i++) {
          subject.addMinor(minors.get(i));
        }
        System.out.println("====== MAJOR ASSESSMENTS ======");
        inputNum = 0;
        while (!(inputNum == -1)) {
          System.out.print("Input your major grades, -1 to finish: ");
          inputNum = scan.nextDouble();
          if (!(inputNum == -1)) {
            Assignment i = new Assignment(inputNum, true);
            majors.add(i);
            subject.increaseMajorCount();
          }
        }
        for (int i = 0; i < majors.size(); i++) {
          subject.addMajor(majors.get(i));
        }
        Calculation grades = new Calculation(minors, majors);

        System.out.println("====== WEIGHTS =======");
        System.out.print("Input the percentage weight of minor assignments: ");
        double minorPercent = scan.nextDouble();
        subject.setMinorWeight(minorPercent);
        System.out.print("Input the percentage weight of major assignments: ");
        double majorPercent = scan.nextDouble();
        subject.setMajorWeight(majorPercent);
        double grade = grades.calculateAverage(minorPercent, majorPercent);
        subject.setGrade(grade);
        subjects.add(subject);
        System.out.println("\nYour average for this class is: " + grade + "%");
      } else if (input == 3 && subjects.size() > 0) {
        minors.clear();
        majors.clear();
        System.out.println();
        int idx = 1;
        for (Subject subject : subjects) {
          System.out.println(idx + ". " + subject.getSubjectName());
          idx++;
        }
        System.out.print("Select the class to edit: ");
        int subjectNum = scan.nextInt() - 1;
        System.out.println("Selected: " + subjects.get(subjectNum).getSubjectName());
        double inputNum = 0;
        System.out.println("\n====== MINOR ASSESSMENTS ======");
        while (inputNum != -1) {
          System.out.print("Input one minor assignment grade, -1 to finish: ");
          inputNum = scan.nextDouble();
          if (!(inputNum == -1)) {
            Assignment i = new Assignment(inputNum, false);
            minors.add(i);
            subjects.get(subjectNum).increaseMinorCount();
          }
        }
        for (int i = 0; i < minors.size(); i++) {
          subjects.get(subjectNum).addMinor(minors.get(i));
        }
        System.out.println("====== MAJOR ASSESSMENTS ======");
        inputNum = 0;
        while (!(inputNum == -1)) {
          System.out.print("Input your major grades, -1 to finish: ");
          inputNum = scan.nextDouble();
          if (!(inputNum == -1)) {
            Assignment i = new Assignment(inputNum, true);
            majors.add(i);
            subjects.get(subjectNum).increaseMajorCount();
          }
        }
        for (int i = 0; i < majors.size(); i++) {
          subjects.get(subjectNum).addMajor(majors.get(i));
        }
        Calculation grades = new Calculation(subjects.get(subjectNum).getMinor(), subjects.get(subjectNum).getMajor());
        double grade = grades.calculateAverage(subjects.get(subjectNum).getMinorWeight(),
            subjects.get(subjectNum).getMajorWeight());
        subjects.get(subjectNum).setGrade(grade);
        System.out.println("\nYour average for this class is: " + grade + "%");
      } else if (input == 4) {
        try { // ADD TEXT FILE
          File obj = new File(name + "Grades.txt");
          if (!obj.exists()) {
            obj.createNewFile();
            System.out.println("File created: " + obj.getName());
          } else {
            System.out.println("File already exists.");
            System.out.print("Do you wish to overwrite the file? If not, the program will create a new file. (y/n): ");
            String ans = scan.nextLine();
            ans = scan.nextLine();
            ans.toLowerCase();
            if (ans.equals("n") || ans.equals("no")) {
              System.out.print("Enter a new name: ");
              name = scan.nextLine();
              obj = new File(name + "Grades.txt");
            }
          }
          FileWriter myObj = new FileWriter(obj);
          double total = 0;
          for (Subject subject : subjects) {
            myObj.write(subject.getSubjectName() + ": " + subject.getGrade() + "%\n");
            myObj.write("# Minor Assessments: " + subject.getMinorCount() + "\n# Major Assessments: "
                + subject.getMajorCount() + "\n");
            myObj.write("Minor Assessment Grades: " + subject.getMinorValues());
            myObj.write("\nMajor Assessment Grades: " + subject.getMajorValues() + "\n");
            total += subject.getGrade();
          }
          overAllAverage = Math.round((total / subjects.size())*100.0)/100.0;
          myObj.write("\nOverall Average: " + overAllAverage + "%");
          myObj.close();
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        System.out.println("Saved to Text File");
      } else {
        System.out.println("Invalid Input");
      }
    }
  }
}
