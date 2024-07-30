package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.cartes.Carte;
import fr.umontpellier.iut.trains.cartes.TypeCarte;

import java.util.List;

public class CabineDuConducteur extends Carte {
    public CabineDuConducteur() {
        super("Cabine du conducteur", TypeCarte.Action,0,2);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choix = joueur.getMain().getNoms();
        String input = "null";
        int nbCartesDefaussees = 0;

        while (!input.isEmpty()){
            input = joueur.choisir(
                    "Choisissez X carte(s) a défausser et piochez X fois",
                    choix,
                    null,
                    true
            );

            if(!input.isEmpty()) {
                joueur.defausser(input);
                nbCartesDefaussees++;
            }
        }

        joueur.getMain().addAll(joueur.piocher(nbCartesDefaussees));
    }
}
