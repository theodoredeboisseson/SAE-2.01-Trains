package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.*;

public class Appartement extends Carte {
    //les points valeur représentent ici les points victoires données en fin de partie
    public Appartement() {
        super("Appartement", TypeCarte.Victoire,1,3);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.obtenirFerraile(1);
    }
}
