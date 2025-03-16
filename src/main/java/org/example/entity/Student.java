package org.example.entity;

public class Student {
    private int id;
    private String name;
    private String email;
    private Integer group_id;

    public Student(int id, String name, String email, Integer group_id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.group_id = group_id;
    }

    public Student(String name, String email, Integer group_id) {
        this.name = name;
        this.email = email;
        this.group_id = group_id;
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

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", group_id='" + group_id + '\'' +
                '}';
    }
}
