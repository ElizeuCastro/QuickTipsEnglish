package br.com.quicktipsenglish.model;

public class Menu {

    private int type;
    private String descriptionBr;
    private String descriptionUs;

    public Menu(int type, final String descriptionBr, final String descriptionUs) {
        this.type = type;
        this.descriptionBr = descriptionBr;
        this.descriptionUs = descriptionUs;
    }

    public int getType() {
        return type;
    }

    public String getDescriptionBr() {
        return descriptionBr;
    }

    public String getDescriptionUs() {
        return descriptionUs;
    }
}
