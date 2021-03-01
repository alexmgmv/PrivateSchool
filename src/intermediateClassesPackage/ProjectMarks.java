package intermediateClassesPackage;

public class ProjectMarks {

    private int enrollment_id;
    private int coursesProjects_id;
    private int oral_mark;
    private int total_mark;

    public ProjectMarks() {
    }

    public ProjectMarks(int enrollment_id, int coursesProjects_id, int oral_mark, int total_mark) {
        this.enrollment_id = enrollment_id;
        this.coursesProjects_id = coursesProjects_id;
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

    public int getCoursesProjects_id() {
        return coursesProjects_id;
    }

    public void setCoursesProjects_id(int coursesProjects_id) {
        this.coursesProjects_id = coursesProjects_id;
    }

    public int getOral_mark() {
        return oral_mark;
    }

    public void setOral_mark(int oral_mark) {
        this.oral_mark = oral_mark;
    }

}
