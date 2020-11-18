package rest.Model;

public class Student extends User {
    private int majorID;
    private int groupID;
    private int yearsOfStudy;

    public Student(int id, String email, String password, String firstName, String lastName, String role, int majorID, int groupID, int yearsOfStudy) {
        super(id, email, password, firstName, lastName, role);
        this.majorID = majorID;
        this.groupID = groupID;
        this.yearsOfStudy = yearsOfStudy;
    }

    public int getMajorID() {
        return majorID;
    }

    public void setMajorID(int majorID) {
        this.majorID = majorID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public int getYearsOfStudy() {
        return yearsOfStudy;
    }

    public void setYearsOfStudy(int yearsOfStudy) {
        this.yearsOfStudy = yearsOfStudy;
    }
}
