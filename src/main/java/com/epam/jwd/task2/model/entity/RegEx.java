package com.epam.jwd.task2.model.entity;

public enum RegEx {
    LINE_BRAKE("\n");

    private final String regEx;

    RegEx(String regEx) {
        this.regEx = regEx;
    }

    public String getRegEx() {
        return regEx;
    }
}
