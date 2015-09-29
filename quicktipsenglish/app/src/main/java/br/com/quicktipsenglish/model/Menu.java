package br.com.quicktipsenglish.model;

public class Menu {

    private int type;
    private String description;

    public Menu(int type, String description) {
        this.type = type;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
