package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class PoseDeRails extends Carte {
    public PoseDeRails() {
        super("Pose de rails", TypeCarte.Rail,0,3);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.obtenirPointsRails();
        joueur.obtenirFerraile(1);
    }
}
