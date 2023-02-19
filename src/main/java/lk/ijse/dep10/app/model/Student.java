package lk.ijse.dep10.app.model;



import lk.ijse.dep10.app.util.Gender;

import java.util.ArrayList;

public class Student {

    String id;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Student(String id, String name, ArrayList<String> contact, ArrayList<String> modules, Gender gender, String department) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.modules = modules;
        this.gender = gender;
        this.department = department;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getContact() {
        return contact;
    }

    public void setContact(ArrayList<String> contact) {
        this.contact = contact;
    }

    public ArrayList<String> getModules() {
        return modules;
    }

    public void setModules(ArrayList<String> modules) {
        this.modules = modules;
    }

    String name;

    ArrayList <String> contact;
    ArrayList <String> modules;

   public Gender gender;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String department;
    public Student() {
    }
}
