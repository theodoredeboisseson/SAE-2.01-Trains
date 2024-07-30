package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.plateau.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Gare extends Carte {
    public Gare() {
        super("Gare", TypeCarte.Gare,0,3);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.obtenirFerraile(1);

        if (joueur.getJeu().getNbJetonsGare()>0) {
            List<String> choixPossibles = new ArrayList<>();
            List<Tuile> tuiles = joueur.getJeu().getTuiles();

            for (int i = 0; i < tuiles.size(); i++) {
                Tuile tuileTestee = tuiles.get(i);
                if(tuileTestee instanceof TuileVille && tuileTestee.getNbGares() < tuileTestee.getNbGaresMax())
                    choixPossibles.add("TUILE:" + i);
            }

            String choix = joueur.choisir(
                    "Choisir la tuile où poser la gare",
                    choixPossibles,
                    null,
                    false
            );

            if(!choix.isEmpty()){
                int numTuile = parseInt(choix.split(":")[1]);
                Tuile tuileCiblee = joueur.getJeu().getTuile(numTuile);

                tuileCiblee.ajouterGare(joueur);
                joueur.log("Place une gare sur la tuile "+numTuile);
            }

        } else
            joueur.log("Plus aucun jeton gare disponible");
    }
}
