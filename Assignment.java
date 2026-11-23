public class Assignment {
    private String name;
    private String course;
    private String dueDate;
    private boolean completed;

    public Assignment(String name, String course, String dueDate) {
        this.name = name;
        this.course = course;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }

    public String toString() {
        return course + " - " + name + " | Due: " + dueDate
                + " | Completed: " + completed;
    }
}
