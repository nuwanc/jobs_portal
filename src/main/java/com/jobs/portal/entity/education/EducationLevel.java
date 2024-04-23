package com.jobs.portal.entity.education;

public enum EducationLevel {

    GCSE(1,"General Certificate of Secondary Education"),
    GCSEOL (2, "General Certificate of Secondary Education (OL)"),

    GCSEAL(3, "General Certificate of Secondary Education (AL)"),

    ASSOCIATE(4, "Associate degree"),

    BACHELORS(5,"Bachelor's degree"),

    MASTERS(6, "Master's degree"),

    PHD(7, "Doctoral degree");



    private final int level;
    private final String description;

    EducationLevel(int level, String description) {
        this.level = level;
        this.description = description;
    }

    // Getter for level
    public int getLevel() {
        return level;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.level + " (" + this.name() + ") - " + this.description;
    }

    // Main method to test the enum
    public static void main(String[] args) {
        for (EducationLevel level : EducationLevel.values()) {
            System.out.println(level);
        }
    }
}
