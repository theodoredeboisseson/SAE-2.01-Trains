package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class TrainOmnibus extends Carte {
    public TrainOmnibus() {
        super("Train omnibus", TypeCarte.Train,1,1);
    }

    @Override
    public void jouer(Joueur joueur) {
        genererValeurPour(joueur);
    }
}
