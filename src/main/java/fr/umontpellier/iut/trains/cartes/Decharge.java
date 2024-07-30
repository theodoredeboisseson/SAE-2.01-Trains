package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.cartes.Carte;
import fr.umontpellier.iut.trains.cartes.ListeDeCartes;
import fr.umontpellier.iut.trains.cartes.TypeCarte;

public class Decharge extends Carte {
    public Decharge() {
        super("Décharge", TypeCarte.Action,0,2);
    }

    @Override
    public void jouer(Joueur joueur) {
        if(!joueur.getMain().contains("Ferraille"))
            joueur.log("Vous n'avez aucune carte ferraille dans votre main, cette carte n'auras aucun effet");
        else {
            while(joueur.getMain().contains("Ferraille"))
                joueur.remettreSurLaPile("Ferraille");

            joueur.log("Remet toutes les cartes ferraille de sa main dans la réserve");
        }
    }
}
