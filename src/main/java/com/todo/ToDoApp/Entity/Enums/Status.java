package com.todo.ToDoApp.Entity.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    PENDING("Pending"),
    IN_PROGRESS("In progress"),
    COMPLETED("Completed");

    private final String value;

    Status(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue(){
        return value;
    }

    @JsonCreator
    public static Status fromValue(String value){
        for(Status status : Status.values()){
            if(status.value.equalsIgnoreCase(value)){
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status: "+value);
    }
}
