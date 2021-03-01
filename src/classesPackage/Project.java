package classesPackage;

public class Project {

    private String title;
    private String description;
    private int maxOralMark;
    private int maxTotalMark;

    public Project() {
    }

    public Project(String title, String description, int maxOralMark, int maxTotalMark) {
        this.title = title;
        this.description = description;
        this.maxOralMark = maxOralMark;
        this.maxTotalMark = maxTotalMark;
    }

    public float getMaxTotalMark() {
        return maxTotalMark;
    }

    public void setMaxTotalMark(int maxTotalMark) {
        this.maxTotalMark = maxTotalMark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMaxOralMark() {
        return maxOralMark;
    }

    public void setMaxOralMark(int maxOralMark) {
        this.maxOralMark = maxOralMark;
    }

}
