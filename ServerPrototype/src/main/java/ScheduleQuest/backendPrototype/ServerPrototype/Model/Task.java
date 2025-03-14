package ScheduleQuest.backendPrototype.ServerPrototype.Model;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class Task {

    private String taskName;

    private int difficulty;
    private int pointValue;

    private Timestamp createdAt;



    public Task(int internalId, String taskName, int difficulty, int pointValue, Timestamp createdAt) {
        this.taskName = taskName;
        this.difficulty = difficulty;
        this.pointValue = pointValue;
        this.createdAt = createdAt;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }




    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }
}