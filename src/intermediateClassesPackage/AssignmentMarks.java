package intermediateClassesPackage;

public class AssignmentMarks {

    private int enrollment_id;
    private int coursesAssignments_id;
    private int oral_mark;
    private int total_mark;

    public AssignmentMarks() {
    }

    public AssignmentMarks(int enrollment_id, int coursesAssignments_id, int oral_mark, int total_mark) {
        this.enrollment_id = enrollment_id;
        this.coursesAssignments_id = coursesAssignments_id;
        this.oral_mark = oral_mark;
        this.total_mark = total_mark;
    }

    public int getTotal_mark() {
        return total_mark;
    }

    public void setTotal_mark(int total_mark) {
        this.total_mark = total_mark;
    }

    public int getEnrollment_id() {
        return enrollment_id;
    }

    public void setEnrollment_id(int enrollment_id) {
        this.enrollment_id = enrollment_id;
    }

    public int getCoursesAssignments_id() {
        return coursesAssignments_id;
    }

    public void setCoursesAssignments_id(int coursesAssignments_id) {
        this.coursesAssignments_id = coursesAssignments_id;
    }

    public int getOral_mark() {
        return oral_mark;
    }

    public void setOral_mark(int oral_mark) {
        this.oral_mark = oral_mark;
    }

}
