package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Effet;
import fr.umontpellier.iut.trains.Joueur;

public class Tunnel extends Carte {
    public Tunnel() {
        super("Tunnel", TypeCarte.Rail,0,5);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.obtenirPointsRails();
        joueur.obtenirFerraile(1);
        joueur.ajouterEffet(Effet.aucunSurcoutSurMontagne);
    }
}
