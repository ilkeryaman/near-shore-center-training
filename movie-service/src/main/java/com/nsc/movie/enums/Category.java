package com.nsc.movie.enums;

public enum Category {
    DRAMA("drama"),
    ACTION("action"),
    SCI_FI("sci-fi"),
    FANTASY("fantasy"),
    COMEDY("comedy");

    private String name;

    Category(String name){
        this.name = name;
    }

    public String getValue(){
        return this.name;
    }
}
