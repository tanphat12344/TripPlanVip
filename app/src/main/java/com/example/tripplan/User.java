package com.example.tripplan;

public class User {
    private String id;
    private String Name;
    private String Password;
    private String groupId;

    public User() {
    }

    public User(String name, String password) {
        Name = name;
        Password = password;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", Password='" + Password + '\'' +
                ", groupId=" + groupId +
                '}';
    }
}
