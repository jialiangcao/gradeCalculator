import java.util.ArrayList;

public class Subject {
  private String subjectName;
  private double grade;
  private int majorCount;
  private int minorCount;
  private ArrayList<Assignment> minor;
  private ArrayList<Assignment> major;
  private double minorWeight;
  private double majorWeight;

  public Subject(String subjectName) {
    this.subjectName = subjectName;
    this.minor = new ArrayList<>();
    this.major = new ArrayList<>();
    minorWeight = 50;
    majorWeight = 50;
    grade = 0;
  }

  public ArrayList<Assignment> getMinor(){
    return minor;
  }

  public ArrayList<Assignment> getMajor(){
    return major;
  }

  public void addMinor(Assignment assignment){
    minor.add(assignment);
  }

  public void addMajor(Assignment assignment){
    major.add(assignment);
  }

  public ArrayList<Double> getMinorValues() {
    ArrayList<Double> list = new ArrayList<>();
    for(Assignment val: minor){
      list.add(val.getGrade());
    }
    return list;
  }

  public ArrayList<Double> getMajorValues() {
    ArrayList<Double> list = new ArrayList<>();
    for(Assignment val: major){
      list.add(val.getGrade());
    }
    return list;
  }

  public void setMinor(ArrayList<Assignment> minor) {
    this.minor = minor;
  }

  public void setMajor(ArrayList<Assignment> major) {
    this.major = major;
  }

  public double getMinorWeight() {
    return minorWeight;
  }

  public double getMajorWeight() {
    return majorWeight;
  }

  public void setMinorWeight(double num) {
    minorWeight = num;
  }

  public void setMajorWeight(double num) {
    majorWeight = num;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setGrade(double grade) {
    this.grade = grade;
  }

  public double getGrade() {
    return grade;
  }

  public void setMajorCount(int num) {
    majorCount = num;
  }

  public void setMinorCount(int num) {
    minorCount = num;
  }

  public void increaseMajorCount() {
    majorCount++;
  }

  public void increaseMinorCount() {
    minorCount++;
  }

  public int getMajorCount() {
    return majorCount;
  }

  public int getMinorCount() {
    return minorCount;
  }
}