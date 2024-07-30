package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Effet;
import fr.umontpellier.iut.trains.Joueur;

public class VoieSouterraine extends Carte {
    public VoieSouterraine() {
        super("Voie souterraine", TypeCarte.Rail,0,7);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.obtenirPointsRails();
        joueur.obtenirFerraile(1);
        joueur.ajouterEffet(Effet.aucunSurcout);
    }
}