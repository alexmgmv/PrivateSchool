package intermediateClassesPackage;

import java.time.LocalDate;

public class CoursesAssignments {

    private int course_id;
    private int assignment_id;
    private LocalDate submission_date;

    public CoursesAssignments() {
    }

    public CoursesAssignments(int course_id, int assignment_id, LocalDate submission_date) {
        this.course_id = course_id;
        this.assignment_id = assignment_id;
        this.submission_date = submission_date;
    }

    public LocalDate getSubmission_date() {
        return submission_date;
    }

    public void setSubmission_date(LocalDate submission_date) {
        this.submission_date = submission_date;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(int assignment_id) {
        this.assignment_id = assignment_id;
    }

}
