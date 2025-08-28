package com.todo.ToDoApp.Entity.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Priority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String value;

    Priority(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue(){
        return value;
    }

    @JsonCreator
    public static Priority fromValue(String value){
        for(Priority priority : Priority.values()){
            if(priority.value.equalsIgnoreCase(value)){
                return priority;
            }
        }
        throw new IllegalArgumentException("Invalid priority: "+value);
    }
}
