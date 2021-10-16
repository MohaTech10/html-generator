package com.mohdev.css;


public class CssLevel {
    enum LevelType {
        Inline, DocumentLevel, External,
    }
    LevelType levelType;

    public CssLevel(LevelType levelType) {
        this.levelType = levelType;
    }
}
class Inline extends CssLevel {

    public Inline(LevelType levelType) {
        super(levelType);
    }

    public void execute() {

    }
}


