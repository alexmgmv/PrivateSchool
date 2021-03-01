package intermediateClassesPackage;

import java.time.LocalDate;

public class CoursesProjects {

    private int course_id;
    private int project_id;
    private LocalDate submission_date;

    public CoursesProjects() {
    }

    public CoursesProjects(int course_id, int project_id, LocalDate submission_date) {
        this.course_id = course_id;
        this.project_id = project_id;
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

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

}
