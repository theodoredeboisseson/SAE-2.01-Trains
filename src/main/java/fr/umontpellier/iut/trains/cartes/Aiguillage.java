package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.cartes.Carte;
import fr.umontpellier.iut.trains.cartes.TypeCarte;

public class Aiguillage extends Carte {
    public Aiguillage() {
        super("Aiguillage", TypeCarte.Action, 0, 5);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.getMain().addAll(joueur.piocher(2));
    }
}
