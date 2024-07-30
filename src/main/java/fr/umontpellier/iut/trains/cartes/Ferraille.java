package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Ferraille extends Carte {
    public Ferraille() {
        super("Ferraille", TypeCarte.Ferraille, 0, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        //je ne sers à rien
    }
}
