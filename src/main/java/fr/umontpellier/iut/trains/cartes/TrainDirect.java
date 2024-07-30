package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class TrainDirect extends Carte {
    public TrainDirect() {
        super("Train direct", TypeCarte.Train,3,6);
    }

    @Override
    public void jouer(Joueur joueur) {
        genererValeurPour(joueur);
    }
}
