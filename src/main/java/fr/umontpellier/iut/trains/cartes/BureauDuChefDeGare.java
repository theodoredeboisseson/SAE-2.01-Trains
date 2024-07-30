package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.cartes.Carte;
import fr.umontpellier.iut.trains.cartes.TypeCarte;

import java.util.List;

public class BureauDuChefDeGare extends Carte {
    public BureauDuChefDeGare() {
        super("Bureau du chef de gare", TypeCarte.Action,0,4);
    }


    @Override
    public void jouer(Joueur joueur) {
        List<String> choix = joueur.getMain().selection(TypeCarte.Action,-1,-1).getNoms();

        if(choix.isEmpty()){
            joueur.log("Vous n'avez aucune carte Action dans votre main, cette carte n'auras aucun effet");
            return;
        }

        boolean peutPasser = choix.size() == joueur.getMain().count("Bureau du chef de gare");
        if(peutPasser)
            joueur.getJeu().log("Vous avez que cette carte pouvez passer l'action de la carte");

        String input = joueur.choisir(
                "Choisissez une carte Action de votre main afin de copier son effet",
                choix,
                null,
                peutPasser
        );

        if(!input.isEmpty()){
            Carte c = joueur.getMain().getCarte(input);
            c.jouer(joueur);
        }
    }
}
