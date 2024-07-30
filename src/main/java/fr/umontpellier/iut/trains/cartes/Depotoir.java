package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Effet;
import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.cartes.Carte;
import fr.umontpellier.iut.trains.cartes.TypeCarte;

public class Depotoir extends Carte {
    public Depotoir() {
        super("Dépotoir", TypeCarte.Action,1,5);
    }

    @Override
    public void jouer(Joueur joueur) {
        genererValeurPour(joueur);
        if (!joueur.getListeEffets().contains(Effet.pasDeFerraille))
            joueur.getListeEffets().add(Effet.pasDeFerraille);
    }
}
