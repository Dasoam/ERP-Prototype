package com.dadash.erpprototype;

import java.util.List;

public class Club {
    private String name;
    private List<String> members;

    // Empty constructor required for Firestore
    public Club() {
    }

    public Club(String name, List<String> members) {
        this.name = name;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public List<String> getMembers() {
        return members;
    }
}
