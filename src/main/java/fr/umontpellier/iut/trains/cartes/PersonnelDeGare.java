package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.Arrays;
import java.util.List;

public class PersonnelDeGare extends Carte {
    public PersonnelDeGare() {
        super("Personnel de gare", TypeCarte.Action,0,2);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<Bouton> boutons = Arrays.asList(
                new Bouton("Piocher 1 carte","piocher"),
                new Bouton("Recevoir 1 valeur","argent"),
                new Bouton("Remettre 1 ferraille sur la pile","ferraille")
        );

        boolean bool = false;
        while(!bool){
            String input = joueur.choisir("Choisissez parmi ces 3 options",null,boutons,false);
            switch (input) {
                case "piocher" :
                    joueur.log("Pioche");
                    joueur.getMain().addAll(joueur.piocher(1));
                    bool = true;
                    break;
                case "argent" :
                    joueur.log("Reçoit 1 d'argent");
                    joueur.incrementerArgent(1);
                    bool = true;
                    break;
                case "ferraille" :
                    if(joueur.getMain().count("Ferraille") == 0){
                        joueur.log("Vous n'avez pas de carte Ferraille dans votre main");
                    } else {
                        joueur.log("Remet 1 ferraille sur la pile");
                        joueur.remettreSurLaPile("Ferraille");
                        bool = true;
                    }
                    break;
                default :
                    bool = true;
            }

        }

    }
}
