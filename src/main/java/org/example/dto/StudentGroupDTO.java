package org.example.dto;

public class StudentGroupDTO {
    private int id;
    private String name;
    private String email;
    private String groupName;

    public StudentGroupDTO(int id, String name, String email, String groupName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.groupName = groupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "StudentGroupDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
