package com.example.workoutapp;

public class ExercisesList {
    private String day;
    private String muscleGroup;

    public ExercisesList(String day, String muscleGroup) {
        this.day = day;
        this.muscleGroup = muscleGroup;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}
