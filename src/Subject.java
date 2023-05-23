public class Subject{
  private String subjectName;
  private double grade;

  public Subject(String subjectName){
    this.subjectName = subjectName;
    grade = 0;
  }

  public String getSubjectName(){
    return subjectName;
  }

  public void setGrade(double grade){
    this.grade = grade;
  }

  public double getGrade(){
    return grade;
  }
}