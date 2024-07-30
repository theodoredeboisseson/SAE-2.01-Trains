package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Remorquage extends Carte {

    public Remorquage() {
        super("Remorquage", TypeCarte.Action,0,3);
    }

    @Override
    public void jouer(Joueur joueur) {
        ListeDeCartes main = joueur.getMain();
        ListeDeCartes defausse = joueur.getDefausse();

        List<String> choix = defausse.selection(TypeCarte.Train, -1, -1).getNoms().stream().distinct().toList();
        if (choix.isEmpty()){
            joueur.log("Vous ne possédez aucune carte Train dans votre défausse, cette carte n'auras aucun effet.");
            return;
        }

        List<Bouton> boutons = new ArrayList<>();
        for(String s : choix)
            boutons.add(new Bouton(s));

        String input = joueur.choisir(
                "Veuillez choisir une carte Train à prendre de la défausse",
                null,
                boutons,
                true
        );
        main.add(defausse.retirer(input));
        joueur.log("Prend la carte "+input+" de la défausse");
    }
}
