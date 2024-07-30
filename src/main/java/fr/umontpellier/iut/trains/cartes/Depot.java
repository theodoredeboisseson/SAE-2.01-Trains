package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;

public class Depot extends Carte {
    public Depot() {
        super("Dépôt", TypeCarte.Action,1,3);
    }

    @Override
    public void jouer(Joueur joueur) {
        genererValeurPour(joueur);

        joueur.log("Pioche 2 cartes");
        joueur.getMain().addAll(joueur.piocher(2));


        List<String> choix = joueur.getMain().getNoms();

        String input = joueur.choisir(
                "Choisissez 2 cartes à défausser",
                choix,
                null,
                false
        );

        joueur.defausser(input);

        choix.remove(input);

        input = joueur.choisir(
                "Choisissez la 2e carte à défausser",
                choix,
                null,
                false
        );

        joueur.defausser(input);
    }
}
