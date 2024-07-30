package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.cartes.Carte;
import fr.umontpellier.iut.trains.cartes.TypeCarte;

public class SalleDeControle extends Carte {
    public SalleDeControle() {
        super("Salle de contrôle", TypeCarte.Action,0,7);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.getMain().addAll(joueur.piocher(3));
    }
}
