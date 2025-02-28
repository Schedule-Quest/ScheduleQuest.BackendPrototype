package ScheduleQuest.backendPrototype.ServerPrototype.Model;

public class Task {

    private int internalId;

    private String taskName;

    private Difficulty difficulty;
    private int pointValue;


    public Task(int internalId, String taskName, Difficulty difficulty, int pointValue) {
        this.internalId = internalId;
        this.taskName = taskName;
        this.difficulty = difficulty;
        this.pointValue = pointValue;
    }

    public int getInternalId() {
        return internalId;
    }

    public void setInternalId(int internalId) {
        this.internalId = internalId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }
}