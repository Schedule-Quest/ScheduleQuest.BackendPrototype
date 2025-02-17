package Storage;

import Models.Difficulty;

public class StorageTask {

        private String taskName;

        private Difficulty difficulty;

        private int pointValue;

        public StorageTask(String taskName, Difficulty difficulty, int pointValue) {
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



