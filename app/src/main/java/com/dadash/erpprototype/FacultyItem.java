package com.dadash.erpprototype;

public class FacultyItem {

    private String name;
    private String location;
    private String contact;

    // Constructor
    public FacultyItem(String name, String location, String contact) {
        this.name = name;
        this.location = location;
        this.contact = contact;
    }

    // Getters
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getContact() { return contact; }
}
