package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Effet;
import fr.umontpellier.iut.trains.Joueur;

public class PontEnAcier extends Carte {
    public PontEnAcier() {
        super("Pont en acier", TypeCarte.Rail,0,4);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.obtenirPointsRails();
        joueur.obtenirFerraile(1);
        joueur.ajouterEffet(Effet.aucunSurcoutSurRiviere);
    }
}
