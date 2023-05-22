import java.util.ArrayList;

public class Class {
  private ArrayList<Assignment> minor;
  private ArrayList<Assignment> major;

  public Class(ArrayList<Assignment> minor, ArrayList<Assignment> major){
    this.minor = minor;
    this.major = major;
  }
  public double calculateAverage(double minorPercent, double majorPercent) {
    double minorTotal = 0;
    double minorCount = 0;
    double majorTotal = 0;
    double majorCount = 0;
    for (Assignment i : minor) {
      minorTotal+=i.getGrade();
      minorCount++;
    }
    for (Assignment j : major) {
      majorTotal += j.getGrade();
      majorCount++;
    }
    double minorAverage = minorTotal/minorCount;
    double majorAverage = majorTotal/majorCount;
    return Math.round((((minorPercent*0.01)*minorAverage)+((majorPercent*0.01)*majorAverage))*10.0)/10.0;
  }
}