package com.wallforfry.budget_etudiant;

import java.util.ArrayList;

/**
 * Created by Wallerand on 22/07/2015.
 */
public class Item {
    public final static int MASCULIN = 1;
    public final static int FEMININ = 2;

    public String nom;
    public int prenom;
    public int genre;

    public Item(String aNom, int aPrenom, int aGenre) {
        nom = aNom;
        prenom = aPrenom;
        genre = aGenre;
    }

    /**
     * Initialise une liste de personnes
     * @return une liste de "Personne"
     */
    public static ArrayList<Item> getAListOfItem() {
        ArrayList<Item> listPers = new ArrayList<Item>();

        listPers.add(new Item("Dashboard", 0, R.drawable.ic_home_black_24dp));
        listPers.add(new Item("Divider", 2, R.drawable.ic_home_black_24dp));
        listPers.add(new Item("Mon Budget", 1, R.drawable.ic_home_black_24dp));
        listPers.add(new Item("Revenus", 0, R.drawable.ic_file_download_black_24dp));
        listPers.add(new Item("Dépenses", 0, R.drawable.ic_file_upload_black_24dp));
        listPers.add(new Item("Opérations", 0, R.drawable.ic_add_black_24dp));
        listPers.add(new Item("Divider", 2, R.drawable.ic_add_black_24dp));
        listPers.add(new Item("Paramètres", 0, R.drawable.ic_settings_black_24dp));
        listPers.add(new Item("Aide & Commentaires", 0, R.drawable.ic_help_black_24dp));


        return listPers;
    }


}
