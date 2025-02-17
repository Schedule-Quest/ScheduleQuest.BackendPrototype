package Models;


public class Task {
    private String taskName;

    private Difficulty difficulty;
    private int pointValue;

    private int id;

    public Task(String taskName, Difficulty difficulty, int pointValue) {

        this.taskName = taskName;
        this.difficulty = difficulty;
        this.pointValue = pointValue;
    }

    public String getTaskName() {
        return taskName;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getPointValue() {
        return pointValue;
    }
}
