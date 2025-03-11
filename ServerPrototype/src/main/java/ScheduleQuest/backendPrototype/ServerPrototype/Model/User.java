package ScheduleQuest.backendPrototype.ServerPrototype.Model;

public class User {

    private int internalId;
    private String userName;

    private String passWord;

    private byte[] salt;

    public int getInternalId() {
        return internalId;
    }

    public void setInternalId(int internalId) {
        this.internalId = internalId;
    }


    public User(int internalId, String userName, String passWord) {
        this.internalId = internalId;
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
