package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;

public class ParcDAttractions extends Carte {
    public ParcDAttractions() {
        super("Parc d'attractions", TypeCarte.Action,1,4);
    }

    @Override
    public void jouer(Joueur joueur) {
        genererValeurPour(joueur);

        List<String> choix = joueur.getCartesEnJeu()
                .selection(TypeCarte.Train,-1,-1)
                .listeDistincte()
                .getNoms();

        String input = joueur.choisir(
                "Veuillez choisir un nombre de valeur à obtenir égal à la valeur d'une de vos cartes Train en jeu",
                choix,
                null,
                true
        );

        if(!input.isEmpty()){
            int valeur = joueur.getCartesEnJeu().getCarte(input).getValeur();
            joueur.incrementerArgent(valeur);
        }

    }
}
