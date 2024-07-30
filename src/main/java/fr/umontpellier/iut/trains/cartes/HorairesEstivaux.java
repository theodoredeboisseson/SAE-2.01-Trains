package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.Arrays;
import java.util.List;

public class HorairesEstivaux extends Carte {
    public HorairesEstivaux() {
        super("Horaires estivaux", TypeCarte.Action,0,3);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<Bouton> boutons = Arrays.asList(
                new Bouton("Oui!","oui"),
                new Bouton("Non!","non")
        );

        String input = joueur.choisir(
                "Souhaitez vous écarter cette carte et gagner 3 de valeur ?",
                null,
                boutons,
                false
        );

        if(input.equals("oui")){
            Carte c = joueur.getCartesEnJeu().retirer(this.getNom());
            joueur.getJeu().getCartesEcartees().add(c);
            joueur.incrementerArgent(3);
            joueur.log("écarte cette carte et reçoit 3 de valeur");
        } else {
            joueur.log("Refuse l'effet de la carte");
        }
    }
}
