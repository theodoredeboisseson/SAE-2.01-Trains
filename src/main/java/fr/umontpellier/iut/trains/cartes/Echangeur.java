package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;

public class Echangeur extends Carte {
    public Echangeur() {
        super("Échangeur", TypeCarte.Action,1,3);
    }

    @Override
    public void jouer(Joueur joueur) {
        genererValeurPour(joueur);

        List<String> choix = joueur.getCartesEnJeu().selection(TypeCarte.Train,-1,-1).getNoms();

        if(choix.isEmpty()){
            joueur.log("Vous n'avez aucune carte Train en jeu, cette carte n'auras aucun effet");
            return;
        }

        String input = joueur.choisir(
                "Choisissez une carte Train jouée à mettre sur votre deck",
                choix,
                null,
                true
        );

        if(input.isEmpty())
            joueur.log("Le joueur passe l'effet de la carte");
        else {
            Carte c = joueur.getCartesEnJeu().retirer(input);
            joueur.getPioche().add(0,c);
            joueur.log("Met la carte "+c.getNom()+" sur son deck");
        }

    }
}
