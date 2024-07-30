package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;

public class TrainPostal extends Carte {
    public TrainPostal() {
        super("Train postal", TypeCarte.Train,1,4);
    }

    @Override
    public void jouer(Joueur joueur) {
        genererValeurPour(joueur);

        List<String> choix = joueur.getMain().getNoms();
        String input = "null";

        while(!input.isEmpty()){
            input = joueur.choisir(
                    "Choissisez une carte à défausser",
                    choix,
                    null,
                    true
            );
            joueur.defausser(input);

            if(!input.isEmpty()){
                choix.remove(input);
                joueur.incrementerArgent(1);
            }
        }
    }
}
