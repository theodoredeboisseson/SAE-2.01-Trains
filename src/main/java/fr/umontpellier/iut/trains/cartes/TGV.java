package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class TGV extends Carte {
    public TGV() {
        super("TGV", TypeCarte.Train,1,2);
    }

    @Override
    public void jouer(Joueur joueur) {
        genererValeurPour(joueur);

        if(joueur.getCartesEnJeu().count("Train omnibus") > 0)
            joueur.incrementerArgent(1);
    }
}
