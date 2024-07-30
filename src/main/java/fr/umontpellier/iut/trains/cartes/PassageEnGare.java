package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.cartes.Carte;
import fr.umontpellier.iut.trains.cartes.TypeCarte;

public class PassageEnGare extends Carte {

    public PassageEnGare() {
        super("Passage en gare", TypeCarte.Action,1,3);
    }

    @Override
    public void jouer(Joueur joueur) {
        genererValeurPour(joueur);
        joueur.getMain().addAll(joueur.piocher(1));
    }
}
