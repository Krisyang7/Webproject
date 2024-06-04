package com.example.webproject.Bean;

import java.sql.Time;

public class Diary {
    private String operator;
    private String action;
    private Time actionTime;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Time getActionTime() {
        return actionTime;
    }

    public void setActionTime(Time actionTime) {
        this.actionTime = actionTime;
    }
}
