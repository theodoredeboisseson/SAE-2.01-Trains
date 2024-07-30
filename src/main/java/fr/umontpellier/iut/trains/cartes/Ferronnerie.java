package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Effet;
import fr.umontpellier.iut.trains.Joueur;

public class Ferronnerie extends Carte {
    public Ferronnerie() {
        super("Ferronnerie", TypeCarte.Action,1,4);
    }

    @Override
    public void jouer(Joueur joueur) {
        genererValeurPour(joueur);
        joueur.ajouterEffet(Effet.gagne2argentCarteRailJouee);
    }
}
