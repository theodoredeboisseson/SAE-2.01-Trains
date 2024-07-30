package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class TrainDeTourisme extends Carte {
    public TrainDeTourisme() {
        super("Train de tourisme", TypeCarte.Train,1,4);
    }

    @Override
    public void jouer(Joueur joueur) {
        genererValeurPour(joueur);
        joueur.incrementerMarqueurScore(1);
    }
}
