package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Effet;
import fr.umontpellier.iut.trains.Joueur;

public class Cooperation extends Carte {
    public Cooperation() {
        super("Coopération", TypeCarte.Rail,0,5);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.obtenirPointsRails();
        joueur.obtenirFerraile(1);
        joueur.ajouterEffet(Effet.aucunSurcoutSurAdversaire);
        joueur.ajouterEffet(Effet.pasDeFerraille);
    }
}
