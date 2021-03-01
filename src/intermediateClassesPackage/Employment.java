package intermediateClassesPackage;

public class Employment {

    private int course_id;
    private int trainer_id;

    public Employment() {
    }

    public Employment(int course_id, int trainer_id) {
        this.course_id = course_id;
        this.trainer_id = trainer_id;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

}
