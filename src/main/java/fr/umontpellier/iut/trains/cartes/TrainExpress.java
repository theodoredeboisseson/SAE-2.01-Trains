package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class TrainExpress extends Carte {
    public TrainExpress() {
        super("Train express", TypeCarte.Train,2,3);
    }

    @Override
    public void jouer(Joueur joueur) {
        genererValeurPour(joueur);
    }
}
