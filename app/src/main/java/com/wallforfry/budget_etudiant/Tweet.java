package com.wallforfry.budget_etudiant;

/**
 * Created by Wallerand on 21/07/2015.
 */
public class Tweet {
    private int color;
    private String pseudo;
    private String text;

    public Tweet(int color, String pseudo, String text) {
        this.color = color;
        this.pseudo = pseudo;
        this.text = text;
    }


    public int getColor() {
        return color;
    }

    public String getText() {
        return text;
    }

    public String getPseudo() {
        return pseudo;
    }
}
