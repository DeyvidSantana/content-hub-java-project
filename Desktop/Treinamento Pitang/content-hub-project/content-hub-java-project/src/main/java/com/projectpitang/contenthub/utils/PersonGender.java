package com.projectpitang.contenthub.utils;

public enum PersonGender {
    O(0,"Other"), F(1, "Female"), M(2, "Male");

    private int id;
    private String description;

    PersonGender(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
