  public class Assignment {
    private final double grade;
    private final boolean isMajor;

    public Assignment(double grade, boolean isMajor){
      this.grade = grade;
      this.isMajor = isMajor;
    }
    public double getGrade() {
      return grade;
    }
    public boolean isMajor() {
      return isMajor;
    }
  }