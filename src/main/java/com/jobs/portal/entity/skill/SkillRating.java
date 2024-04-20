package com.jobs.portal.entity.skill;

public enum SkillRating {
    BEGINNER(1, "Learning the basics, requires supervision."),
    NOVICE(2, "Basic understanding, limited experience."),
    INTERMEDIATE(3, "Good working proficiency, can perform tasks independently."),
    ADVANCED(4, "Very skilled, can mentor others, handle complex tasks."),
    EXPERT(5, "Top-level expertise, thought leader or innovator in the area.");

    private final int level;
    private final String description;

    // Constructor
    SkillRating(int level, String description) {
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
        for (SkillRating rating : SkillRating.values()) {
            System.out.println(rating);
        }
    }
}
