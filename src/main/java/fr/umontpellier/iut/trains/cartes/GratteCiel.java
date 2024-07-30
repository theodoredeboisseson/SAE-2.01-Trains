package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class GratteCiel extends Carte {
    //les points valeur représentent ici les points victoires données en fin de partie
    public GratteCiel() {
        super("Gratte-ciel", TypeCarte.Victoire,4,8);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.obtenirFerraile(1);
    }
}