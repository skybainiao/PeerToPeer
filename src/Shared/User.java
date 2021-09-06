package Shared;

public class User {
    private String username;
    private int receiveMessageNum;

    public User(String username,int receiveMessageNum){
        this.username=username;
        this.receiveMessageNum=receiveMessageNum;
    }

    public void setReceiveMessageNum(int receiveMessageNum) {
        this.receiveMessageNum = receiveMessageNum;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getReceiveMessageNum() {
        return receiveMessageNum;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", receiveMessageNum=" + receiveMessageNum +
                '}';
    }
}
