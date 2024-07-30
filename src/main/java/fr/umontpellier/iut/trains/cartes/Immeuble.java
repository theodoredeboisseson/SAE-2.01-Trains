package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Immeuble extends Carte {
    //les points valeur représentent ici les points victoires données en fin de partie
    public Immeuble() {
        super("Immeuble", TypeCarte.Victoire,2,5);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.obtenirFerraile(1);
    }
}
