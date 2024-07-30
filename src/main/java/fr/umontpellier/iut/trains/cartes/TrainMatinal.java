package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Effet;
import fr.umontpellier.iut.trains.Joueur;

public class TrainMatinal extends Carte {
    public TrainMatinal() {
        super("Train matinal", TypeCarte.Train,2,5);
    }

    @Override
    public void jouer(Joueur joueur) {
        genererValeurPour(joueur);
        joueur.ajouterEffet(Effet.cartesAchetees_auDeck);
    }
}
